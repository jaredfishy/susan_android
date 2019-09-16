package za.co.jaredfishy.susan.util;

import java.time.LocalDateTime;
import java.util.Date;

public class TimestampUtil {

    public static String getTimestampAsString(){
        return LocalDateTime.now().toString();
    }

}
