package com.domain;

import com.utils.ApplicationProperties;
import javafx.application.Application;

public class GenericTable {

    ApplicationProperties appPros =
            new ApplicationProperties(ApplicationProperties.APPLICATION_PROP_FILE);

    public String getNthColumn(String tableName, int columnNumber){
        String columnName=null;
        switch (tableName){
            case "employees":
                columnName = appPros.getEmployeesTableColumn(columnNumber);
                break;
            case  "regions":
                columnName= appPros.getRegionsTableColumn(columnNumber);
        }
        return columnName;
    }
}
