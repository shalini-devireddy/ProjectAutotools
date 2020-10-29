package com.utils;

import com.domain.Regions;
import com.domain.User;
import io.cucumber.core.internal.gherkin.deps.com.google.gson.JsonObject;
import jdk.nashorn.internal.parser.JSONParser;
import jdk.nashorn.internal.parser.Parser;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserDataProvider {

    private static final Logger LOG = Logger.getLogger(UserDataProvider.class);
    private List<User> validUsers = new ArrayList<User>();
    private List<Regions>regions= new ArrayList<>();
    private String VALID_USER_FILE = "/testData/validUserDetails.csv";
    private String VALID_JSON_USER_FILE="/testData/validUsers.json";
    private String VALID_USER_FILE_HEADER = "##username,password,title,organization";
    JSONParser aParser;
    FileHelper aFileHelper = new FileHelper();

    public UserDataProvider(){
        super();
        if(validUsers==null || validUsers.size()==0){
            processUsers(aFileHelper.readDataFromFile(VALID_USER_FILE));
        }
    }
    public UserDataProvider(String fileType ){
        if(validUsers ==null || getValidUser().size()==0){
            fileType=VALID_JSON_USER_FILE;
            processJsonUsers(aFileHelper.readDataFromFile((fileType)));
        }
    }

    private void processUsers(List<String> lines){
        for(String line : lines){
            if(!line.contains("##")) {
                String[] lineSplits = line.split(",");
                User aUser = new User();
//                LOG.debug(lineSplits[0]+" "+ lineSplits[1]+" "+ lineSplits[2]);
                aUser.setUserName(lineSplits[0]);
                aUser.setPassword(lineSplits[1]);
                aUser.setNewPwd(lineSplits[2]);
                validUsers.add(aUser);
            }
        }
    }

    private void processJsonUsers(List<String> lines){

        Object obj= Parser.parse(lines);
        aParser= new JSONParser();
        JsonObject jsonObject=(JsonObject) obj;
    }


    public String getTableColumn(String page, int columnNumber){
        //TODO read columns data from page-tables file and return column name like valid users, region data etc.
        return "First Name";
//
    }

    public String getRegionName(String countryName){
        // TODO Same as users data logic get the data from the file
        Regions regionName=null;
        for (Regions aTable : regions){

        }
            //if (aTable.getName())
        return "Europe";
    }
    // TODO method Same as users data logic get the data from the file
    public int totalCountries(String regionName){
        return 0;
    }

    public void updateCredentials(String userName, String pwd){

        String validUsersFile="src\\test\\resources\\testData\\validUserDetails.csv";
        // = appProps.getValidUserFiles();
        // TODO get the above value from application property file
        LOG.debug("Inside the update credentials");
        for(User aUser: validUsers){
            if(aUser.getUserName().equals(userName)){
                LOG.debug("found user name");
                aUser.setPassword(pwd);
            }
        }
        List<String> linesToBeWritten = new ArrayList<String>();
        linesToBeWritten.add(VALID_USER_FILE_HEADER);
        for(User aUser:validUsers){
            linesToBeWritten.add(aUser.getUserName()+","+aUser.getPassword());
        }
        LOG.debug("Total lines in new file: "+linesToBeWritten);
        aFileHelper.writeDataIntoFile(validUsersFile, linesToBeWritten);
    }

    public void flipPasswords(String userName){

        String validUsersFile="src\\test\\resources\\testData\\validUserDetails.csv";
        // = appProps.getValidUserFiles();
        // TODO get the above value from application property file
        LOG.debug("Inside the update credentials");
        for(User aUser: validUsers){
            if(aUser.getUserName().equals(userName)){
                LOG.debug("found user name");
                String currentPwd = aUser.getPassword();
                aUser.setPassword(aUser.getNewPwd());
                aUser.setNewPwd(currentPwd);
            }
        }
        List<String> linesToBeWritten = new ArrayList<String>();
        linesToBeWritten.add(VALID_USER_FILE_HEADER);
        for(User aUser:validUsers){
            linesToBeWritten.add(aUser.getUserName()+","+aUser.getPassword()+","+aUser.getNewPwd());
        }
        LOG.debug("Total lines in new file: "+linesToBeWritten);
        aFileHelper.writeDataIntoFile(validUsersFile, linesToBeWritten);
    }

    public User getValidUser(){

        return validUsers.get(new Random().nextInt(validUsers.size()));
    }

    public User getValidUser(String userName){
        User returnUser=null;
        for (User aUser : validUsers){
            if(aUser.getUserName().equals(userName)){
                returnUser = aUser;
                break;
            }
        }
        return returnUser;
    }

    public void getRegionsTableColumn(){

    }

    public static void main(String[] args) {
        UserDataProvider data = new UserDataProvider();
        User user = data.getValidUser();
        LOG.debug(user.getUserName()+ " "+user.getPassword());
    }

}
