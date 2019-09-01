package za.co.jaredfishy.susan.util;

public class ThreadSleeper {

    public static void sleep(long millis){
        try {
            Thread.sleep(millis);
        } catch (Exception err) {
        }
    }
}
