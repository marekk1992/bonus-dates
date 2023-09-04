package com.devbridge.bonusdate;

import com.devbridge.bonusdate.error.InvalidYearException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BonusDate {

    public static void printBonusDatesBetween(int fromYear, int toYear) {
        validateInput(fromYear, toYear);
        LocalDate startDate = LocalDate.of(fromYear, 1, 1);
        LocalDate endDate = LocalDate.of(toYear, 1, 1);
        List<LocalDate> datesBetween = startDate.datesUntil(endDate).toList();
        List<LocalDate> suitableDates = new ArrayList<>();
        datesBetween.forEach(date -> {
            if (date.equals(reverseDate(date))) {
                suitableDates.add(date);
            }
        });
        suitableDates.forEach(System.out::println);
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

        if (isValidYear(reversedYear) && isValidMonth(reversedMonth) && isValidDay(reversedDay)) {
            return LocalDate.of(reversedYear, reversedMonth, reversedDay);
        } else {
            return null;
        }
    }

    private static boolean isValidYear(int year) {
        return year / 1000 >= 1;
    }

    private static boolean isValidMonth(int month) {
        return month >= 1 && month <= 12;
    }

    private static boolean isValidDay(int day) {
        return day >= 1 && day <= 31;
    }
}
