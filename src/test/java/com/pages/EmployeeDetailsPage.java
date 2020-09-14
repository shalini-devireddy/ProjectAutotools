package com.pages;

import com.exceptions.UserNotFound;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;

import java.util.List;

public class EmployeeDetailsPage extends BasePage{

    private static final Logger LOG = Logger.getLogger(EmployeeDetailsPage.class);

    public EmployeeDetailsPage(BasePage theCallingPage){
        theWebDriver=theCallingPage.theWebDriver;
        wait = new FluentWait<>(theWebDriver);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(trTagNameBy));
    }
    public int employeeCount(){

        wait=new FluentWait<>(theWebDriver);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(trTagNameBy));
        theWebElement=theWebDriver.findElement(tbodyTagNameBy);
        LOG.debug(theWebElement.findElements(trTagNameBy).size());
        return theWebElement.findElements(trTagNameBy).size();

    }
}
