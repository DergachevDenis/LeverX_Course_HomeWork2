package com.dergachev.homework2.dogfarm.dog;

import com.dergachev.homework2.dogfarm.util.date.DateUtil;
import com.dergachev.homework2.dogfarm.util.myexception.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Dog implements Serializable {

    private transient DateUtil dateUtil = new DateUtil();

    private String name;
    private LocalDate birthday;
    private int yearsOld;
    private Age age;
    private PlaceOFWorkDog work;
    private boolean isHungry;
    private boolean isTrained;
    private boolean isHealthy;

    public Dog() {
    }

    public Dog(String name, String birthday, PlaceOFWorkDog work, boolean isHungry, boolean isTrained, boolean isHealthy) throws DateException {
        if (name == null) {
            throw new NullPointerException("Dog need name.");
        }
        this.name = name;
        if (!dateUtil.validDate(birthday)) {
            throw new DateException("Please, set birthday in format " + DateUtil.DATE_PATTERN);
        }
        this.birthday = dateUtil.parse(birthday);
        this.yearsOld = dateUtil.getYearsOld(this.birthday);
        setAge(this.yearsOld);
        this.isHungry = isHungry;
        this.isTrained = isTrained;
        this.isHealthy = isHealthy;

        if (work == null) {
            throw new NullPointerException("Dog need work.");
        }
        this.work = switch (this.age){
            case PUPPY -> PlaceOFWorkDog.TRAINING;
            case ADULTDOG -> work;
            case ELDERLYDOG -> PlaceOFWorkDog.UNEMPLOYED;
        };

    }

    public Dog(String name, String birthday, boolean isHungry, boolean isTrained, boolean isHealthy) throws DateException {
        validName(name);
        this.name = name;
        if (!dateUtil.validDate(birthday)) {
            throw new DateException("Please, set birthday in format " + DateUtil.DATE_PATTERN);
        }
        this.birthday = dateUtil.parse(birthday);
        this.yearsOld = dateUtil.getYearsOld(this.birthday);
        setAge(this.yearsOld);
        this.isHungry = isHungry;
        this.isTrained = isTrained;
        this.isHealthy = isHealthy;

        this.work = switch (this.age){
            case PUPPY -> PlaceOFWorkDog.TRAINING;
            case ADULTDOG, ELDERLYDOG -> PlaceOFWorkDog.UNEMPLOYED;
        };

    }

    public String getName() {
        return name;
    }

    public int getYearsOld() {
        return yearsOld;
    }

    public Age getAge() {
        return age;
    }

    public boolean isHungry() {
        return isHungry;
    }

    public void setHungry(boolean hungry) {
        isHungry = hungry;
    }

    public boolean isTrained() {
        return isTrained;
    }

    public void setTrained(boolean trained) {
        isTrained = trained;
    }

    public boolean isHealthy() {
        return isHealthy;
    }

    public void setHealthy(boolean healthy) {
        isHealthy = healthy;
    }

    public PlaceOFWorkDog getWork() {
        return work;
    }

    public void setWork(PlaceOFWorkDog work)  {
        if (work == null) {
            throw new NullPointerException("Dog need work.");
        }
        this.work = switch (this.age){
            case PUPPY -> PlaceOFWorkDog.TRAINING;
            case ADULTDOG -> work;
            case ELDERLYDOG -> PlaceOFWorkDog.UNEMPLOYED;
        };
    }

    public void setBirthday(String birthday) throws DateException {
        if (!dateUtil.validDate(birthday)) {
            throw new DateException("Please, set birthday in format " + DateUtil.DATE_PATTERN);
        }
        this.birthday = dateUtil.parse(birthday);
        this.yearsOld = dateUtil.getYearsOld(this.birthday);
        setAge(this.yearsOld);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dog dog = (Dog) o;
        return isTrained == dog.isTrained && Objects.equals(name, dog.name) && age == dog.age;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, isTrained);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Dog name: " + this.name + "\nBirthday: " + this.birthday + "\nYears: " +
                +this.yearsOld + "\nAge: " + this.age + "\nWork: " + this.work + "\n");
        if (this.isHungry) {
            result.append("Dog hungry, ");
        } else {
            result.append("Dog not hungry, ");
        }
        if (this.isHealthy) {
            result.append("healthy, ");
        } else {
            result.append("not healthy, ");
        }
        if (this.isTrained) {
            result.append("training.");
        } else {
            result.append("not training.");
        }
        return result.toString();
    }

    private void validName(String name) {
        if (name == null) {
            throw new NullPointerException("Dog need name.");
        }
    }

    private void setAge(int yearsOld) {
        if (yearsOld > 2 && yearsOld < 8) {
            this.age = Age.ADULTDOG;
        } else if (yearsOld < 2) {
            this.age = Age.PUPPY;
        } else {
            this.age = Age.ELDERLYDOG;
        }
    }
}
