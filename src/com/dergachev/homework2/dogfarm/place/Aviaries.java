package com.dergachev.homework2.dogfarm.place;

import com.dergachev.homework2.dogfarm.util.exception.*;
import com.dergachev.homework2.dogfarm.worker.Work;
import com.dergachev.homework2.dogfarm.worker.Worker;

public class Aviaries {
    private Worker cleaner;
    private boolean isClear = false;

    public Aviaries() {
    }

    public Aviaries(Worker cleaner) throws WorkerException {
        validCleaner(cleaner);
        this.cleaner = cleaner;
    }

    public Worker getCleaner() {
        return cleaner;
    }

    public void setCleaner(Worker cleaner) throws WorkerException {
        validCleaner(cleaner);
        this.cleaner = cleaner;
    }

    public boolean isClear() {
        return isClear;
    }

    public void setClear(boolean clear) {
        isClear = clear;
    }

    public void clearAviaries() {
        if (this.isClear) {
            System.out.println("Aviaries have already bean cleaned");
            return;
        }
        this.isClear = true;
        System.out.println("The worker " + cleaner.getName() + " cleans the aviaries\n");
    }

    private void validCleaner(Worker cleaner) throws WorkerException {
        if(cleaner == null){
            throw new NullPointerException("There is a cleaner to work here");
        }
        else if (cleaner.getWork() != Work.CLEANER) {
            throw new WorkerException("It is not the responsibility of the " + cleaner.getWork() + " to clean the enclosures");
        }
    }
}
