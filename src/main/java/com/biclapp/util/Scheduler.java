package com.biclapp.util;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class Scheduler {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
    private static final int TIME_HEART_BEAT = 1000 * 60; // 1 MINUTO

    @Scheduled(fixedDelay = TIME_HEART_BEAT)
    // @Scheduled(cron = "0 */1 * ? * *", zone = "America/Lima") // prueba
    public void cronHeartBeat() {
        Date date = new Date();
        String strDate = dateFormat.format(date);
        System.out.println("date: " + strDate);
    }
}
