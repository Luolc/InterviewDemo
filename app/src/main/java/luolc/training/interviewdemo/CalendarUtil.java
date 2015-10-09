package luolc.training.interviewdemo;

import android.util.Log;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Luo Liangchen on 2015/10/5.
 */
public class CalendarUtil {

    private static final long WEEK_IN_MILLIS = 604800000L;
    private static final Calendar schoolOpen = new GregorianCalendar(2015, 8, 14);

    public static int getCurrentWeek() {
        Calendar rightNow = Calendar.getInstance();
        return getDeltaWeek(rightNow, schoolOpen) + 1;
    }
    public static int getDeltaWeek(Calendar t1, Calendar t2) {
        Log.i("calendar-test", "t1: " + t1.getTime());
        Log.i("calendar-test", "t2: " + t2.getTime());
        long delta = Math.abs(t1.getTimeInMillis() - t2.getTimeInMillis());
        return (int)(delta / WEEK_IN_MILLIS);
    }
}
