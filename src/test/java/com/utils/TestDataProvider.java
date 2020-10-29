package com.utils;

import com.domain.User;
import org.apache.log4j.Logger;

import java.util.Random;

public class TestDataProvider {

    private static final Logger LOG = Logger.getLogger(UserDataProvider.class);
    RegionsDataProvider regionsDataProvider =
            new RegionsDataProvider();
    UserDataProvider userDataProvider = new UserDataProvider();

    public TestDataProvider(){
        super();
    }
    public String[] getCountriesInRegion(String regionName){
        return regionsDataProvider.countriesInRegion(regionName);
    }
    public int countCountries(String regionName){
        return regionsDataProvider.countCountries(regionName);
    }
    public User getValidUser()
    {
        return userDataProvider.getValidUser();
    }
    public User getValidUser(String userName)
    {
        return userDataProvider.getValidUser(userName);
    }
    public void flipPasswords(String userName){
        userDataProvider.flipPasswords(userName);
    }

}
