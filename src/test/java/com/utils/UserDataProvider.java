package com.utils;

import com.domain.User;
import com.exceptions.UserNotFound;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class UserDataProvider {

    private static final Logger LOG = Logger.getLogger(UserDataProvider.class);
    private List<User> validUsers = new ArrayList<User>();
    private String VALID_USER_FILE = "/testData/validUserDetails.csv";
//    private String VALID_USER_FILE = "src\\test\\resources\\testData\\validUsers.csv";
    private String VALID_USER_FILE_HEADER = "##username,password,title,organization";

    FileHelper aFileHelper = new FileHelper();
    ApplicationProperties applicationProps = new ApplicationProperties(ApplicationProperties.APPLICATION_PROP_FILE);


    public UserDataProvider(){
        super();
        if(validUsers==null || validUsers.size()==0){
            processUsers(aFileHelper.readDataFromFile(VALID_USER_FILE));

        }

    }

    private void processUsers(List<String> lines){
        for(String line : lines){
            if(!line.contains("##")) {
                String[] lineSplits = line.split(",");
                User aUser = new User();
                aUser.setUserName(lineSplits[0]);
                aUser.setPassword(lineSplits[1]);
                validUsers.add(aUser);
            }
        }
    }

    private void getValidUsers(){





    }

    public String getPassword(String userName) throws UserNotFound {
        String pwd=null;
        for(User aUser : validUsers){
            if(aUser.getUserName().equals(userName)){
                pwd = aUser.getPassword();
            }
        }
        return pwd;
    }

    /**
     * returns a valid user from the test data
     * @return
     * @throws UserNotFound
     */
    public String getUserName() throws UserNotFound {
        // TODO for any valid user
        // random method
        // valid users lo randomly get 1-4 numbers

        return "shalini@kubecloudsinc.com";
    }

    public String getTableColumn(String page, int columnNumber){
        //TODO read columns data from page-tables file and return column name like valid users, region data etc.
        return "First Name";
    }

    public String getRegionName(String countryName){
        // TODO Same as users data logic get the data from the file
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

    public String getWelcomeMsg(){
        return applicationProps.getWelcomeMsg();
    }

}
