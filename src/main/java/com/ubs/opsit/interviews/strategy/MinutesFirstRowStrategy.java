package com.ubs.opsit.interviews.strategy;

/**
 * Created by jeetu on 5/27/18.
 */
public class MinutesFirstRowStrategy implements ClockStrategy {
    public static final int TOTAL_LAMPS = 11;
    public static final int MINUTES_PER_LAMP = 5;

    @Override
    public String apply(String time) {
        int minutes = Integer.parseInt(time.split(":")[1]);
        StringBuffer sb = new StringBuffer();

        int minutesInd = minutes / MINUTES_PER_LAMP;

        int remainingLamps = TOTAL_LAMPS - minutesInd;

        buildMinutesLamps(sb, minutesInd);
        buildMinutesRemainingLamp(sb, remainingLamps, "O");
        return sb.toString();
    }

    private void buildMinutesLamps(StringBuffer sb, int minutesInd) {
        int quarterCount = 1;
        while (minutesInd > 0) {
            if (quarterCount % 3 != 0) {
                sb.append("Y");
            } else {
                sb.append("R");
            }
            quarterCount++;
            minutesInd--;
        }
    }

    private void buildMinutesRemainingLamp(StringBuffer sb, int minutesInd, String lampVal) {
        while (minutesInd > 0) {
            sb.append(lampVal);
            minutesInd--;
        }
    }
}
