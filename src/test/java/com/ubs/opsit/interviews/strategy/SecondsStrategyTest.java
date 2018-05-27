package com.ubs.opsit.interviews.strategy;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by jeetu on 5/27/18.
 */
public class SecondsStrategyTest {
    SecondsStrategy unit;
    @Before
    public void setUp() throws Exception {
        unit = new SecondsStrategy();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void shouldReturnOFFLampWhenOddSecondsArePassed(){
        String clock = unit.apply("00:00:01");
        assertThat(clock , is("O"));
    }

    @Test
    public void shouldReturnONLampWhenOddSecondsArePassed(){
        String clock = unit.apply("00:00:24");
        assertThat(clock , is("Y"));
    }

}