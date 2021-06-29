package cn.lee.boot;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author ：lix492
 * 2021/5/14
 */

public class DateTest {
    public static String parseHourAndMinute(final Date dateToBeFormatted) {
        final SimpleDateFormat dateFormatter = new SimpleDateFormat("HH:mm", Locale.US);
        return dateFormatter.format(dateToBeFormatted);
    }

    public static void main(String[] args) {
        System.out.println(parseHourAndMinute(new Date()));
    }
}
