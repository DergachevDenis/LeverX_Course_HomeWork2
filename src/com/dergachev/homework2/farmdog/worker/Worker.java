package com.dergachev.homework2.farmdog.worker;

import com.dergachev.homework2.farmdog.exception.WorkerException;

import java.math.BigDecimal;
import java.util.Objects;

public class Worker {
    private String name;
    private int age;
    private Work work;
    private BigDecimal salary;

    public Worker() {
    }

    public Worker(String name, int age, Work work, BigDecimal salary) throws WorkerException {
        this.name = name;
        if(age<0){
            throw new WorkerException("The age of the worker cannot be negative");
        }
        this.age = age;
        this.work = work;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) throws WorkerException {
        if(age<0){
            throw new WorkerException("The age of the worker cannot be negative");
        }
        this.age = age;
    }

    public Work getWork() {
        return work;
    }

    public void setWork(Work work) {
        this.work = work;
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
        return age == worker.age && Objects.equals(name, worker.name) && Objects.equals(work, worker.work) && Objects.equals(salary, worker.salary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, work, salary);
    }

    @Override
    public String toString() {
        return "Worker{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", work='" + work + '\'' +
                ", salary=" + salary +
                '}';
    }
}
