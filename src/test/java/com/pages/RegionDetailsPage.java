package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

public class RegionDetailsPage extends BasePage{
    private By xpathBy=By.xpath("//a[contains(text(),'Region Details')]");
    private By idBy= By.id("footer");

    public RegionDetailsPage(EmployeeDetailsPage theEmployeeDetails){

        theWebDriver = theEmployeeDetails.theWebDriver;
        theWebElement = theWebDriver.findElement(xpathBy);
        theWebElement.click();
        FluentWait<WebDriver> wait = new FluentWait<>(theWebDriver);
        wait.until(ExpectedConditions.presenceOfElementLocated(idBy));

    }
}

