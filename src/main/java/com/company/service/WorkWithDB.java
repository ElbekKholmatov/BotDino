package com.company.service;


import com.company.entity.Users;
import com.company.entity.Words;


import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.company.container.ComponentContainer.*;

public class WorkWithDB {


    public static void main(String[] args) {


        ArrayList<String> definition = new ArrayList<>();
        ArrayList<String> translation = new ArrayList<>();
        ArrayList<String> exam = new ArrayList<>();

        Words words = new Words("1521", "book", translation, definition, exam, false);
        System.out.println("getWord(\"1\") = " + getWord("1"));
        System.out.println("getUser(\"1521\") = " + getUser("11655"));
//        System.out.println("addUser(new Users(\"656421\",\"efrefg\",\"r4fgtb\",true)) = " + addUser(new Users("656421", "efrefg", "r4fgtb", true)));
        System.out.println("addWord(words) = " + addWord(words));
    }

    public static Words words = new Words();

    public static Words getWord(String wordId) {

        String query = """
                select * from wordss where id=?;
                """;
        try (Connection connection = DriverManager.getConnection(URL_DB, USER_DB, PASSWORD_DB);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, Integer.parseInt(wordId));

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                words.setId(resultSet.getString(1));
                words.setUserChatId(resultSet.getString(2));
                words.setWord(resultSet.getString(3));
                words.setDeleted(resultSet.getBoolean(4));
                words.setTranslation(List.of(resultSet.getString(5)));
                words.setDefinition(List.of(resultSet.getString(6)));
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return words;

    }

    public static boolean addUser(Users user) {

        String query = """
                insert into Users(chat_id, username, firstname)
                values  ( ?,?,?);""";
        try (Connection connection = DriverManager.getConnection(URL_DB, USER_DB, PASSWORD_DB);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, user.getChatId());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getName());

            int result = preparedStatement.executeUpdate();
            System.out.println("result = " + result);
            if (result == 1) return true;


        } catch (SQLException e) {
            e.printStackTrace();

        }
        return false;
    }

    public static boolean addWord(Words words) {

        StringBuilder stringtTranslation = new StringBuilder();
        StringBuilder stringSinonim = new StringBuilder();
        StringBuilder stringDefinition = new StringBuilder();

        for (String s : words.getTranslation()) {
            stringtTranslation.append(s).append("##");
        }
        for (String s :words.getSynonym()) {
            stringSinonim.append(s).append("##");
        }
        for (String s : words.getDefinition()) {
            stringDefinition.append(s).append("##");
        }
        String query = """
                insert into Words("user_chat_Id", "wordEng", translated,definition,sinonim,is_deleted) values
                (?,?,?,?,?,?);
                """;
        try (Connection connection = DriverManager.getConnection(URL_DB, USER_DB, PASSWORD_DB);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, words.getUserChatId());
            preparedStatement.setString(2, words.getWord());
            preparedStatement.setString(3, String.valueOf(stringtTranslation));
            preparedStatement.setString(4, String.valueOf(stringDefinition));
            preparedStatement.setString(5,String.valueOf(stringSinonim));

            int executeUpdate = preparedStatement.executeUpdate();
            System.out.println("result = " + executeUpdate);
            if (executeUpdate == 1) return true;

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return false;
    }

    public static Users getUser(String chatId) {
        Users users = new Users();
        String query = """
                select * from userss where chat_id=?;
                """;
        try (Connection connection = DriverManager.getConnection(URL_DB, USER_DB, PASSWORD_DB);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, chatId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                users.setChatId(resultSet.getString(1));
                users.setName(resultSet.getString(2));
                users.setUsername(resultSet.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return users;
    }


}
