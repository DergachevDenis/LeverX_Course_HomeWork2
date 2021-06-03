package com.dergachev.homework2.dogfarm.util.date;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class DateUtil implements Serializable {


    public static final String DATE_PATTERN = "dd.MM.yyyy";


    private DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern(DATE_PATTERN);


    public  LocalDate parse(String dateString) {
        try {
            return DATE_FORMATTER.parse(dateString, LocalDate::from);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    public int getYearsOld(LocalDate birthday){
        Period dif = Period.between(birthday, LocalDate.now());
        return dif.getYears();
    }


    public  boolean validDate(String dateString) {
        return parse(dateString) != null;
    }
}