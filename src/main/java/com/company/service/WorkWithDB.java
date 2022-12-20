package com.company.service;


import com.company.entity.Users;
import com.company.entity.Words;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.company.container.ComponentContainer.*;

public class WorkWithDB {


    public static Words words = new Words();

    public static Words getWord(String wordId) {

        String query = """
                select * from Words where id=?;
                """;
        try (Connection connection = DriverManager.getConnection(URL_DB, USER_DB, PASSWORD_DB);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, Long.parseLong(wordId));

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

        StringBuilder stringBuilder = new StringBuilder();
        for (String s : words.getTranslation()) {
            stringBuilder.append(s).append("##");
        }
        String query = """
                insert into Words("user_chat_Id", "wordEng", translated,definition) values
                (?,?,?,?);
                """;
        try (Connection connection = DriverManager.getConnection(URL_DB, USER_DB, PASSWORD_DB);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, words.getUserChatId());
            preparedStatement.setString(2, words.getWord());
            preparedStatement.setString(3, String.valueOf(stringBuilder));
            preparedStatement.setString(4, String.valueOf(words.getDefinition()));

            int executeUpdate = preparedStatement.executeUpdate();
            System.out.println("result = " + executeUpdate);
            if (executeUpdate == 1) return true;

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return false;
    }


//    public static void getUser(String chatId) {
//        String query = """
//                select * from userss where chat_id='?';
//                """;
//        try (Connection connection = DriverManager.getConnection(URL_DB, USER_DB, PASSWORD_DB);
//             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//            preparedStatement.setString(1, chatId);
//
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            while (resultSet.next()) {
//                chatId = resultSet.getString(1);
//                user = resultSet.getString(2);
//                chatId = resultSet.getString(3);
//                chatId = resultSet.getString(4);
//            }
//            System.out.println("result = " + result);
//            if (result == 1) return true;
//
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//
//        }
//        return false;
//    }
}
