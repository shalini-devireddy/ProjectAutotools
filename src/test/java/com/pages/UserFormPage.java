package com.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;

public class UserFormPage extends BasePage{
    private String USERFORM_URL = "http://23.120.24.187:10080/autotoolsv2/user_profile.html";

    private static final Logger LOG = Logger.getLogger(UserFormPage.class);
    public String FORM_ERROR="Form Errors";


    private By buttonBy = By.className("btn-primary");
    private By idBy = By.id("user.firstName");
    private By lastNameBy=By.id("user.lastName");
    private By tagNameBy= By.tagName("strong");
    private By headerBy= By.tagName("h1");
    private By passWordBy= By.id("password");
    private By verifyPasswordBy=By.id("passwordVerification");
    private By successBy= By.className("alert-success");
    private By alertErrorBy= By.className("alert-error");
    private By strongBy= By.className("strong");
    private String VALUE= "value";
    private By formErrorBy=By.xpath("//strong[contains(text(),'Form Errors')]");
    private By firstNameErrorBy=By.id("user.firstName.errors");
    private By lastNameErrorBy=By.id("user.lastName.errors");
    private By titleBy=By.id("user.title");
    private By organizationBy=By.id("user.organization");
    private By emailBy=By.id("user.email");

    public UserFormPage(BasePage theCallingPage){
        theWebDriver = theCallingPage.theWebDriver;
        theCurrentPage=this;
        wait = new FluentWait<WebDriver>(theWebDriver);
        wait.until(ExpectedConditions.presenceOfElementLocated(buttonBy));

    }


    public String inspectFirstName(){

        theWebElement = theWebDriver.findElement(idBy);
        LOG.debug("The text in the field: "+theWebElement.getAttribute(VALUE));
        return theWebElement.getAttribute(VALUE);
    }
    public void validateFirstName(String expectedFirstName){
        Assert.assertEquals(inspectFirstName(),expectedFirstName);
        theWebDriver.close();
    }


    public void userFormToLogin(String loginHeader){
        callUserFormDirect();
         wait = new FluentWait<>(theWebDriver);
        wait.until(ExpectedConditions.presenceOfElementLocated(tagNameBy));
        WebElement loginPageElement=theWebDriver.findElement((headerBy));
        // TODO get the loginHeader text from UserDataProvider(done)
//         userData.getLoginPageHeader();
        Assert.assertEquals(loginPageElement.getText(),loginHeader);
    }

    public void callUserFormDirect(){
        System.setProperty(GECKO_DRIVER_PROPERTY,GECKO_DRIVER_LOCATION);
        theWebDriver = new FirefoxDriver();
        theWebDriver.get(USERFORM_URL);
    }

    public void enterPwd(String pwd){
        WebElement passwordInput = theWebDriver.findElement(passWordBy);
        LOG.debug("entering password: " + pwd);
        passwordInput.sendKeys(pwd);
    }
    public void enterVerifyPwd(String newPassword){
        WebElement verifyPasswordInput = theWebDriver.findElement(verifyPasswordBy);
        LOG.debug("entering password verification"+newPassword);
        verifyPasswordInput.sendKeys(newPassword);
    }
    public void clickSave(){
        theWebElement= theWebDriver.findElement(buttonBy);
        theWebElement.click();
    }
    public String getSuccessMsg(){

        WebElement successMsg=theWebDriver.findElement(successBy);
        return successMsg.getText();
    }

    public void clearFirstName(){
        theWebDriver.findElement(idBy).clear();
    }
    public String getFormError(){
        WebElement formErrorMsg=theWebDriver.findElement(formErrorBy);
        return formErrorMsg.getText();
    }

    public String getFirstNameError(){
        WebElement firstNameErrorMsg=theWebDriver.findElement(firstNameErrorBy);
        return firstNameErrorMsg.getText();
    }

    public void clearLastName(){
        theWebDriver.findElement(lastNameBy).clear();
    }

    public String getLastNameError(){
        WebElement lastNameErrorMsg=theWebDriver.findElement(lastNameErrorBy);
        return lastNameErrorMsg.getText();
    }

    public void clearTitle(){
        theWebDriver.findElement(titleBy).clear();
    }
    public void clearOrganization(){
        theWebDriver.findElement(organizationBy).clear();
    }

    public void clearEmail(){
        //theWebDriver.findElement(emailBy).clear();
        WebElement email_element = theWebDriver.findElement(emailBy);
        String email_Str = email_element.getAttribute("readonly");
        Assert.assertNotNull(email_Str);

    }
}
