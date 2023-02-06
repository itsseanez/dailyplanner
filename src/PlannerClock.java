import java.util.Calendar;
import java.util.Locale;

public class PlannerClock extends Thread {

    public void run() {
        while (true) {
            Calendar calendar = Calendar.getInstance();
            String amPm;
            int hour = calendar.get(Calendar.HOUR);
            int minutes = calendar.get(Calendar.MINUTE);
            int timeOfDay = calendar.get(Calendar.AM_PM);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            int year = calendar.get(Calendar.YEAR);
            if (timeOfDay==0)
                amPm= "A.M.";
            else
                amPm= "P.M.";
            String month = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH);
            System.out.println(month + ", " + day + " " + year + "\t" + hour + ":" + minutes + " " + amPm);
            try {
                Thread.sleep(60000);
            } catch (Exception e) {}
        }
    }
}
