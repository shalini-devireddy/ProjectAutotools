package com.pages;

import com.exceptions.UserNotFound;
import com.stepdefs.StepDefinitions;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;

public class LoginPage extends BasePage{
    private static final Logger LOG = Logger.getLogger(LoginPage.class);
    public String LOGIN_FAIL_ERROR_MSG = "Your login attempt was not successful, please try again.";

    private String BACKGROUND_IMG= "background-image";
    private By emailBy = By.id("j_username");
    private By pwdBy = By.xpath("//input[@id='j_password']");
    private By errorBy = By.className("alert-error");
    private By checkBoxBy=By.xpath("//input[@name='_spring_security_remember_me']");
    private String PASSWORD = "password";

    public LoginPage(){

        System.setProperty(GECKO_DRIVER_PROPERTY,GECKO_DRIVER_LOCATION);
        theWebDriver = new FirefoxDriver();
        theWebDriver.get(LOGIN_URL);
        wait = new FluentWait<>(theWebDriver);
        wait.until(ExpectedConditions.presenceOfElementLocated(buttonBy));
    }

    public void enterUsername(String aUserName){
        theWebDriver.findElement(emailBy).sendKeys(aUserName);
    }

    public void enterPwd(String aPassword){
        theWebDriver.findElement(pwdBy).sendKeys(aPassword);
    }

    public void clickSignIn(){
        theWebDriver.findElement(buttonBy).click();
    }

    public String getErrorMsg(){
        wait = new FluentWait<>(theWebDriver);
        wait.until(ExpectedConditions.presenceOfElementLocated(errorBy));

        return theWebDriver.findElement(strongTagBy).getText();
    }

    public void verifyButtonColor(String buttonColor){
        theWebElement = theWebDriver.findElement(buttonBy);
        LOG.debug(theWebElement.getCssValue(BACKGROUND_IMG));
        Assert.assertTrue(
                theWebElement.getCssValue(BACKGROUND_IMG).contains(buttonColor));
    }

    public void loginUser(String userName, String password) throws UserNotFound {
        enterUsername(userName);
        enterPwd(password);
        clickSignIn();
    }

//    public void goToHomePage() throws UserNotFound{
//
//        enterUsername(userData.getUserName());
//        LOG.debug("password: "+userData.getPassword(userData.getUserName()));
//        enterPwd(userData.getPassword(userData.getUserName()));
//        clickSignIn();
//
//    }
    public void checkRememberMe(){
        WebElement rememberMeChkbx = null;
        rememberMeChkbx = theWebDriver.findElement(checkBoxBy);
        if (!rememberMeChkbx.isSelected())
        {
            rememberMeChkbx.click();
        }

    }
}
