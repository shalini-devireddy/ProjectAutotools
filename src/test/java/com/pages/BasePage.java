package com.pages;

import com.stepdefs.StepDefinitions;
import com.utils.UserDataProvider;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class BasePage {

    private static final Logger LOG = Logger.getLogger(BasePage.class);
    protected String LOGIN_URL = "http://23.120.24.187:10080/autotoolsv2";
    protected String GECKO_DRIVER_LOCATION = "C:\\Users\\Haripemireddy\\QATraining\\Gecko\\geckodriver.exe";
    protected String GECKO_DRIVER_PROPERTY = "webdriver.gecko.driver";

//    UserDataProvider userData= new UserDataProvider();
    protected WebDriver theWebDriver;
    protected WebElement theWebElement;
    FluentWait<WebDriver> wait;

    protected TakesScreenshot takesScreenshot;
    private static final Random rnd = new Random();
    private static final int UPPER_BOUND = 10000000;

    protected By buttonBy = By.tagName("button");
    protected By ptagBy = By.tagName("p");
    protected By strongTagBy = By.tagName("strong");
    private By tableBy=By.tagName("table");
    private By thTagNameBy=By.tagName("th");

    public void closePage(){
        theWebDriver.close();
    }

    public void takeScreenShot(String page){
        takesScreenshot = (TakesScreenshot) theWebDriver;
        try {

            FileUtils.copyFile(takesScreenshot.getScreenshotAs(OutputType.FILE)
                    ,new File("src/test/resources/screenshots/"+page+rnd.nextInt(UPPER_BOUND)+".png"));
            LOG.debug("Screenshot of "+page+ "taken");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void inspectSecondColumn(int columnNumber, String expectedColumnName){

        theWebElement = theWebDriver.findElement(tableBy);
        List<WebElement> tableHeaders =  theWebElement.findElements(thTagNameBy);
        for (WebElement aHeader : tableHeaders){
            LOG.debug(aHeader.getText());
        }
        Assert.assertEquals(tableHeaders.get(columnNumber-1).getText(),
                expectedColumnName);
    }
}
