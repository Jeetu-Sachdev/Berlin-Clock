package com.ubs.opsit.interviews;

import com.ubs.opsit.interviews.exception.BerlinClockException;
import com.ubs.opsit.interviews.strategy.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by jeetu on 5/27/18.
 */
public class TimeConverterImplTest {
    public static final String MIDNIGHT = "00:00:00";
    public static final String NOON = "12:00:00";
    public static final String ODD_SECONDS = "00:00:01";
    public static final String EVENING_17_35_25 = "17:37:25";
    public static final String SPECIAL_MIDNIGHT = "24:00:00";
    TimeConverter unit;
    private ClockStrategy mockSecondsStrategy;
    private ClockStrategy mockHoursFirstRowStrategy;
    private ClockStrategy mockHoursSecondRowStrategy;
    private ClockStrategy mockMinutesFirstRowStrategy;
    private ClockStrategy mockMinutesSecondRowStrategy;
    private static String MIDNIGHT_TIME = "Y\nOOOO\nOOOO\nOOOOOOOOOOO\nOOOO";
    private static String ODD_SECONDS_TIME = "O\nOOOO\nOOOO\nOOOOOOOOOOO\nOOOO";
    private static String NOON_TIME = "Y\nRROO\nRROO\nOOOOOOOOOOO\nOOOO";
    private static String EVENING_17_37_25_TIME = "O\nRRRO\nRROO\nYYRYYRYOOOO\nYYOO";
    private static String SPECIAL_MIDNIGHT_TIME = "Y\nRRRR\nRRRR\nOOOOOOOOOOO\nOOOO";


    @Before
    public void setUp() throws Exception {
        mockSecondsStrategy = mock(SecondsStrategy.class);
        mockHoursFirstRowStrategy = mock(HoursFirstRowStrategy.class);
        mockHoursSecondRowStrategy = mock(HoursSecondRowStrategy.class);
        mockMinutesFirstRowStrategy = mock(MinutesFirstRowStrategy.class);
        mockMinutesSecondRowStrategy = mock(MinutesSecondRowStrategy.class);
        unit = new TimeConverterImpl(mockSecondsStrategy, mockHoursFirstRowStrategy,
                mockHoursSecondRowStrategy, mockMinutesFirstRowStrategy, mockMinutesSecondRowStrategy);
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
    public void shouldThrowExceptionWhenInvalidHoursArePassed() throws Exception {
        unit.convertTime("25:00:00");
    }

    @Test(expected = BerlinClockException.class)
    public void shouldThrowExceptionWhenInvalidMinutesArePassed() throws Exception {
        unit.convertTime("10:85:00");
    }

    @Test(expected = BerlinClockException.class)
    public void shouldThrowExceptionWhenInvalidSecondsArePassed() throws Exception {
        unit.convertTime("10:00:78");
    }

    @Test(expected = BerlinClockException.class)
    public void shouldThrowExceptionWhenInvalidTimeIsPassed() throws Exception {
        unit.convertTime("10000");
    }

    @Test
    public void shouldReturnBerlinClockAtMidnightWhenTimeAtMidnightIsPassed() throws Exception {
        mockStrategies("Y", "OOOO",
                "OOOO", "OOOOOOOOOOO", "OOOO", MIDNIGHT);
        String clock = unit.convertTime(MIDNIGHT);
        assertThat(clock, is(MIDNIGHT_TIME));
    }

    @Test
    public void shouldReturnBerlinClockAtOddSecondsWhenTimeAtOddSecondsIsPassed() throws Exception {
        mockStrategies("O", "OOOO",
                "OOOO", "OOOOOOOOOOO", "OOOO", ODD_SECONDS);
        String clock = unit.convertTime(ODD_SECONDS);
        assertThat(clock, is(ODD_SECONDS_TIME));
    }

    @Test
    public void shouldReturnBerlinClockAtNoonWhenTimeAtNoonIsPassed() throws Exception {
        mockStrategies("Y", "RROO",
                "RROO", "OOOOOOOOOOO", "OOOO", NOON);
        String clock = unit.convertTime(NOON);
        assertThat(clock, is(NOON_TIME));
    }

    @Test
    public void shouldReturnBerlinClockAtTimeWithMinutesWhenTimeWithMinutesIsPassed() throws Exception {
        mockStrategies("O", "RRRO",
                "RROO", "YYRYYRYOOOO", "YYOO", EVENING_17_35_25);
        String clock = unit.convertTime(EVENING_17_35_25);
        assertThat(clock, is(EVENING_17_37_25_TIME));
    }

    @Test
    public void shouldReturnBerlinClockAtMidnightWhenSpecialMidnightTimeIsPassed() throws Exception {
        mockStrategies("Y", "RRRR",
                "RRRR", "OOOOOOOOOOO", "OOOO", SPECIAL_MIDNIGHT);
        String clock = unit.convertTime(SPECIAL_MIDNIGHT);
        assertThat(clock, is(SPECIAL_MIDNIGHT_TIME));
    }

    private void mockStrategies(String secondLamps, String hoursFirstLamps, String hoursSecondLamps, String minutesFirstLamps, String minutesSecondLamps, String time) {
        when(mockSecondsStrategy.apply(time)).thenReturn(secondLamps);
        when(mockHoursFirstRowStrategy.apply(time)).thenReturn(hoursFirstLamps);
        when(mockHoursSecondRowStrategy.apply(time)).thenReturn(hoursSecondLamps);
        when(mockMinutesFirstRowStrategy.apply(time)).thenReturn(minutesFirstLamps);
        when(mockMinutesSecondRowStrategy.apply(time)).thenReturn(minutesSecondLamps);
    }

}