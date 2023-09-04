package com.devbridge.bonusdate;

import com.devbridge.bonusdate.error.InvalidYearException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;

public class BonusDate {

    private static final LocalDate NON_EXISTING_DATE = null;

    public static void printBonusDatesBetween(int fromYear, int toYear) {
        validateInput(fromYear, toYear);
        LocalDate startDate = LocalDate.of(fromYear, 1, 1);
        LocalDate endDate = LocalDate.of(toYear, 1, 1);
        List<LocalDate> datesBetween = startDate.datesUntil(endDate).toList();
        datesBetween.stream()
                .filter(date -> date.equals(reverseDate(date)))
                .forEach(System.out::println);
    }

    private static void validateInput(int fromYear, int toYear) {
        if (fromYear < 0) {
            throw new InvalidYearException("Start year can`t be negative.");
        }
        if (fromYear >= toYear) {
            throw new InvalidYearException("End year must be higher than start year.");
        }
    }

    private static LocalDate reverseDate(LocalDate date) {
        StringBuilder reversedDate = new StringBuilder(date.toString().replace("-", "")).reverse();
        int reversedYear = Integer.parseInt(reversedDate.substring(0, 4));
        int reversedMonth = Integer.parseInt(reversedDate.substring(4, 6));
        int reversedDay = Integer.parseInt(reversedDate.substring(6, 8));

        try {
            return LocalDate.of(reversedYear, reversedMonth, reversedDay);
        } catch (DateTimeException e) {
            return NON_EXISTING_DATE;
        }
    }
}
