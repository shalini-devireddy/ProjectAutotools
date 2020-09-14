package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

public class RegionDetailsPage extends BasePage{


    public RegionDetailsPage(BasePage theCallingPage){

        theWebDriver = theCallingPage.theWebDriver;
        wait = new FluentWait<>(theWebDriver);
        wait.until(ExpectedConditions.presenceOfElementLocated(idBy));
    }
}

