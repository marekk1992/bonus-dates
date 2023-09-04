# Bonus Dates

## Description

This application provides implementation of method `printBonusDatesBetween(int fromYear, int toYear)`, that prints all dates between two given years, which remain same if numbers are reversed.

If there are no such dates, empty output is returned. While exception is thrown in case of invalid input values.

Some usage examples:

- `printBonusDatesBetween(2010, 2015)` outputs:

       2010-01-02
       2011-11-02

- `printBonusDatesBetween(2010, 2010)` throws exception with a message:

        End year must be higher than start year.
