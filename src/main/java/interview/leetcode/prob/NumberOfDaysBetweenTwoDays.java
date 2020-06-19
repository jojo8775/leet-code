package interview.leetcode.prob;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class NumberOfDaysBetweenTwoDays {
    public int daysBetweenDates(String date1, String date2) {
    	return Math.abs((int) ChronoUnit.DAYS.between(LocalDate.parse(date1), LocalDate.parse(date2)));
    }
}
