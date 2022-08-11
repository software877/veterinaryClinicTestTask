package com.example.clinic;

import static org.junit.Assert.*;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class WorkingHoursTest {

    @Test
    public void isWorkingHoursInTime() {
        WorkingHours workingHours = new WorkingHours();
        String givenTime = "M-F 9:00 - 18:00";

        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.HOUR_OF_DAY, 10);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date date = calendar.getTime();

        Boolean result = workingHours.isWorkingHoursInTime(givenTime, date);
        assertEquals(result, true);
    }


    @Test
    public void workingHoursOutOfTime() {
        WorkingHours workingHours = new WorkingHours();
        String givenTime = "M-F 9:00 - 18:00";

        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.HOUR_OF_DAY, 8);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date date = calendar.getTime();

        Boolean result = workingHours.isWorkingHoursInTime(givenTime, date);
        assertEquals(result, false);
    }

}