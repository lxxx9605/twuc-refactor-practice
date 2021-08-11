package com.twu.refactoring;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

public class DateParser {
    private final String dateAndTimeString;
    private static final HashMap<String, TimeZone> KNOWN_TIME_ZONES = new HashMap<String, TimeZone>();

    static {
        KNOWN_TIME_ZONES.put("UTC", TimeZone.getTimeZone("UTC"));
    }

    /**
     * Takes a date in ISO 8601 format and returns a date
     *
     * @param dateAndTimeString - should be in format ISO 8601 format
     *                          examples -
     *                          2012-06-17 is 17th June 2012 - 00:00 in UTC TimeZone
     *                          2012-06-17TZ is 17th June 2012 - 00:00 in UTC TimeZone
     *                          2012-06-17T15:00Z is 17th June 2012 - 15:00 in UTC TimeZone
     */
    public DateParser(String dateAndTimeString) {
        this.dateAndTimeString = dateAndTimeString;
    }

    public void invalidNumber (int start, int end, String type) {
        try {
            Integer.parseInt(dateAndTimeString.substring(start, end));
        } catch (StringIndexOutOfBoundsException e) {
            throw new IllegalArgumentException(type + " string is less than " + (end - start) + " characters");
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(type + " is not an integer");
        }
    }

    public Date parse() {
        int year, month, date, hour, minute;

        invalidNumber(0, 4, "Year");
        year = Integer.parseInt(dateAndTimeString.substring(0, 4));
        if (year < 2000 || year > 2012) {
            throw new IllegalArgumentException("Year cannot be less than 2000 or more than 2012");
        }

        invalidNumber(5, 7, "Month");
        month = Integer.parseInt(dateAndTimeString.substring(5, 7));
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Month cannot be less than 1 or more than 12");
        }

        invalidNumber(8, 10, "Date");
        date = Integer.parseInt(dateAndTimeString.substring(8, 10));
        if (date < 1 || date > 31) {
            throw new IllegalArgumentException("Date cannot be less than 1 or more than 31");
        }

        switch (dateAndTimeString.charAt(11)) {
            case 'Z':
                hour = 0;
                minute = 0;
                break;
            default:
                invalidNumber(11, 13, "Hour");
                hour = Integer.parseInt(dateAndTimeString.substring(11, 13));
                if (hour < 0 || hour > 23) {
                    throw new IllegalArgumentException("Hour cannot be less than 0 or more than 23");
                }
                invalidNumber(14, 16, "Minute");
                minute = Integer.parseInt(dateAndTimeString.substring(14, 16));
                if (minute < 0 || minute > 59) {
                    throw new IllegalArgumentException("Minute cannot be less than 0 or more than 59");
                }
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
        calendar.set(year, month - 1, date, hour, minute, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
}
