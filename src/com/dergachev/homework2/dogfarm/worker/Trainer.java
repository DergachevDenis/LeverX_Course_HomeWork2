package com.dergachev.homework2.dogfarm.worker;

import com.dergachev.homework2.dogfarm.dog.Dog;
import com.dergachev.homework2.dogfarm.util.myexception.WorkerException;

import java.math.BigDecimal;

public class Trainer extends Worker implements Work{
    public Trainer() {
    }

    public Trainer(String name, int age, BigDecimal salary) throws WorkerException {
        super(name, age, salary);
    }

    @Override
    public String work(Dog dog) {
        return "Come on! Move, soldier "+dog.getName();
    }
}
