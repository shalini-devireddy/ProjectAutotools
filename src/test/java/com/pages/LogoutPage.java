package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

public class LogoutPage extends BasePage{

    public LogoutPage(BasePage theCallingPage){

        theWebDriver = theCallingPage.theWebDriver;
        wait = new FluentWait<>(theWebDriver);

        wait.until(ExpectedConditions.presenceOfElementLocated(buttonBy));
        theWebDriver.close();
    }

}
