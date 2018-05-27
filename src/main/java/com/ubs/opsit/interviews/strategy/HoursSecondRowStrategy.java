package com.ubs.opsit.interviews.strategy;

/**
 * Created by jeetu on 5/27/18.
 */
public class HoursSecondRowStrategy implements ClockStrategy {
    public static final int TOTAL_LAMPS = 4;
    public static final int HOURS_PER_LAMP = 5;

    @Override
    public String apply(String time) {
        int hours = Integer.parseInt(time.split(":")[0]);
        StringBuffer sb = new StringBuffer();
        int hourInd = hours % HOURS_PER_LAMP;
        int remainingLamps = TOTAL_LAMPS - hourInd;

        buildHoursLamp(sb, hourInd, "R");
        buildHoursLamp(sb, remainingLamps, "O");

        return sb.toString();
    }

    private void buildHoursLamp(StringBuffer sb, int hourInd, String lampVal) {
        while (hourInd > 0) {
            sb.append(lampVal);
            hourInd--;
        }
    }
}
