package com.company.db;

import com.company.entity.Time;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.swing.text.DateFormatter;
import java.io.*;
import java.lang.reflect.Type;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Database {
    static File file = new File("src/main/resources/times.json");

    public static boolean timeToFile(List<Time> times) {

        try (PrintWriter writer = new PrintWriter(file)) {
            Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();

            gson.toJson(times, writer);
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Time> getTimes() {
        List<Time>times=new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
            Type type=new TypeToken<List<Time>>(){}.getType();
            times= gson.fromJson(reader, type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return times;
    }
}
