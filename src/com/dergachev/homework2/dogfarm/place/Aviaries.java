package com.dergachev.homework2.dogfarm.place;

import com.dergachev.homework2.dogfarm.dog.Dog;
import com.dergachev.homework2.dogfarm.util.myexception.WorkerException;
import com.dergachev.homework2.dogfarm.worker.Cleaner;
import com.dergachev.homework2.dogfarm.worker.Cook;
import com.dergachev.homework2.dogfarm.worker.Worker;

import java.util.List;

public class Aviaries {
    private Cleaner cleaner;
    private boolean isClear = false;

    public Aviaries() {
    }

    public Aviaries(Worker cleaner) throws WorkerException {
        validCleaner(cleaner);
        this.cleaner = (Cleaner) cleaner;
    }

    public Worker getCleaner() {
        return cleaner;
    }

    public void setCleaner(Worker cleaner) throws WorkerException {
        validCleaner(cleaner);
        this.cleaner = (Cleaner) cleaner;
    }

    public boolean isClear() {
        return isClear;
    }

    public void setClear(boolean clear) {
        isClear = clear;
    }

    public void clearAviaries() {
        if (this.isClear) {
            System.out.println(cleaner.getName()+": I have no work for today, the enclosures are clean. I can go for a walk!");
            return;
        }
        this.isClear = true;
        System.out.println("The worker " + cleaner.getName() + " cleans the aviaries\n");
    }



    public void putToSleep(List<Dog> dogList){
        dogList.forEach(e->e.setHungry(true));
    }

    private void validCleaner(Worker cleaner) throws WorkerException {
        if(cleaner == null){
            throw new NullPointerException("There is a cleaner to work here");
        }
        else if (cleaner.getClass()!=Cleaner.class) {
            throw new WorkerException( "It is not the responsibility of the " + cleaner.getClass() + " to clean the enclosures");
        }
    }
}
