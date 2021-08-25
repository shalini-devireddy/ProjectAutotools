package com.utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class RegionsDataProvider {

    private String REGIONS_DATA_FILE = "/testData/regions.properties";
    Properties props = new Properties();

    public RegionsDataProvider(){
        try {
            FileReader reader = new FileReader(RegionsDataProvider.class.getResource(REGIONS_DATA_FILE).getFile());
            props.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String[] countriesInRegion(String regionName){
        return props.getProperty(regionName).split(",");

    }
    public int countCountries(String regionName){

        return countriesInRegion(regionName).length;
    }
}
