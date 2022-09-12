package com.pluralsight.calcengine;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class GetHumanlyReadableFormat {

    public static final TimeZone TIME_ZONE = TimeZone.getTimeZone("American/Eastern");
    public static final ZoneId TIME_ZONE_ID = ZoneId.of("GMT");
    private static final String FORMAT_DATE = "MMMM d, yyyy";

    public static void main(String[] args) {
        GetHumanlyReadableFormat gHRF = new GetHumanlyReadableFormat();

//        System.out.println(gHRF.getHumanlyReadableFormat(new Date()));
//        System.out.println(gHRF.getHumanlyReadableFormat(Instant.now()));
//        System.out.println(gHRF.getHumanlyReadableFormat(-1990137600000L));

        Calendar calendar = Calendar.getInstance();
        calendar.set(1912, Calendar.JUNE, 23);
        System.out.println(getHumanlyReadableFormat(calendar));
    }


    public String gHRF(final Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATE);
        sdf.setTimeZone(TIME_ZONE);
        return sdf.format(date);
    }
    public static String getHumanlyReadableFormat(final Instant instant) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_DATE).withZone(TIME_ZONE_ID);
        String formattedInstant = formatter.format(instant);
        return formattedInstant;
    }

    public static String getHumanlyReadableFormat(final Long millisecondsSinceTheEpoch) {
        Instant instant = Instant.ofEpochMilli(millisecondsSinceTheEpoch);
        return getHumanlyReadableFormat(instant);
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy").withZone(TIME_ZONE_ID);
//        String formattedInstant = formatter.format(instant);
//        return formattedInstant;
    }

    public static String getHumanlyReadableFormat(final Calendar calendar) {

        //SimpleDateFormat format = new SimpleDateFormat("MMMM dd, yyyy");
        //String date = format.format(calendar.getTime());
        return getHumanlyReadableFormat(calendar.getTimeInMillis());
    }
}

/*
    Example Method Signatures:
public String getHumanlyReadableFormat(final Date date);
public String getHumanlyReadableFormat(final Instant instant);
public String getHumanlyReadableFormat(final Long millisecondsSinceTheEpoch);
public String getHumanlyReadableFormat(final Calendar calendar);
        Example Output:
        assertEquals("December 9, 1906", getHumanlyReadableFormat(-1990137600000L));
        Calendar calendar = Calendar.getInstance();
        calendar.set(1912, 6, 23);
        assertEquals("June 23, 1912", getHumanlyReadableFormat(calendar));

 */