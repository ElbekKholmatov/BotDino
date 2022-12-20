package com.company.threads;

import java.time.LocalDateTime;

public class SendMessageThread extends Thread{
    @Override
    public void run() {
        LocalDateTime localDateTime=LocalDateTime.now().plusSeconds(5);
        LocalDateTime localDateTime1=LocalDateTime.now();
        while (localDateTime1.isBefore(localDateTime)){
            localDateTime1=LocalDateTime.now();
        }
        if (localDateTime.isBefore(localDateTime1)){
            System.out.println("10 sec uti");
        }
    }
}
