/*
 * Copyright (c) 2016 libing. All rights reserved.
 */
package cn.com.tcsl.gregoriancalendar;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * <P>
 * Description:制作当日所在月的日历
 * </p>
 * @author libing
 * @version 1.0
 * @Date 2016年6月3日上午9:47:56
 */
public class CalendarTest {

    public static void main(String[] args) {
        GregorianCalendar d = new GregorianCalendar();

        int today = d.get(Calendar.DAY_OF_MONTH);
        int month = d.get(Calendar.MONTH);
        // System.out.println(today + "," + month);

        d.set(Calendar.DAY_OF_MONTH, 1);
        int weekday = d.get(Calendar.DAY_OF_WEEK);
        // System.out.println(weekday);

        int firstDayOfWeek = d.getFirstDayOfWeek();
        // System.out.println(firstDayOfWeek);
        int indent = 0;
        while (weekday != firstDayOfWeek) {
            indent++;
            d.add(Calendar.DAY_OF_MONTH, -1);
            // System.out.println(d.get(Calendar.DAY_OF_MONTH));
            weekday = d.get(Calendar.DAY_OF_WEEK);
        }

        String[] weekdayNames = new DateFormatSymbols().getShortWeekdays();
        do {
            System.out.printf("%4s", weekdayNames[weekday]);
            d.add(Calendar.DAY_OF_MONTH, 1);
            weekday = d.get(Calendar.DAY_OF_WEEK);
        } while (weekday != firstDayOfWeek);
        System.out.println();

        for (int i = 0; i < indent; i++) {
            System.out.print("    ");
            d.set(Calendar.DAY_OF_MONTH, 1);
        }

        do {
            int day = d.get(Calendar.DAY_OF_MONTH);
            System.out.printf("%3d", day);
            if (day == today) {
                System.out.print("*");
            } else {
                System.out.print(" ");
            }
            d.add(Calendar.DAY_OF_MONTH, 1);
            weekday = d.get(Calendar.DAY_OF_WEEK);
            if (weekday == firstDayOfWeek) {
                System.out.println();
            }
        } while (d.get(Calendar.MONTH) == month);

        if (weekday != firstDayOfWeek) {
            System.out.println();
        }
    }

}
