package com.tests;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "classpath:features",
        glue = "com.stepdefs", tags = { "@testFeature" } )
public class TestRunner extends AbstractTestNGCucumberTests {

}
