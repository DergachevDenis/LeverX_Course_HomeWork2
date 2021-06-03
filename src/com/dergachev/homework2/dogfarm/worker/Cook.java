package com.dergachev.homework2.dogfarm.worker;

import com.dergachev.homework2.dogfarm.dog.Dog;
import com.dergachev.homework2.dogfarm.util.myexception.WorkerException;

import java.math.BigDecimal;

public class Cook extends Worker implements Work{

    public Cook() {
    }

    public Cook(String name, int age, BigDecimal salary) throws WorkerException {
        super(name, age, salary);
    }

    @Override
    public String work(Dog dog) {


        return "Ooo how you lost weight, let me feed you "+dog.getName();
    }
}
