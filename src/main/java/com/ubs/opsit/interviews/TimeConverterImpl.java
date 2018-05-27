package com.ubs.opsit.interviews;

import com.ubs.opsit.interviews.exception.BerlinClockException;
import com.ubs.opsit.interviews.strategy.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeConverterImpl implements TimeConverter {
    private static final Logger LOG = LoggerFactory.getLogger(TimeConverterImpl.class);
    public static final String SPECIAL_TIME = "24:00:00";
    private ClockStrategy secondsStrategy;
    private ClockStrategy hoursFirstRowStrategy;
    private ClockStrategy hoursSecondRowStrategy;
    private ClockStrategy minutesFirstRowStrategy;
    private ClockStrategy minutesSecondRowStrategy;

    public TimeConverterImpl() {
        secondsStrategy = new SecondsStrategy();
        hoursFirstRowStrategy = new HoursFirstRowStrategy();
        hoursSecondRowStrategy = new HoursSecondRowStrategy();
        minutesFirstRowStrategy = new MinutesFirstRowStrategy();
        minutesSecondRowStrategy = new MinutesSecondRowStrategy();
    }

    public TimeConverterImpl(ClockStrategy secondsStrategy, ClockStrategy hoursFirstRowStrategy,
                             ClockStrategy hoursSecondRowStrategy, ClockStrategy minutesFirstRowStrategy,
                             ClockStrategy minutesSecondRowStrategy) {
        this.secondsStrategy = secondsStrategy;
        this.hoursFirstRowStrategy = hoursFirstRowStrategy;
        this.hoursSecondRowStrategy = hoursSecondRowStrategy;
        this.minutesFirstRowStrategy = minutesFirstRowStrategy;
        this.minutesSecondRowStrategy = minutesSecondRowStrategy;
    }

    @Override
    public String convertTime(String aTime) {
        validateTime(aTime);

        StringBuffer sb = new StringBuffer();
        applyClockStrategy(aTime, sb, secondsStrategy).append("\n");
        applyClockStrategy(aTime, sb, hoursFirstRowStrategy).append("\n");
        applyClockStrategy(aTime, sb, hoursSecondRowStrategy).append("\n");
        applyClockStrategy(aTime, sb, minutesFirstRowStrategy).append("\n");
        applyClockStrategy(aTime, sb, minutesSecondRowStrategy);

        return sb.toString();
    }

    private void validateTime(String aTime) {
        try {
            String[] time = aTime.split(":");
            SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");
            sdf1.setLenient(false);
            Date date = sdf1.parse(aTime);
            LOG.info("Valid time" + date);
        } catch (Exception ex) {
            if (!isSpecialTimeScenario(aTime)) {
                LOG.error("Invalid Time" + aTime);
                throw new BerlinClockException("Invalid Time" + aTime);
            }
        }
    }

    private boolean isSpecialTimeScenario(String aTime) {
        return aTime != null && aTime.equals(SPECIAL_TIME);
    }

    private StringBuffer applyClockStrategy(String aTime, StringBuffer sb, ClockStrategy clockStrategy) {
        sb.append(clockStrategy.apply(aTime));
        return sb;
    }
}
