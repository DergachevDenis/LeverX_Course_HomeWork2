package com.dergachev.homework2.dogfarm.worker;

import com.dergachev.homework2.dogfarm.util.myexception.*;

import java.math.BigDecimal;
import java.util.Objects;

public abstract class Worker {
    private String name;
    private Integer age;
    private BigDecimal salary;

    public Worker() {
    }

    public Worker(String name, int age, BigDecimal salary) throws WorkerException {
        this.name = name;
        if (age < 0) {
            throw new WorkerException("The age of the worker cannot be negative");
        }
        this.age = age;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(int age) throws WorkerException {
        if (age < 0) {
            throw new WorkerException("The age of the worker cannot be negative");
        }
        this.age = age;
    }


    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Worker worker = (Worker) o;
        return age == worker.age && Objects.equals(name, worker.name)  && Objects.equals(salary, worker.salary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, salary);
    }

    @Override
    public String toString() {
        return "Worker{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                '}';
    }
}
