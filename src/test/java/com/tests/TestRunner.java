package com.tests;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/features",
        glue = "com.stepdefs", tags = { "@wip" } )
public class TestRunner extends AbstractTestNGCucumberTests {

}
