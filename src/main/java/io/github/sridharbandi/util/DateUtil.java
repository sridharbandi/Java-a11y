package io.github.sridharbandi.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static String FORMAT = "dd MMM yyyy HH:mm:ss";

    public static String getDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT);
        return dateFormat.format(new Date());
    }
}
