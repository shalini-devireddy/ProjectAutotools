package com.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

public class WelcomePage extends BasePage{

    private static final Logger LOG = Logger.getLogger(WelcomePage.class);
    public String WELCOME_PAGE = "welcome";
    public String WELCOME_MESSAGE = "Welcome to Auto Tools";
    protected By xpathBy= By.xpath("//div[text(),'==0']");
    protected By welcomeMsgBy = By.xpath("//div[@class='page-header']//following-sibling::p");

    public WelcomePage(BasePage callingPage){
        theWebDriver = callingPage.getWebDriver();
        wait = new FluentWait<>(theWebDriver);
        wait.until(ExpectedConditions.presenceOfElementLocated(ptagBy));
    }

    public String getWelcomeMsg(){
        LOG.debug(theWebDriver.findElement(welcomeMsgBy).getText());
       return theWebDriver.findElement(welcomeMsgBy).getText();
    }

    public void welcomePageMenuBar(){
         theWebDriver.findElement(xpathBy);
    }

}
