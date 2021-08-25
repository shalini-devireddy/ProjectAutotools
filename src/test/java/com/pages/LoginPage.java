package com.pages;

import com.exceptions.UserNotFound;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;

import java.util.List;

public class LoginPage extends BasePage{
    private static final Logger LOG = Logger.getLogger(LoginPage.class);
    public String LOGINPAGE = "http://23.120.24.187:10080/autotoolsv2/login.html";
    public String LOGIN_FAIL_ERROR_MSG = "Your login attempt was not successful, please try again.";
    public static final String EMAIL_LABEL= "Email";
    public static final String PASSWORD_LABEL="Password";
    private By headerBy= By.tagName("h1");
    private By footer= By.tagName("p");
    private By menuBy= By.tagName("a");
    private String FONT_SIZE= "font-size";
    private String BACKGROUND_IMG= "background-image";
    private By labelBy= By.className("control-label");
    private By emailBy = By.id("j_username");
    private By pwdBy = By.xpath("//input[@id='j_password']");
    private By errorBy = By.className("alert-error");
    private By checkBoxBy=By.xpath("//input[@name='_spring_security_remember_me']");

    public LoginPage(){

        if(theWebDriver==null) {
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
    }

    public String getHeader(){
        wait = new FluentWait<>(theWebDriver);
        wait.until(ExpectedConditions.presenceOfElementLocated(buttonBy));
        WebElement loginPageElement=theWebDriver.findElement((headerBy));
        return loginPageElement.getText();
    }

    public String getLoginHeaderFont(){
        theWebElement=theWebDriver.findElement(headerBy);
        LOG.debug(theWebElement.getCssValue(FONT_SIZE));
        String fontSize=theWebElement.getCssValue(FONT_SIZE);
        //Assert.assertTrue(theWebElement.getCssValue(FONT_SIZE).contains(fontSize));
        return fontSize;
    }

    public String checkLabels(){
        String label=null;
        List<WebElement> labels = theWebDriver.findElements(labelBy);
        for (WebElement aLabel:labels){
            LOG.debug(aLabel.getText());
            System.out.println(aLabel.getText());
            if (aLabel.getText().equals(EMAIL_LABEL)){
                return aLabel.getText();
            }else if (aLabel.getText().equals(PASSWORD_LABEL)){
                return aLabel.getText();
            }
        }
        return label;
    }
//    public String checkPasswordLabel(){
//        String label=null;
//        List<WebElement> labels = theWebDriver.findElements(labelBy);
//        for (WebElement aLabel:labels) {
//            LOG.debug(aLabel.getText());
//            if (aLabel.getText().equals(PASSWORD_LABEL)) {
//                return aLabel.getText();
//            }
//        }
//            return label;
//    }

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

    public void loginMenu(){
        theWebDriver.findElement(menuBy).getText();
    }

    public void clickLogin(){
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

        public void stayInLogin(){

    }

    public String seeFooter(){

          return theWebDriver.findElement(footerBy).getText();


    }

    public void checkRememberMe(){
        WebElement rememberMeChkbx = null;
        rememberMeChkbx = theWebDriver.findElement(checkBoxBy);
        if (!rememberMeChkbx.isSelected())
        {
            rememberMeChkbx.click();
        }

    }

    public String getHeaderTag() {
        return theWebDriver.findElement(headerBy).getTagName();
    }
}
