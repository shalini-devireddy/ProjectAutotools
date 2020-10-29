package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

public class CountriesInRegionPage extends BasePage{

    private String regionName;
    private By xpathBy=By.xpath("//a[contains(text(),'"+regionName+"')]");

    public CountriesInRegionPage(RegionDetailsPage callingPage){

        WebElement theWebElement = theWebDriver.findElement(xpathBy);
        theWebElement.click();
        FluentWait<WebDriver> wait = new FluentWait<>(theWebDriver);
        wait.until(ExpectedConditions.presenceOfElementLocated(footerBy));
    }

    public int countCountries(){
        theWebElement=theWebDriver.findElement(tbodyTagNameBy);
        return theWebElement.findElements(trTagNameBy).size();

    }
}
