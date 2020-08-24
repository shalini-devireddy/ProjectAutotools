package com.utils;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ApplicationProperties {

    public static final String APPLICATION_PROP_FILE= "/testData/applicationProperties.cfg";
    private static final Logger LOG = Logger.getLogger(UserDataProvider.class);
    private Properties appProps = new Properties();
    private String WELCOME_MSG_KEY = "welcomeMsg";
    private String LOGIN_PAGE_HEADER_KEY = "loginPageHeader";
    private String EMPLOYEE_TABLE_KEY = "employeesTable";
    private String USERFORM_PAGE_HEADER_KEY="userFormHeader";
    private String USERFORM_SUCCESS_MSG="userSuccessMsg";
    private String REGIONS_TABLE_KEY="tableRegionDetails";
    private String COUNTRIES_TABLE_KEY="tableCountriesDetails";
    private String LOCATIONS_TABLE_KEY="tableLocationsInCountryDetails";
    private String DEPARTMENTS_TABLE_KEY="tableDepartmentsInLocations";

    public ApplicationProperties(String path){
        super();
        try {
            appProps.load(new FileInputStream(getClass().getResource(path).getFile()));
//            appProps.load(new FileInputStream("src\\test\\resources\\testData\\applicationProperties.cfg"));
        } catch (IOException e) {
         LOG.error("Cannot load properties file");
        }
     }

     public String getWelcomeMsg(){
         return appProps.getProperty(WELCOME_MSG_KEY);
     }

    public String getLoginPageHeader(){
        return appProps.getProperty(LOGIN_PAGE_HEADER_KEY);
    }

    public String getEmployeesTableColumn(int columnNumber){
         String columnString = appProps.getProperty(EMPLOYEE_TABLE_KEY);
         return columnString.split(",")[columnNumber-1].trim();
    }

//    public static void main(String[] args) {
//        ApplicationProperties props = new ApplicationProperties("/testData/applicationProperties.cfg");
//        LOG.debug("welcome msgs "+ props.getWelcomeMsg());
//    }
    public String getUserFormHeader(){
         return appProps.getProperty(USERFORM_PAGE_HEADER_KEY);
    }
    public String getUserSuccessMessage(){
         return appProps.getProperty(USERFORM_SUCCESS_MSG);

    }

    public String getRegionsTableColumn(int columnNumber){
        String columnString = appProps.getProperty(REGIONS_TABLE_KEY);
        return columnString.split(",")[columnNumber-1].trim();
    }

    public String getCountriesTableColumn(int columnNumber){
        String columnString = appProps.getProperty(REGIONS_TABLE_KEY);
        return columnString.split(",")[columnNumber-1].trim();
    }
    public String getLocationsTableColumn(int columnNumber){
        String columnString = appProps.getProperty(LOCATIONS_TABLE_KEY);
        return columnString.split(",")[columnNumber-1].trim();
    }

    public String getDepartmentsTableColumn(int columnNumber){
        String columnString = appProps.getProperty(DEPARTMENTS_TABLE_KEY);
        return columnString.split(",")[columnNumber-1].trim();
    }

}
