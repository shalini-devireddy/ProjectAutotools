package com.pages;

import com.exceptions.UserNotFound;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;

import java.util.List;

public class EmployeeDetailsPage extends BasePage{

    private static final Logger LOG = Logger.getLogger(EmployeeDetailsPage.class);
    private String ALL_EMPLOYEE="http://23.120.24.187:10080/autotoolsv2/employee_details.html";
    private String firstName;
    private By xpathBy=By.xpath("//td[contains(text(),'" +firstName+"')]");

    public EmployeeDetailsPage(BasePage theCallingPage){
        theWebDriver=theCallingPage.theWebDriver;
        theCurrentPage=this;
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

    public void verifyLastEmployee(){
       theWebDriver.get(ALL_EMPLOYEE);
      //theWebElement=theWebDriver.findElement(xpathBy);
        Actions act= new Actions(theWebDriver);


//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

    }
    public void scrollToEmployee(String empName){
//        Actions actions = new Actions(theWebDriver);
        theWebElement = theWebDriver.findElement(By.xpath("//td[contains(text(),'"+empName+"')]"));
//        actions.moveToElement(theWebElement).perform();
        scrollToElement(theWebElement);

    }
}
