package learn.schedule;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Main {

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args){
        Date now = new Date();
        String dateStr = simpleDateFormat.format(now);
        System.out.println(dateStr);

        String[] times = dateStr.split(" ");
        String newDateStr = times[0]+" "+"00:00:00";
        System.out.println(newDateStr);
        try {
            Date date = simpleDateFormat.parse(newDateStr);
            System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
