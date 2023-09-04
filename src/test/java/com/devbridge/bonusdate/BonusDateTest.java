package com.devbridge.bonusdate;

import com.devbridge.bonusdate.error.InvalidYearException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class BonusDateTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @ParameterizedTest
    @MethodSource("normalFlowTestCases")
    public void prints_dates_within_given_range_of_years_which_remains_same_if_numbers_are_reversed(
            int fromYear, int toYear, String expectedOutput
    ) {
        // when
        BonusDate.printBonusDatesBetween(fromYear, toYear);

        // then
        assertEquals(outContent.toString(), expectedOutput);
    }

    @ParameterizedTest
    @MethodSource("errorTestCases")
    public void throws_exception_when_input_data_is_invalid(
            int fromYear, int toYear, String expectedMessage
    ) {
        // when
        InvalidYearException thrown = assertThrows(
                InvalidYearException.class,
                () -> BonusDate.printBonusDatesBetween(fromYear, toYear)
        );

        // then
        assertTrue(thrown.getMessage().contains(expectedMessage));
    }

    private static Stream<Arguments> normalFlowTestCases() {
        return Stream.of(
                arguments(
                        2002, 2010, ""
                ),
                arguments(
                        2010, 2015, """
                                2010-01-02
                                2011-11-02
                                """
                )
        );
    }

    private static Stream<Arguments> errorTestCases() {
        return Stream.of(
                arguments(
                        -1, 2010, "Start year can`t be negative."
                ),
                arguments(
                        2010, 2010, "End year must be higher than start year."
                )
        );
    }
}
