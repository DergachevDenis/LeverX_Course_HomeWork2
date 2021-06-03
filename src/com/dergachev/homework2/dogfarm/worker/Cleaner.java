package com.dergachev.homework2.dogfarm.worker;

import com.dergachev.homework2.dogfarm.dog.Dog;
import com.dergachev.homework2.dogfarm.util.myexception.WorkerException;

import java.math.BigDecimal;

public class Cleaner extends Worker implements Work{

    public Cleaner() {
    }

    public Cleaner(String name, int age, BigDecimal salary) throws WorkerException {
        super(name, age, salary);
    }

    @Override
    public String work(Dog dog) {
        return "Wow how you stink, let me wash you "+dog.getName();
    }
}
