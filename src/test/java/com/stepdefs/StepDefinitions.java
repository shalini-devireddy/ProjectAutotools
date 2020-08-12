package com.stepdefs;

import com.exceptions.UserNotFound;
import com.utils.UserDataProvider;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en_scouse.An;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;

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
    public void checkWelcome(String welcomeMsg){

        FluentWait<WebDriver> wait = new FluentWait<>(theWebDriver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("p")));
        WebElement msgElement = theWebDriver.findElement(By.tagName("p"));

        Assert.assertEquals(msgElement.getText(), welcomeMsg);
//        theWebDriver.close();

    }
    @Then("User should see the message {string}")
    public void checkErrorMessage(String errorMessage){
        FluentWait<WebDriver> wait = new FluentWait<>(theWebDriver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='alert alert-error']")));
        WebElement errorMsgElement=theWebDriver.findElement((By.tagName("strong")));
        
        Assert.assertEquals(errorMsgElement.getText(),errorMessage);
//        theWebDriver.close(); //div[@class='alert alert-error']
    }
    @Then("User should see sign in button with background color {string}")
    public void verifyButtonColor(String buttonColor){
        theWebElement = theWebDriver.findElement(By.tagName("button"));
        LOG.debug(theWebElement.getCssValue("background-image"));
        Assert.assertTrue(
                theWebElement.getCssValue("background-image").contains(buttonColor));
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
    }

}
