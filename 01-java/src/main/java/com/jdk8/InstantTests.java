package com.jdk8;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class InstantTests {
    public static void main(String[] args) {
        //时间戳
        Instant now = Instant.now();
        System.out.println(now);
        Instant instant = now.plusMillis(TimeUnit.HOURS.toMillis(8));
        System.out.println(instant);

        //日期
        LocalDate now1 = LocalDate.now();
        System.out.println(now1);

        //时间
        LocalTime now2 = LocalTime.now();
        System.out.println(now2);

        //日期+时间
        LocalDateTime now3 = LocalDateTime.now();
        System.out.println(now3);

        //时区+日期+时间
        ZonedDateTime now4 = ZonedDateTime.now();
        System.out.println(now4);
        ZoneId zone = now4.getZone();
        System.out.println(zone);

        //字符串转日期时间
        String dateStr="2022/10/28 14:20:15";
        LocalDateTime parse = LocalDateTime.parse(dateStr,
                DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
        System.out.println(parse);

    }
}
