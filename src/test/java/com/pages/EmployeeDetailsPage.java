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

    private By classNameBy= By.className("dropdown-toggle");
    private By openBy= By.className("open");
    private By xpathBy= By.xpath("//a[contains(text(),'All Employees Details')]");
    private By tagNameBy= By.tagName("tr");
    private By trTagNameBy=By.tagName("tr");
    private By tbodyTagNameBy=By.tagName("tbody");

    private String EMPLOYEE_INFO= "Employee Information";

    public EmployeeDetailsPage(BasePage theCallingPage){

        theWebDriver=theCallingPage.theWebDriver;
        List<WebElement> dropDownMenus = theWebDriver.findElements(classNameBy);
        for (WebElement aWebElement : dropDownMenus){
            LOG.debug(aWebElement.getText());
            if(aWebElement.getText().trim().equals(EMPLOYEE_INFO)){
                aWebElement.click();
                FluentWait<WebDriver> wait = new FluentWait<>(theWebDriver);
                wait.until(ExpectedConditions.presenceOfElementLocated(openBy));
                theWebElement = theWebDriver.findElement(xpathBy);
                theWebElement.click();
                wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(trTagNameBy));
            }
        }
    }
    public void employeeCount(){

        wait=new FluentWait<>(theWebDriver);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(tagNameBy));
        theWebElement=theWebDriver.findElement(tbodyTagNameBy);
        LOG.debug(theWebElement.findElements(tagNameBy).size());
        int totalCount= theWebElement.findElements(tagNameBy).size();

    }
}
