package com.dergachev.homework2.farmdog.dog;

import com.dergachev.homework2.farmdog.exception.DogException;

import java.util.Objects;

public class Dog {
    private String name;
    private int yearsOld;
    private Age age;
    private String work = "unemployed";
    private boolean isHungry;
    private boolean isTraining;
    private boolean isHealthy;


    public Dog(String name, int yearsOld, String work, boolean isHungry, boolean isTraining, boolean isHealthy) throws DogException {
        this.name = name;
        if(yearsOld<0){
            throw new DogException("The age of the dog cannot be negative");
        }
        this.yearsOld = yearsOld;
        setAge(yearsOld);
        this.isHungry = isHungry;
        this.isTraining = isTraining;
        this.isHealthy = isHealthy;
        if (age == Age.ADULTDOG) {
            this.work = work;
        }
    }
    public Dog(String name, int yearsOld, boolean isHungry, boolean isTraining, boolean isHealthy) throws DogException {
        this.name = name;
        if(yearsOld<0){
            throw new DogException("The age of the dog cannot be negative");
        }
        this.yearsOld = yearsOld;
        setAge(yearsOld);
        this.isHungry = isHungry;
        this.isTraining = isTraining;
        this.isHealthy = isHealthy;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearsOld() {
        return yearsOld;
    }

    public void setYearsOld(int yearsOld) throws DogException {
        if(yearsOld<0){
            throw new DogException("The age of the dog cannot be negative");
        }
        this.yearsOld = yearsOld;
        setAge(yearsOld);
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

    public boolean isTraining() {
        return isTraining;
    }

    public void setTraining(boolean training) {
        isTraining = training;
    }

    public boolean isHealthy() {
        return isHealthy;
    }

    public void setHealthy(boolean healthy) {
        isHealthy = healthy;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dog dog = (Dog) o;
        return isTraining == dog.isTraining && Objects.equals(name, dog.name) && age == dog.age;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, isTraining);
    }

    @Override
    public String toString() {
        StringBuffer result = new StringBuffer("Dog name: " + this.name + "\nAge: " + this.age + "\n");
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
        if (this.isTraining) {
            result.append("training.");
        } else {
            result.append("not training.");
        }
        return result.toString();
    }

    private void setAge(int yearsOld) {
        if (yearsOld > 2 && yearsOld < 8) {
            this.age = Age.ADULTDOG;
        }
        else if (yearsOld <2){
            this.age = Age.PUPPY;
        }
        else {
            this.age=Age.ELDERLYDOG;
        }
    }
}
