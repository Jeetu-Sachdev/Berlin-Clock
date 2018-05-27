package com.ubs.opsit.interviews;

import com.ubs.opsit.interviews.exception.BerlinClockException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

/**
 * Created by jeetu on 5/27/18.
 */
public class TimeConverterImplIntegrationTest {
    public static final String MIDNIGHT = "00:00:00";
    public static final String NOON = "12:00:00";
    public static final String ODD_SECONDS = "00:00:01";
    public static final String EVENING_17_35_25 = "17:37:25";
    public static final String SPECIAL_MIDNIGHT = "24:00:00";
    TimeConverter unit;
    private static String MIDNIGHT_TIME = "Y\nOOOO\nOOOO\nOOOOOOOOOOO\nOOOO";
    private static String ODD_SECONDS_TIME = "O\nOOOO\nOOOO\nOOOOOOOOOOO\nOOOO";
    private static String NOON_TIME = "Y\nRROO\nRROO\nOOOOOOOOOOO\nOOOO";
    private static String EVENING_17_37_25_TIME = "O\nRRRO\nRROO\nYYRYYRYOOOO\nYYOO";
    private static String SPECIAL_MIDNIGHT_TIME = "Y\nRRRR\nRRRR\nOOOOOOOOOOO\nOOOO";

    @Before
    public void setUp() throws Exception {
      unit = new TimeConverterImpl();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test(expected = BerlinClockException.class)
    public void shouldThrowExceptionWhenNullTimeIsPassed() throws Exception {
        unit.convertTime(null);
    }

    @Test(expected = BerlinClockException.class)
    public void shouldThrowExceptionWhenBlankTimeIsPassed() throws Exception {
        unit.convertTime("");
    }

    @Test(expected = BerlinClockException.class)
    public void shouldThrowExceptionWhenInvalidTimeIsPassed() throws Exception {
        unit.convertTime("10000");
    }

    @Test
    public void shouldReturnBerlinClockAtMidnightWhenTimeAtMidnightIsPassed() throws Exception {
        String clock = unit.convertTime(MIDNIGHT);
        assertThat(clock, is(MIDNIGHT_TIME));
    }

    @Test
    public void shouldReturnBerlinClockAtOddSecondsWhenTimeAtOddSecondsIsPassed() throws Exception {
        String clock = unit.convertTime(ODD_SECONDS);
        assertThat(clock, is(ODD_SECONDS_TIME));
    }

    @Test
    public void shouldReturnBerlinClockAtNoonWhenTimeAtNoonIsPassed() throws Exception {
        String clock = unit.convertTime(NOON);
        assertThat(clock, is(NOON_TIME));
    }

    @Test
    public void shouldReturnBerlinClockAtTimeWithMinutesWhenTimeWithMinutesIsPassed() throws Exception {
        String clock = unit.convertTime(EVENING_17_35_25);
        assertThat(clock, is(EVENING_17_37_25_TIME));
    }

    @Test
    public void shouldReturnBerlinClockAtMidnightWhenSpecialMidnightTimeIsPassed() throws Exception {
        String clock = unit.convertTime(SPECIAL_MIDNIGHT);
        assertThat(clock, is(SPECIAL_MIDNIGHT_TIME));
    }

}