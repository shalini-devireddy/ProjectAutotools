package com.tests;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "classpath:features",
        glue = "com.stepdefs", tags = { "@337721 or @337724" } )
public class TestRunner extends AbstractTestNGCucumberTests {

}
