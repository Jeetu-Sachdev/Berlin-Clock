package com.ubs.opsit.interviews.strategy;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by jeetu on 5/27/18.
 */
public class MinutesSecondRowStrategyTest {
    public static final String FIRST_QUARTER_CLOCK = "YYYO";
    public static final String SECOND_QUARTER_CLOCK = "YYOO";
    public static final String THIRD_QUARTER_CLOCK = "YOOO";
    public static final String FOURTH_QUARTER_CLOCK = "YYYY";
    public static final String FIRST_QUARTER_TIME = "00:08:00";
    public static final String SECOND_QUARTER_TIME = "00:17:00";
    public static final String THIRD_QUARTER_TIME = "00:41:00";
    public static final String FOURTH_QUARTER_TIME = "00:59:00";
    MinutesSecondRowStrategy unit;

    @Before
    public void setUp() throws Exception {
        unit = new MinutesSecondRowStrategy();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void shouldReturnBerlinClockAtFirstQuarterMinutesWhenFirstQuarterMinutesArePassed() {
        String clock = unit.apply(FIRST_QUARTER_TIME);
        assertThat(clock, is(FIRST_QUARTER_CLOCK));
    }


    @Test
    public void shouldReturnBerlinClockAtSecondQuarterMinutesWhenSecondQuarterMinutesArePassed() {
        String clock = unit.apply(SECOND_QUARTER_TIME);
        assertThat(clock, is(SECOND_QUARTER_CLOCK));
    }


    @Test
    public void shouldReturnBerlinClockAtThirdQuarterMinutesWhenThirdQuarterMinutesArePassed() {
        String clock = unit.apply(THIRD_QUARTER_TIME);
        assertThat(clock, is(THIRD_QUARTER_CLOCK));
    }


    @Test
    public void shouldReturnBerlinClockAtFourthQuarterMinutesWhenFourthQuarterMinutesArePassed() {
        String clock = unit.apply(FOURTH_QUARTER_TIME);
        assertThat(clock, is(FOURTH_QUARTER_CLOCK));
    }

}