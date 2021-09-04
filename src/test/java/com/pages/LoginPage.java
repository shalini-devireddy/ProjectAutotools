package com.pages;

import com.exceptions.UserNotFound;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;

import java.util.List;

public class LoginPage extends BasePage {
    public static final String EMAIL_LABEL = "Email";
    public static final String PASSWORD_LABEL = "Password";
    private static final Logger LOG = Logger.getLogger(LoginPage.class);
    public String LOGINPAGE = "http://23.120.24.187:10080/autotoolsv2/login.html";
    public String LOGIN_FAIL_ERROR_MSG = "Your login attempt was not successful, please try again.";
    private final By headerBy = By.tagName("h1");
    private final By footer = By.tagName("p");
    private final By menuBy = By.tagName("a");
    private final String FONT_SIZE = "font-size";
    private final String BACKGROUND_IMG = "background-image";
    private final By labelBy = By.className("control-label");
    private final By emailBy = By.id("j_username");
    private final By pwdBy = By.xpath("//input[@id='j_password']");
    private final By errorBy = By.className("alert-error");
    private final By checkBoxBy = By.xpath("//input[@name='_spring_security_remember_me']");
    private final By emailLabelBy = By.xpath("//label[text()='Email']");
    private final By passwordLabelBy=By.xpath("//label[text()='Password']");

    public LoginPage() {
        super();
    }

    public String getHeader() {
        wait = new FluentWait<>(theWebDriver);
        wait.until(ExpectedConditions.presenceOfElementLocated(buttonBy));
        WebElement loginPageElement = theWebDriver.findElement((headerBy));
        return loginPageElement.getText();
    }

    public String getLoginHeaderFont() {
        theWebElement = theWebDriver.findElement(headerBy);
        LOG.debug(theWebElement.getCssValue(FONT_SIZE));
        String fontSize = theWebElement.getCssValue(FONT_SIZE);
        //Assert.assertTrue(theWebElement.getCssValue(FONT_SIZE).contains(fontSize));
        return fontSize;
    }

    public String getEmailLabel(){
        return theWebDriver.findElement(emailLabelBy).getText();
    }

    public String getPasswordLabel(){
        return theWebDriver.findElement(passwordLabelBy).getText();
    }
    public boolean isEmailInputBoxPresent() {
        return theWebDriver.findElement(emailBy)!=null;
    }

    public boolean isPasswordInputBox(){
        return theWebDriver.findElement(pwdBy)!=null;
    }

    public void enterUsername(String aUserName) {
        theWebDriver.findElement(emailBy).sendKeys(aUserName);
    }

    public void enterPwd(String aPassword) {
        theWebDriver.findElement(pwdBy).sendKeys(aPassword);
    }

    public void clickSignIn() {
        theWebDriver.findElement(buttonBy).click();
    }
    public String getHeaderTag() {
        return theWebDriver.findElement(headerBy).getTagName();
    }



    public String getErrorMsg() {
        wait = new FluentWait<>(theWebDriver);
        wait.until(ExpectedConditions.presenceOfElementLocated(errorBy));

        return theWebDriver.findElement(strongTagBy).getText();
    }

    public void verifyButtonColor(String buttonColor) {
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

    public void loginMenu() {
        theWebDriver.findElement(menuBy).getText();
    }

    public void clickLogin() {
        theWebDriver.findElement(menuBy).click();
    }
//    public void goToHomePage() throws UserNotFound{
//
//        enterUsername(userData.getUserName());
//        LOG.debug("password: "+userData.getPassword(userData.getUserName()));
//        enterPwd(userData.getPassword(userData.getUserName()));
//        clickSignIn();
//
//    }


    public void stayInLogin() {

    }

    public String seeFooter() {

        return theWebDriver.findElement(footerBy).getText();


    }

    public void checkRememberMe() {
        WebElement rememberMeChkbx = null;
        rememberMeChkbx = theWebDriver.findElement(checkBoxBy);
        if (!rememberMeChkbx.isSelected()) {
            rememberMeChkbx.click();
        }

    }


}
