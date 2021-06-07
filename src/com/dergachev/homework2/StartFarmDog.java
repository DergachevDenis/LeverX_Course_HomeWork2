package com.dergachev.homework2;

import com.dergachev.homework2.dogfarm.FarmDog;
import com.dergachev.homework2.dogfarm.dog.Dog;
import com.dergachev.homework2.dogfarm.dog.PlaceOFWorkDog;
import com.dergachev.homework2.dogfarm.util.myexception.DateException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StartFarmDog {
    public static void main(String[] args) {
        FarmDog farmDog = new FarmDog();
        farmDog.doOneDay();
        farmDog.printListDogInFile("");
    }
}
