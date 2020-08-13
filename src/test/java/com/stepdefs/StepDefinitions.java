package com.stepdefs;

import com.exceptions.UserNotFound;
import com.utils.UserDataProvider;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;

import java.util.List;

public class StepDefinitions {

    private static final Logger LOG = Logger.getLogger(StepDefinitions.class);

    WebDriver theWebDriver;
    WebElement theWebElement;
    UserDataProvider userData = new UserDataProvider();

    @Given("User is on Login Page")
    public void getLoginPage(){

        System.setProperty("webdriver.gecko.driver",
                "C:\\Users\\Haripemireddy\\QATraining\\Gecko\\geckodriver.exe");
        theWebDriver = new FirefoxDriver();
        theWebDriver.get("http://23.120.24.187:10080/autotoolsv2");
        FluentWait<WebDriver> wait = new FluentWait<>(theWebDriver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("button")));
    }
    @When("User enters {string} as username")
    public void enterUsername(String aUserName){

        WebElement userNameInput = theWebDriver.findElement(By.id("j_username"));
        LOG.debug("entering username: "+aUserName);
        userNameInput.sendKeys(aUserName);
    }
    @And("User enters {string} as password")
    public void enterPwd(String aPassword){
        WebElement passWordInput = theWebDriver.findElement(By.xpath("//input[@id='j_password']"));
        passWordInput.sendKeys(aPassword);
    }
    @And("User clicks Sign In")
    public void clickSignIn(){
//        btn btn-primary'
            WebElement button = theWebDriver.findElement(By.tagName("button"));
            button.click();
    }
    @Then("User should see Welcome page with msg {string}")
    public void checkAndCloseWelcome(String welcomeMsg){
        checkWelcome(welcomeMsg);
       // theWebDriver.close();
    }

    private void checkWelcome(String welcomeMsg){

        FluentWait<WebDriver> wait = new FluentWait<>(theWebDriver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("p")));
        WebElement msgElement = theWebDriver.findElement(By.tagName("p"));

        Assert.assertEquals(msgElement.getText(), welcomeMsg);
    }

    @Then("User should see the message {string}")
    public void checkErrorMessage(String errorMessage){
        FluentWait<WebDriver> wait = new FluentWait<>(theWebDriver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='alert alert-error']")));
        WebElement errorMsgElement=theWebDriver.findElement((By.tagName("strong")));
        
        Assert.assertEquals(errorMsgElement.getText(),errorMessage);
        //theWebDriver.close(); //div[@class='alert alert-error']
    }
    @Then("User should see sign in button with background color {string}")
    public void verifyButtonColor(String buttonColor){
        theWebElement = theWebDriver.findElement(By.tagName("button"));
        LOG.debug(theWebElement.getCssValue("background-image"));
        Assert.assertTrue(
                theWebElement.getCssValue("background-image").contains(buttonColor));
        theWebDriver.close();
    }
    //User form
    @Given("User logged in as {string}")
    public void loginUser(String userName) throws UserNotFound {
        getLoginPage();
        enterUsername(userName);
        // TODO Properties or file
        LOG.debug("password: "+userData.getPassword(userName));
        enterPwd(userData.getPassword(userName));
        clickSignIn();
        // TODO Welcome message get from a properties files
        checkWelcome("Welcome to Auto Tools");
    }
    @And("user navigates to user form page")
    public void goToUserForm(){
        theWebElement = theWebDriver.findElement(By.className("dropdown-toggle"));
        theWebElement.click();
        FluentWait<WebDriver> wait = new FluentWait<>(theWebDriver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("open")));
        theWebElement = theWebDriver.findElement(By.xpath("//a[contains(text(),'My Profile')]"));
        theWebElement.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("btn-primary")));
    }
    @When("User inspects the first name field value")
    public String inspectFirstName(){
        theWebElement = theWebDriver.findElement(By.id("user.firstName"));
        LOG.debug("The text in the field: "+theWebElement.getAttribute("value"));
        return theWebElement.getAttribute("value");
    }
    @And("the first name field value should be {string}")
    public void validateFirstName(String expectedFirstName){
        Assert.assertEquals(inspectFirstName(),expectedFirstName);
        theWebDriver.close();
    }
    //employee details
    @Given("Valid User is on employee details page")
    public void goToEmpDetails() throws UserNotFound{
        goToHomePage();
        List<WebElement> dropDownMenus = theWebDriver.findElements(By.className("dropdown-toggle"));
        for (WebElement aWebElement : dropDownMenus){
            LOG.debug(aWebElement.getText());
            if(aWebElement.getText().trim().equals("Employee Information")){
                aWebElement.click();
                FluentWait<WebDriver> wait = new FluentWait<>(theWebDriver);
                wait.until(ExpectedConditions.presenceOfElementLocated(By.className("open")));
                theWebElement = theWebDriver.findElement(By.xpath("//a[contains(text(),'All Employees Details')]"));
                theWebElement.click();
                wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("tr")));
            }
        }
    }

    @And("user observes {int} column is {string}")
    public void inspectSecondColumn(int columnNumber, String expectedColumnName){
        theWebElement = theWebDriver.findElement(By.tagName("table"));
        List<WebElement> tableHeaders =  theWebElement.findElements(By.tagName("th"));
        for (WebElement aHeader : tableHeaders){
            LOG.debug(aHeader.getText());
        }
        Assert.assertEquals(tableHeaders.get(columnNumber-1).getText(),
                expectedColumnName);
        //theWebDriver.close();
    }
    //Region details
    @Given("Valid User is on region details page")
    public void goToRegionDetails() throws UserNotFound {

        goToHomePage();
        theWebElement = theWebDriver.findElement(By.xpath("//a[contains(text(),'Region Details')]"));
        theWebElement.click();
        FluentWait<WebDriver> wait = new FluentWait<>(theWebDriver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("footer")));

    }

    private void goToHomePage() throws UserNotFound{
        getLoginPage();
        enterUsername(userData.getUserName());
        // TODO Properties or file
        LOG.debug("password: "+userData.getPassword(userData.getUserName()));
        enterPwd(userData.getPassword(userData.getUserName()));
        clickSignIn();
        // TODO Welcome message get from a properties files
        checkWelcome("Welcome to Auto Tools");
    }
    //Countries in region
    @Given("Valid User is on countries in {string} region page")
    public void goToCountriesInRegion(String regionName) throws UserNotFound{
        goToRegionDetails();
        theWebElement=theWebDriver.findElement(By.xpath("//a[contains(text(),'"+regionName+"')]"));
        theWebElement.click();
        FluentWait<WebDriver> wait = new FluentWait<>(theWebDriver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("footer")));
    }
    //locations in country
    @Given("Valid User navigates to locations in country {string}")
     public void goToLocationsInCountry(String countryName) throws UserNotFound{
        // TODO find region from the properties file countries
        // create a map of regions and countries and get the region from country
        goToCountriesInRegion(userData.getRegionName(countryName));
        theWebElement=theWebDriver.findElement(By.xpath("//a[contains(text(),'"+countryName+"')]"));
        theWebElement.click();
        FluentWait<WebDriver> wait = new FluentWait<>(theWebDriver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("footer")));
     }
     //Departments in location
    @Given("Valid User is on departments in location page")
    public void goToDepertmentsInLocations() throws UserNotFound{

        goToLocationsInCountry("");
        theWebElement=theWebDriver.findElement(By.xpath("//a[contains(text(),'2500')]"));
        theWebElement.click();
        FluentWait<WebDriver> wait = new FluentWait<>(theWebDriver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("footer")));
    }
}
