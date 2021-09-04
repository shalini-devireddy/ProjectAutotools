package com.pages;

//import com.oldstepdefs.StepDefinitions;
//import com.utils.UserDataProvider;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
    protected String CHROME_DRIVER_LOCATION = "C:\\Users\\Haripemireddy\\QATraining\\Chrome\\chromedriver.exe";
    protected String CHROME_DRIVER_PROPERTY = "webdriver.chrome.driver";

    public static final String EMPLOYEE_INFO="Employee Information";
    public static final String ALL_EMPLOYEE_DETAILS= "All Employee Details";
    public static final String EMPLOYEE_SEARCH= "Employee Search";
    public static final String MYPROFILE="My Profile";
    public static final String LOGOUT="Logout";
    public static final String REGIONS="Region Details";

//    UserDataProvider userData= new UserDataProvider();
    protected static WebDriver theWebDriver;
    protected WebElement theWebElement;
    FluentWait<WebDriver> wait;

    protected TakesScreenshot takesScreenshot;
    private static final Random rnd = new Random();
    private static final int UPPER_BOUND = 10000000;

    protected By buttonBy = By.tagName("button");
    protected By ptagBy = By.tagName("p");
    protected By strongTagBy = By.tagName("strong");
    protected By tableBy=By.tagName("table");
    protected By thTagNameBy=By.tagName("th");
   // private By dropDownBy = By.className("dropdown-toggle");
    protected By classNameBy = By.className("open");
    protected By userFormBy = By.xpath("//a[contains(text(),'My Profile')]");
    protected By dropDownToggleBy= By.className("dropdown-toggle");
    protected By openBy= By.className("open");
    protected By logOutBy= By.xpath("//a[contains(text(),'Logout')]");
    protected By allEmployeeDetailsMenuBy= By.xpath("//a[contains(text(),'All Employees Details')]");
    protected By employeeSearchBy=By.xpath("//a[contains(text(),'Employee Search')]");
    protected By trTagNameBy=By.tagName("tr");
    protected By tbodyTagNameBy=By.tagName("tbody");
    protected By footerBy=By.id("footer");
    protected By regionDetailsBy=By.xpath("//a[contains(text(),'Region Details')]");
    protected By idBy= By.id("footer");

    protected BasePage theCurrentPage=this;

    public BasePage(){

        System.setProperty(CHROME_DRIVER_PROPERTY, CHROME_DRIVER_LOCATION);
        System.setProperty("webdriver.chrome.whitelistedIps", "23.120.24.187");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--explicitly-allowed-ports=10080");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        options.merge(capabilities);
        theWebDriver = new ChromeDriver(options);
        //        theWebDriver = new FirefoxDriver();
        //        theWebDriver = new ChromeDriver();
        theWebDriver.get(LOGIN_URL);
        wait = new FluentWait<>(theWebDriver);
        wait.until(ExpectedConditions.presenceOfElementLocated(buttonBy));
    }

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
    public void clickMenuItem(String menuItemName){
        List<WebElement> dropDownMenus = theWebDriver.findElements(dropDownToggleBy);
        switch (menuItemName){
            case ALL_EMPLOYEE_DETAILS:
                for (WebElement aWebElement : dropDownMenus){
                    LOG.debug(aWebElement.getText());
                    if(aWebElement.getText().trim().equals(EMPLOYEE_INFO)){
                        aWebElement.click();
                        wait = new FluentWait<>(theWebDriver);
                        wait.until(ExpectedConditions.presenceOfElementLocated(openBy));
                        theWebElement = theWebDriver.findElement(allEmployeeDetailsMenuBy);
                        theWebElement.click();
                        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(trTagNameBy));
                    }
                }
                break;
            case EMPLOYEE_SEARCH:
                for (WebElement aWebElement : dropDownMenus){
                    LOG.debug(aWebElement.getText());
                    if(aWebElement.getText().trim().equals(EMPLOYEE_INFO)){
                        aWebElement.click();
                        wait = new FluentWait<>(theWebDriver);
                        wait.until(ExpectedConditions.presenceOfElementLocated(openBy));
                        theWebElement = theWebDriver.findElement(employeeSearchBy                        );
                        theWebElement.click();
                        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(trTagNameBy));
                    }
                }
                break;
            case MYPROFILE:
                theWebElement = theWebDriver.findElement(dropDownToggleBy);
                theWebElement.click();
                wait = new FluentWait<>(theWebDriver);
                wait.until(ExpectedConditions.presenceOfElementLocated(classNameBy));
                theWebElement = theWebDriver.findElement(userFormBy);
                theWebElement.click();
                wait.until(ExpectedConditions.presenceOfElementLocated(buttonBy));
                break;
            case LOGOUT:
                theWebElement = theWebDriver.findElement(dropDownToggleBy);
                theWebElement.click();
                wait = new FluentWait<>(theWebDriver);
                wait.until(ExpectedConditions.presenceOfElementLocated(classNameBy));
                theWebElement = theWebDriver.findElement(logOutBy);
                theWebElement.click();
                wait.until(ExpectedConditions.presenceOfElementLocated(buttonBy));
                theWebDriver.close();
                break;
            case REGIONS:
                theWebElement = theWebDriver.findElement(regionDetailsBy);
                theWebElement.click();
                wait = new FluentWait<>(theWebDriver);
                wait.until(ExpectedConditions.presenceOfElementLocated(idBy));
                break;
        }

    }
    public void closeBrowser(){
         theWebDriver.close();
    }

    protected void scrollToElement(WebElement theWebElement){
        ((JavascriptExecutor) theWebDriver).executeScript("arguments[0].scrollIntoView(true);", theWebElement);
    }
    public BasePage getCurrentPage(){
        return theCurrentPage;
    }

    public WebDriver getWebDriver(){
        return theWebDriver;
    }
}
