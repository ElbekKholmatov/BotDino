package com.company.service;

import com.company.entity.Users;
import com.company.entity.Words;

import java.sql.*;
import java.util.List;
import java.util.Objects;

import static com.company.container.ComponentContainer.*;

public class UserService {
    private static UserService instance;


    private UserService() {
    }

    public static UserService getInstance() {
        if (Objects.isNull(instance)){
            instance=new UserService();
        }
        return instance;
    }


    public List<Words> getAllMyWords(String chatId){

        // databasedan shu chatId ga tegishli barcha so'zlarni topib qaytarish kerak


        return null;
    }


    public Words getWord(Long wordId){

        // databasedan shu id ga tegishli wordni topib qaytarish kerak


        return null;
    }
    public Words getWord(String word){

        // databasedan shu id ga tegishli wordni topib qaytarish kerak


        return null;
    }


    public boolean addWord(Words words) {
        //databasega kelga shu wordni qo'shish kerak
        WorkWithDB.addWord(words);

        return false;
    }

    public boolean addUser(Users user) {

        if (Objects.nonNull(user)) ;
        return WorkWithDB.addUser(user);
    }


    public static Users getUser(String chatId) {
        Users users = new Users();
        String query = """
                select * from Users where chat_id=?;
                """;
        try (Connection connection = DriverManager.getConnection(URL_DB, USER_DB, PASSWORD_DB);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, Long.parseLong(chatId));

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
