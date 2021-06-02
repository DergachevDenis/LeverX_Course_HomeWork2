package com.dergachev.homework2.dogfarm.place;

import com.dergachev.homework2.dogfarm.dog.Dog;
import com.dergachev.homework2.dogfarm.util.myexception.*;
import com.dergachev.homework2.dogfarm.worker.Work;
import com.dergachev.homework2.dogfarm.worker.Worker;

import java.util.List;

public class VeterinaryClinic {

    private Worker veterinary;

    public VeterinaryClinic() {
    }

    public VeterinaryClinic(Worker veterinary) throws WorkerException {
        validVetetinary(veterinary);
        this.veterinary = veterinary;
    }

    public Worker getVeterinary() {
        return veterinary;
    }

    public void setVeterinary(Worker veterinary) throws WorkerException {
        validVetetinary(veterinary);
        this.veterinary = veterinary;
    }

    public void inspectionDogs(List<Dog> dogList) {
        validDogList(dogList);
        for (Dog dog : dogList) {
            System.out.println("The veterinary "+ veterinary.getName()+" inspection "+dog.getName()+".");
            if(!dog.isHealthy()){
                System.out.println("The veterinary "+ veterinary.getName()+" treats "+dog.getName()+".");
                dog.setHealthy(true);
            }
        }
        System.out.println("The veterinary "+ veterinary.getName()+" inspected all dogs.\n");
    }

    private void validVetetinary(Worker veterinary) throws WorkerException {
        if (veterinary == null) {
            throw new NullPointerException("There is a veterinary to work here.");
        } else if (veterinary.getWork() != Work.VETERINARY) {
            throw new WorkerException("It is not the responsibility of the " + veterinary.getWork() + " to treat dogs.");
        }
    }

    private void validDogList(List<Dog> dogList) {
        if (dogList == null) {
            throw new NullPointerException("Need someone to inspection.");
        }
    }
}
