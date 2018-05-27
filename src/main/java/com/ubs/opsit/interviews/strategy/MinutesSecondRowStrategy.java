package com.ubs.opsit.interviews.strategy;

/**
 * Created by jeetu on 5/27/18.
 */
public class MinutesSecondRowStrategy implements ClockStrategy {
    public static final int TOTAL_LAMPS = 4;
    public static final int MINUTES_PER_LAMP = 5;

    @Override
    public String apply(String time) {
        int minutes = Integer.parseInt(time.split(":")[1]);
        StringBuffer sb = new StringBuffer();
        int minutesInd = minutes % MINUTES_PER_LAMP;
        int remainingLamps = TOTAL_LAMPS - minutesInd;

        buildMinutesLamp(sb, minutesInd, "Y");
        buildMinutesLamp(sb, remainingLamps, "O");

        return sb.toString();
    }

    private void buildMinutesLamp(StringBuffer sb, int hourInd, String lampVal) {
        while (hourInd > 0) {
            sb.append(lampVal);
            hourInd--;
        }
    }
}
