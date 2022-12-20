package com.company.threads;

import com.company.db.Database;
import com.company.entity.Time;

import java.time.LocalDateTime;
import java.util.List;

public class MyThread extends Thread {
    static List<Time> times= Database.getTimes();
    private final String name;

    public MyThread(String name) {
        this.name = name;
    }

    public void run() {


            while (true){


                LocalDateTime dateTime;
                for (Time time : times) {
                    dateTime= LocalDateTime.parse(time.getTime());
                    if (dateTime.isAfter(LocalDateTime.now())){

                    }
                }

            }

    }


}
