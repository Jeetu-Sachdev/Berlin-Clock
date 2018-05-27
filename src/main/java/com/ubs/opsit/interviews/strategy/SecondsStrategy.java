package com.ubs.opsit.interviews.strategy;

/**
 * Created by jeetu on 5/27/18.
 */
public class SecondsStrategy implements ClockStrategy {
    @Override
    public String apply(String time) {
        int seconds = Integer.parseInt(time.split(":")[2]);
        return seconds % 2 == 0 ? "Y" : "O";
    }
}