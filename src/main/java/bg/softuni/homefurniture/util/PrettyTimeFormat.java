package bg.softuni.homefurniture.util;

import org.ocpsoft.prettytime.PrettyTime;

import java.time.LocalDateTime;
import java.util.Date;

public class PrettyTimeFormat {
    public static String format(LocalDateTime pastDateTime) {
        PrettyTime prettyTime = new PrettyTime();
        Date date = java.sql.Timestamp.valueOf(pastDateTime);

        return prettyTime.format(date);
    }

    public static void main(String[] args) {
        LocalDateTime pastDateTime = LocalDateTime.now();
        String timeAgo = format(pastDateTime);
        System.out.println(timeAgo);
    }
}
