package project.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Utils {

    private static String[] months = {
            "January",
            "February",
            "March",
            "April",
            "May",
            "June",
            "July",
            "August",
            "September",
            "October",
            "November",
            "December"
    };

    public static Calendar getCalendar(int day, int month, int year) {
        Calendar date = Calendar.getInstance();

        date.set(Calendar.YEAR, year);

        date.set(Calendar.MONTH, month+1);

        date.set(Calendar.DAY_OF_MONTH, day);

        return date;
    }

    public static String calendarToString(Calendar cal)
    {
        cal.add(Calendar.DATE, 1);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(cal.getTime());

        String formatted = format.format(cal.getTime());
        String[] calendarSplit = formatted.split("-");
        return months[Integer.valueOf(calendarSplit[1])] + " " + calendarSplit[2] + ", " + calendarSplit[0];


    }
}
