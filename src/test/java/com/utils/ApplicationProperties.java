package com.utils;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ApplicationProperties {

    public static final String APPLICATION_PROP_FILE= "/testData/applicationProperties.cfg";
    private static final Logger LOG = Logger.getLogger(ApplicationProperties.class);
    private Properties appProps = new Properties();

    private String LOGIN_PAGE_HEADER_KEY = "loginPageHeader";
    private String EMPLOYEE_TABLE_KEY = "employeesTable";
    private String WELCOME_MSG_KEY = "welcomeMsg";
    private String USERFORM_PAGE_HEADER_KEY="userFormHeader";
    private String USERFORM_SUCCESS_MSG="userSuccessMsg";
    private String REGIONS_TABLE_KEY="tableRegionDetails";
    private String COUNTRIES_TABLE_KEY="tableCountriesDetails";
    private String LOCATIONS_TABLE_KEY="tableLocationsInCountryDetails";
    private String DEPARTMENTS_TABLE_KEY="tableDepartmentsInLocations";
    private String ERROR_MSG_KEY="userErrorMsg";
    private String FORM_ERROR_KEY="formError";
    private String FIRSTNAME_ERROR_KEY="firstNameError";
    private String LASTNAME_ERROR_KEY="lastNameError";
    private String EUROPE_KEY="Europe";
    private String ASIA_KEY="Asia";
    private String AMERICAS_KEY="Americas";
    private String MIDDLEEASTANDAFRICA_KEY="MiddleEastandAfrica";

     public ApplicationProperties(){
        super();
        try {
         appProps.load(new FileInputStream(getClass().getResource(APPLICATION_PROP_FILE).getFile()));
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
    public String getErrorMsg(){
         return appProps.getProperty(ERROR_MSG_KEY);
    }

//    public static void main(String[] args) {
//        ApplicationProperties props = new ApplicationProperties("/testData/applicationProperties.cfg");
//        LOG.debug("welcome msgs "+ props.getWelcomeMsg());
//    }

    //    public static void main(String[] args) {
//        ApplicationProperties props = new ApplicationProperties("/testData/applicationProperties.cfg");
//        LOG.debug("welcome msgs "+ props.getWelcomeMsg());
//    }
    public String getUserFormHeader(){
        return appProps.getProperty(USERFORM_PAGE_HEADER_KEY);
    }
    public String getSuccessMessage(){
        return appProps.getProperty(USERFORM_SUCCESS_MSG);

    }
    public String getFormError(){
        return appProps.getProperty(FORM_ERROR_KEY);

    }
    public String getFirstNameError(){
        return appProps.getProperty(FIRSTNAME_ERROR_KEY);

    }
    public String getLastNameError(){
         return appProps.getProperty(LASTNAME_ERROR_KEY);
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

    public String getCountriesInEurope(int rowNumber){

         String rowString=appProps.getProperty(EUROPE_KEY);
         return rowString.split(",")[rowNumber-1].trim();
    }
    public String getCountriesInAsia(int rowNumber){

        String rowString=appProps.getProperty(ASIA_KEY);
        return rowString.split(",")[rowNumber-1].trim();
    }
    public String getCountriesInAmerics(int rowNumber){

        String rowString=appProps.getProperty(AMERICAS_KEY);
        return rowString.split(",")[rowNumber-1].trim();
    }
    public String getCountriesInMiddleEastandAfrica(int rowNumber){

        String rowString=appProps.getProperty(MIDDLEEASTANDAFRICA_KEY);
        return rowString.split(",")[rowNumber-1].trim();
    }
}
