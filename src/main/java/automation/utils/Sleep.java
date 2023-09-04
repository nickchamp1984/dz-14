package automation.utils;

public class Sleep
{
    static public void sleep(long ms){
        try {
            Thread.sleep(ms);
        } catch (Throwable e) { /* Ignore */ }
    }
}
