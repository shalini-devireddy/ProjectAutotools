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

public class EmployeeSearchPage extends BasePage{
    private static final Logger LOG = Logger.getLogger(EmployeeSearchPage.class);

    private By classNameBy=By.className("dropdown-toggle");
    private By openBy=By.className("open");
    private By xpathBy=By.xpath("//a[contains(text(),'Employee Search')]");
    private By idBy=By.id("footer");
    private By inputBy=By.tagName("input");
    private By lastNameIdBy=By.id("lastName");
    private By nameBy=By.xpath("//h1[contains(text(),'Alexis Bull')]");
    private By tagNameBy=By.tagName("table");
    private By bTagBy=By.tagName("b");

    private String EMPLOYEEINFO="Employee Information";
    private String FIRSTNAME="entering firstname: ";
    private String LASTNAME="entering lastname: ";

    public EmployeeSearchPage(){

        List<WebElement> dropDownMenus = theWebDriver.findElements(classNameBy);

        for (WebElement aWebElement : dropDownMenus) {
            LOG.debug(aWebElement.getText());

            if (aWebElement.getText().trim().equals(EMPLOYEEINFO)) {
                aWebElement.click();
                FluentWait<WebDriver> wait = new FluentWait<>(theWebDriver);
                wait.until(ExpectedConditions.presenceOfElementLocated(openBy));
                theWebElement = theWebDriver.findElement(xpathBy);
                theWebElement.click();
                wait.until(ExpectedConditions.presenceOfElementLocated(idBy));
            }
        }
    }

    public void enterFirstName(String firstName){

        WebElement userNameInput = theWebDriver.findElement(inputBy);
        LOG.debug(FIRSTNAME+firstName);
        userNameInput.sendKeys(firstName);
    }

    public void enterLastName(String lastName){
        WebElement userNameInput = theWebDriver.findElement(lastNameIdBy);
        LOG.debug(LASTNAME+lastName);
        userNameInput.sendKeys(lastName);
    }

    public void clickSearch(){

        WebElement button = theWebDriver.findElement(buttonBy);
        button.click();
    }

    public void checkHeader(String header){
        FluentWait<WebDriver> wait = new FluentWait<>(theWebDriver);
        wait.until(ExpectedConditions.presenceOfElementLocated(nameBy));
        WebElement msgElement = theWebDriver.findElement(nameBy);

        Assert.assertEquals(msgElement.getText(), header);
        // java generic classes

    }
    public void checkSecondLabel(int rowNumber,String expecedRowName)
    {

        theWebElement = theWebDriver.findElement(tagNameBy);
        List<WebElement> tableHeaders =  theWebElement.findElements(bTagBy);
        for (WebElement aHeader : tableHeaders){
            LOG.debug(aHeader.getText());
        }
        Assert.assertEquals(tableHeaders.get(rowNumber-1).getText(),
                expecedRowName);
    }
}
