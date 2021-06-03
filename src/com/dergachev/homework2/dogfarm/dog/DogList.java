package com.dergachev.homework2.dogfarm.dog;


import com.dergachev.homework2.dogfarm.util.myexception.DogException;

import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class DogList {

    private static String pathPrintListDefault = "resources/PrintListsDog/";
    private static final File FILE_FOR_SERIALIZATION = new File("resources/SaveListDog/ListDog.dat");

    //Will need to be redone to Map
    private List<Dog> dogList;

    public DogList() {
        initDogList();
    }

    public DogList(List<Dog> dogList) {
        this.dogList = Optional.of(new ArrayList<>(dogList)).orElse(new ArrayList<>());
        saveDogListInFile();
    }

    public static String getPathPrintListDefault() {
        return pathPrintListDefault;
    }

    public static void setPathPrintListDefault(String pathPrintListDefault) {
        DogList.pathPrintListDefault = pathPrintListDefault;
    }

    public List<Dog> getDogList() {
        return dogList;
    }

    public void setDogList(List<Dog> dogList) {
        this.dogList = Optional.of(new ArrayList<>(dogList)).orElse(new ArrayList<>());
        saveDogListInFile();
    }

    public void addDog(Dog dog) {
        if (dog == null) {
            throw new NullPointerException("Need dog");
        }
        this.dogList.add(dog);
        saveDogListInFile();
    }

    public void removeDogByName(String dogName) throws DogException {
        if (dogName == null) {
            throw new NullPointerException("Need name dog");
        }
        Dog dogRemove = dogList.stream().filter(e -> e.getName().equalsIgnoreCase(dogName)).findFirst().orElseThrow(() -> new DogException("No dogs found with this name"));
        dogList.remove(dogRemove);
        saveDogListInFile();
    }

    public Dog getDogByName(String dogName) throws DogException {
        return dogList.stream().filter(e -> e.getName().equalsIgnoreCase(dogName)).findFirst().orElseThrow(() -> new DogException("No dogs found with this name"));
    }


    public List<Dog> getSortedDogListByName() {
        return Optional.of(dogList.stream().sorted(Comparator.comparing(Dog::getName)).collect(Collectors.toList())).orElse(new ArrayList<>());
    }

    public List<Dog> getSortedDogListByAge() {
        return Optional.of(dogList.stream().sorted
                ((e1, e2) -> e1.getAge() != (e2.getAge()) ? e1.getAge().compareTo(e2.getAge()) : e1.getName().compareTo(e2.getName()))
                .collect(Collectors.toList())).orElse(new ArrayList<>());
    }

    public List<Dog> getListAgeDog(Age age) throws DogException {
        if (age == null) {
            throw new DogException("please indicate age: " + Age.PUPPY + ", " + Age.ADULTDOG + ", " + Age.ELDERLYDOG + ".");
        }
        return switch (age) {
            case PUPPY -> Optional.of(dogList.stream().filter(e -> e.getAge() == Age.PUPPY).sorted(Comparator.comparing(Dog::getName)).collect(Collectors.toList()))
                    .orElse(new ArrayList<>());
            case ADULTDOG -> Optional.of(dogList.stream().filter(e -> e.getAge() == Age.ADULTDOG).sorted(Comparator.comparing(Dog::getName)).collect(Collectors.toList()))
                    .orElse(new ArrayList<>());
            case ELDERLYDOG -> Optional.of(dogList.stream().filter(e -> e.getAge() == Age.ELDERLYDOG).sorted(Comparator.comparing(Dog::getName)).collect(Collectors.toList()))
                    .orElse(new ArrayList<>());
        };
    }

    public Map<Age, List<Dog>> getMapDogByAge() {
        return Optional.of(dogList.stream().collect(Collectors.groupingBy(Dog::getAge))).orElse(new HashMap<>());
    }

    public Map<PlaceOFWork, List<Dog>> getMapDogByWork() {
        return Optional.of(dogList.stream().collect(Collectors.groupingBy(Dog::getWork))).orElse(new HashMap<>());
    }


    public void printListDogInFile(String path) {
        String pathFile;
        if (path == null || path.equals("")) {
            pathFile = getPrintListPath(pathPrintListDefault);
        } else {
            pathFile = getPrintListPath(path);
        }
        File file = new File(pathFile);
        try (FileWriter fileWriter = new FileWriter(file, false);
             BufferedWriter writer = new BufferedWriter(fileWriter)) {
            for (Dog e : dogList) {
                try {
                    writer.write(e.toString());
                    writer.write("\n\n");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            writer.flush();
            System.out.println("DogList has been written");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveDogListInFile() {
        try (FileOutputStream fileWriter = new FileOutputStream(FILE_FOR_SERIALIZATION, false);
             ObjectOutputStream outputStream = new ObjectOutputStream(fileWriter)) {
            outputStream.writeObject(dogList);
            System.out.println("DogList has been save");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public List<Dog> getDogListFromFile() {
        List<Dog> dogList = new ArrayList<>();
        try (FileInputStream fileInput = new FileInputStream(FILE_FOR_SERIALIZATION);
             ObjectInputStream inputStream = new ObjectInputStream(fileInput)) {
            dogList = (ArrayList<Dog>) inputStream.readObject();
            System.out.println("DogList has been read");
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return dogList;
    }


    private String getPrintListPath(String path) {
        LocalDate dateNow = LocalDate.now();
        return path + "ListDog_" + dateNow + ".txt";
    }


    private void initDogList() {
        if (FILE_FOR_SERIALIZATION.exists()) {
            this.dogList = getDogListFromFile();
        } else {
            this.dogList = new ArrayList<>();
        }
    }
}

//        try {
//            Dog dog1 = new Dog("Barbos", "12.01.2017", PlaceOFWork.POLICE, false, true, true);
//            Dog dog2 = new Dog("Sharik", "19.07.2014", true, true, false);
//            Dog dog3 = new Dog("Gav", "12.04.2020", true, false, true);
//            Dog dog4 = new Dog("Bim", "03.08.2020", false, false, false);
//            Dog dog5 = new Dog("Palkan", "31.08.2018", PlaceOFWork.EMERGENCY, true, true, false);
//            Dog dog6 = new Dog("Pes", "25.07.2010", false, true, true);
//
//            dogList.add(dog1);
//            dogList.add(dog2);
//            dogList.add(dog3);
//            dogList.add(dog4);
//            dogList.add(dog5);
//            dogList.add(dog6);
//
//        } catch (DateException e) {
//            System.out.println(e.getMessage());
//        }
