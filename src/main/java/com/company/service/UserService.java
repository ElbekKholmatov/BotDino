package com.company.service;

import com.company.entity.Users;
import com.company.entity.Words;

import java.util.List;
import java.util.Objects;

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


    public Words getWord(String wordId){

        // databasedan shu id ga tegishli wordni topib qaytarish kerak


        return null;
    }


    public boolean addWord(Words words){
        //databasega kelga shu wordni qo'shish kerak
        return false;
    }

    public boolean addUser(Users user){


        return false;
    }


    public Users getUser(String chatId){



        return null;
    }
}
