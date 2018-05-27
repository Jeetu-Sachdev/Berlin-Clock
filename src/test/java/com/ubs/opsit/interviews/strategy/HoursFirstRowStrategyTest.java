package com.ubs.opsit.interviews.strategy;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by jeetu on 5/27/18.
 */
public class HoursFirstRowStrategyTest {
    public static final String MIDNIGHT_CLOCK = "OOOO";
    public static final String MORNING_6AM_CLOCK = "ROOO";
    public static final String AFTERNOON_2PM_CLOCK = "RROO";
    public static final String EVENING_7PM_CLOCK = "RRRO";
    public static final String MIDNIGHT_TIME = "00:00:00";
    public static final String MORNING_6AM_TIME = "06:00:00";
    public static final String AFTERNOON_2PM_TIME = "14:00:00";
    public static final String EVENING_7PM_TIME = "19:00:00";
    HoursFirstRowStrategy unit;

    @Before
    public void setUp() throws Exception {
        unit = new HoursFirstRowStrategy();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void shouldReturnBerlinClockHoursFirstRowWhenHoursAtMidnightArePassed(){
        String clock = unit.apply(MIDNIGHT_TIME);
        assertThat(clock, is(MIDNIGHT_CLOCK));
    }


    @Test
    public void shouldReturnBerlinClockHoursFirstRowWhenHoursAtMorningArePassed(){
        String clock = unit.apply(MORNING_6AM_TIME);
        assertThat(clock, is(MORNING_6AM_CLOCK));
    }


    @Test
    public void shouldReturnBerlinClockHoursFirstRowWhenHoursAtAfternoonArePassed(){
        String clock = unit.apply(AFTERNOON_2PM_TIME);
        assertThat(clock, is(AFTERNOON_2PM_CLOCK));
    }


    @Test
    public void shouldReturnBerlinClockHoursFirstRowWhenHoursAtEveningArePassed(){
        String clock = unit.apply(EVENING_7PM_TIME);
        assertThat(clock, is(EVENING_7PM_CLOCK));
    }



}