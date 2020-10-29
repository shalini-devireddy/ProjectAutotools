package com.domain;

import com.utils.ApplicationProperties;
import javafx.application.Application;

public class GenericTable {

    ApplicationProperties appPros =
            new ApplicationProperties();

    public String getNthColumn(String tableName, int columnNumber){
        String columnName=null;
        switch (tableName){
            case "employees":
                columnName = appPros.getEmployeesTableColumn(columnNumber);
                break;
            case "regions":
                columnName = appPros.getRegionsTableColumn(columnNumber);
                break;
            case "countries":
                columnName = appPros.getCountriesTableColumn(columnNumber);
                break;
            case "locations":
                columnName = appPros.getLocationsTableColumn(columnNumber);
                break;
            case "departments":
                columnName=appPros.getDepartmentsTableColumn(columnNumber);
                break;
        }
        return columnName;
    }
//    public String getCountry(String regionName,int rowNumber){
//        String countryName=null;
//        switch (regionName){
//            case "europe":
//                countryName=appPros.getCountriesInEurope(rowNumber);
//                break;
//            case "asia":
//                countryName=appPros.
//        }
//        return countryName;
//    }
}
