package com.pages;

import com.exceptions.UserNotFound;
import com.stepdefs.StepDefinitions;
import org.apache.log4j.Logger;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;

public class WelcomePage extends BasePage{

    private static final Logger LOG = Logger.getLogger(WelcomePage.class);
    public String WELCOME_PAGE = "welcome";
    public String WELCOME_MESSAGE = "Welcome to Auto Tools";

    public WelcomePage(BasePage callingPage){
        theWebDriver = callingPage.theWebDriver;
        wait = new FluentWait<>(theWebDriver);
        wait.until(ExpectedConditions.presenceOfElementLocated(ptagBy));
    }

    public String getWelcomeMsg(){
        LOG.debug(theWebDriver.findElement(ptagBy).getText());
       return theWebDriver.findElement(ptagBy).getText();
    }

}
