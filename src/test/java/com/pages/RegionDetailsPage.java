package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.List;

public class RegionDetailsPage extends BasePage{
    private String regionName;
    private By xpathBy=By.xpath("//a[contains(text(),'"+regionName+"')]");


    public RegionDetailsPage(BasePage theCallingPage){

        theWebDriver = theCallingPage.theWebDriver;
        wait = new FluentWait<>(theWebDriver);
        wait.until(ExpectedConditions.presenceOfElementLocated(idBy));
    }
    public void clickRegion(String regionName){

          theWebElement=theWebDriver.findElement(By.xpath("//a[contains(text(),'"+regionName+"')]"));
          theWebElement.click();
        wait = new FluentWait<>(theWebDriver);
        wait.until(ExpectedConditions.presenceOfElementLocated(strongTagBy));
    }


}

