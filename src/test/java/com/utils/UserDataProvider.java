package com.utils;

import com.domain.User;
import com.exceptions.UserNotFound;

import java.util.ArrayList;
import java.util.List;

public class UserDataProvider {

    private List<User> allValidUsers=new ArrayList<User>();

    public List<User> getValidUsers(){
        // TODO read csv file and convert them into user object and return the list
        //  set it to the variable allValidUsers
        return null;
    }

    public String getPassword(String userName) throws UserNotFound {
        // TODO
        // if valid users is empty
        // call getValidUsers and populate the list users variable
        // search valid users list and get the passwrod
        return "Testing1";
    }
}
