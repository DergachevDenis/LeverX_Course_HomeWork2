package com.dergachev.homework2.dogfarm.place;

import com.dergachev.homework2.dogfarm.dog.Dog;
import com.dergachev.homework2.dogfarm.util.myexception.*;
import com.dergachev.homework2.dogfarm.worker.Veterinary;
import com.dergachev.homework2.dogfarm.worker.Worker;

import java.util.List;

public class VeterinaryClinic {

    private Veterinary veterinary;

    public VeterinaryClinic() {
    }

    public VeterinaryClinic(Worker veterinary) throws WorkerException {
        validVetetinary(veterinary);
        this.veterinary = (Veterinary) veterinary;
    }

    public Worker getVeterinary() {
        return veterinary;
    }

    public void setVeterinary(Worker veterinary) throws WorkerException {
        validVetetinary(veterinary);
        this.veterinary = (Veterinary) veterinary;
    }

    public void inspectionDogs(List<Dog> dogList) {
        validDogList(dogList);
        long countNoHealthy = dogList.stream().peek(e -> System.out.println("The veterinary " + veterinary.getName() + " inspected " + e.getName())).filter(e -> !e.isHealthy())
                .peek(e -> veterinary.work(e)).count();
        if (countNoHealthy > 0) {
            System.out.println(veterinary.getName()+": I examined and cured all dogs!");
        }
        else {
            System.out.println(veterinary.getName()+": I guess I'm a very good doctor, because all dogs are healthy.");
        }
        System.out.println();
    }

    private void cureDog(Dog dog) {
        System.out.println("The veterinary " + veterinary.getName() + " treats " + dog.getName() + ".");
        dog.setHealthy(true);
    }

    private void validVetetinary(Worker veterinary) throws WorkerException {
        System.out.println(veterinary instanceof Veterinary);
        if (veterinary == null) {
            throw new NullPointerException("There is a veterinary to work here.");
        } else if (veterinary.getClass() != Veterinary.class) {
            throw new WorkerException("It is not the responsibility of the " + veterinary.getClass() + " to treat dogs.");
        }
    }

    private void validDogList(List<Dog> dogList) {
        if (dogList == null || dogList.isEmpty()) {
            throw new NullPointerException("Need someone to inspection.");
        }
    }
}
