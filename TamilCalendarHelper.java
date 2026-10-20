package com.tamilclock.widget;

import java.util.Calendar;

public class TamilCalendarHelper {

    private static final String[] DAYS = {
        "ஞாயிறு", "திங்கள்", "செவ்வாய்", "புதன்", "வியாழன்", "வெள்ளி", "சனி"
    };

    private static final String[] TAMIL_MONTHS = {
        "சித்திரை", "வைகாசி", "ஆனி", "ஆடி", "ஆவணி", "புரட்டாசி",
        "ஐப்பசி", "கார்த்திகை", "மார்கழி", "தை", "மாசி", "பங்குனி"
    };

    private static final int[] TRANSITION_DAYS = {
        14, // சித்திரை (Chithirai starts ~Apr 14)
        15, // வைகாசி (Vaikasi starts ~May 15)
        15, // ஆனி (Aani starts ~Jun 15)
        16, // ஆடி (Aadi starts ~Jul 16)
        17, // ஆவணி (Aavani starts ~Aug 17)
        17, // புரட்டாசி (Purattasi starts ~Sep 17)
        18, // ஐப்பசி (Aippasi starts ~Oct 18)
        17, // கார்த்திகை (Karthigai starts ~Nov 17)
        16, // மார்கழி (Margazhi starts ~Dec 16)
        14, // தை (Thai starts ~Jan 14)
        13, // மாசி (Maasi starts ~Feb 13)
        14  // பங்குனி (Panguni starts ~Mar 14)
    };

    public static String getTamilDateString(Calendar cal) {
        int dayOfWeekIndex = cal.get(Calendar.DAY_OF_WEEK) - 1;
        String dayName = DAYS[dayOfWeekIndex];

        int gMonth = cal.get(Calendar.MONTH); // 0=Jan .. 9=Oct .. 11=Dec
        int gDay = cal.get(Calendar.DAY_OF_MONTH);

        int tMonthIndex;
        int tDay;

        if (gMonth == 0) { // Jan
            if (gDay >= 14) { tMonthIndex = 9; tDay = gDay - 13; }
            else { tMonthIndex = 8; tDay = gDay + 16; }
        } else if (gMonth == 1) { // Feb
            if (gDay >= 13) { tMonthIndex = 10; tDay = gDay - 12; }
            else { tMonthIndex = 9; tDay = gDay + 18; }
        } else if (gMonth == 2) { // Mar
            if (gDay >= 14) { tMonthIndex = 11; tDay = gDay - 13; }
            else { tMonthIndex = 10; tDay = gDay + 16; }
        } else if (gMonth == 3) { // Apr
            if (gDay >= 14) { tMonthIndex = 0; tDay = gDay - 13; }
            else { tMonthIndex = 11; tDay = gDay + 18; }
        } else if (gMonth == 4) { // May
            if (gDay >= 15) { tMonthIndex = 1; tDay = gDay - 14; }
            else { tMonthIndex = 0; tDay = gDay + 17; }
        } else if (gMonth == 5) { // Jun
            if (gDay >= 15) { tMonthIndex = 2; tDay = gDay - 14; }
            else { tMonthIndex = 1; tDay = gDay + 17; }
        } else if (gMonth == 6) { // Jul
            if (gDay >= 16) { tMonthIndex = 3; tDay = gDay - 15; }
            else { tMonthIndex = 2; tDay = gDay + 16; }
        } else if (gMonth == 7) { // Aug
            if (gDay >= 17) { tMonthIndex = 4; tDay = gDay - 16; }
            else { tMonthIndex = 3; tDay = gDay + 16; }
        } else if (gMonth == 8) { // Sep
            if (gDay >= 17) { tMonthIndex = 5; tDay = gDay - 16; }
            else { tMonthIndex = 4; tDay = gDay + 15; }
        } else if (gMonth == 9) { // Oct
            if (gDay >= 18) { tMonthIndex = 6; tDay = gDay - 17; }
            else { tMonthIndex = 5; tDay = gDay + 14; }
        } else if (gMonth == 10) { // Nov
            if (gDay >= 17) { tMonthIndex = 7; tDay = gDay - 16; }
            else { tMonthIndex = 6; tDay = gDay + 14; }
        } else { // Dec
            if (gDay >= 16) { tMonthIndex = 8; tDay = gDay - 15; }
            else { tMonthIndex = 7; tDay = gDay + 14; }
        }

        return dayName + " - " + TAMIL_MONTHS[tMonthIndex] + " " + tDay;
    }
}
