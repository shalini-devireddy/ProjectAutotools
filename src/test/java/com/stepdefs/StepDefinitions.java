package com.stepdefs;

import com.domain.User;
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
    @And("User checks remember me check box")
    public void checkRememberMe(){
          WebElement rememberMeChkbx= theWebDriver.findElement(By.name("_spring_security_remember_me"));
          rememberMeChkbx.click();
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
        LOG.debug("password: "+userData.getPassword(userName));
        enterPwd(userData.getPassword(userName));
        clickSignIn();
        // TODO Welcome message get from a properties files
//        checkWelcome(userData.getWelcomeMsg());
        checkWelcome("Welcome to Auto Tools");
    }
    @And("User {string} tries to login with old password")
    public void loginWithOldPwd(String userName) throws UserNotFound {
        getLoginPage();
        enterUsername(userName);
        LOG.debug("password: "+userData.getPassword(userName));
        enterPwd(userData.getPassword(userName));
        clickSignIn();

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
    @And("logout")
    public void logOut(){
       goToUserForm();
        theWebElement = theWebDriver.findElement(By.className("dropdown-toggle"));
        theWebElement.click();
        FluentWait<WebDriver> wait = new FluentWait<>(theWebDriver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("open")));
        theWebElement = theWebDriver.findElement(By.xpath("//a[contains(text(),'Logout')]"));
        theWebElement.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("btn-primary")));
        theWebDriver.close();
    }
    @Then("User form direct call should take to login page with {string}")
    public void userFormToLogin(String loginHeader){
        callUserFormDirect();
        FluentWait<WebDriver> wait = new FluentWait<>(theWebDriver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("strong")));
        WebElement loginPageElement=theWebDriver.findElement((By.tagName("h1")));
        // TODO get the loginHeader text from UserDataProvider
        Assert.assertEquals(loginPageElement.getText(),loginHeader);
    }
    @Then("User form direct call should take to userform page {string}")
    public void directToUserForm(String userFormHeader){
        callUserFormDirect();
        FluentWait<WebDriver> wait = new FluentWait<>(theWebDriver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("strong")));
        WebElement userFormElement=theWebElement.findElement(By.tagName("h1"));
        // TODO get userformheader text from UserDataprovider.
        Assert.assertEquals(userFormElement.getText(),userFormHeader);
    }

    private void callUserFormDirect(){
        System.setProperty("webdriver.gecko.driver",
                "C:\\Users\\Haripemireddy\\QATraining\\Gecko\\geckodriver.exe");
        theWebDriver = new FirefoxDriver();
        theWebDriver.get("http://23.120.24.187:10080/autotoolsv2/user_profile.html");
    }
    @And("Change the password as {string}")
    public void changePassword(String newPassword) {
        goToUserForm();
        WebElement passwordInput = theWebDriver.findElement(By.id("password"));
        LOG.debug("entering password: " + newPassword);
        passwordInput.sendKeys(newPassword);
        WebElement verifyPasswordInput = theWebDriver.findElement(By.id("passwordVerification"));
        LOG.debug("entering password verification"+verifyPasswordInput);
        verifyPasswordInput.sendKeys(newPassword);
        theWebElement= theWebDriver.findElement(By.tagName("button"));
        theWebElement.click();
        /// TODO verify that the success message get from USerData provider
        WebElement successMsg=theWebDriver.findElement((By.className("alert-success")));
    }
    @And("Check error with old password")
    public void logiWithOldPassword()throws UserNotFound{
        FluentWait<WebDriver> wait = new FluentWait<>(theWebDriver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("alert-error")));
        WebElement errorMsg=theWebDriver.findElement(By.className("alert-error"));
        theWebDriver.close();

    }
    @And("User {string} logs in with pwd {string}")
    public void enterNewPwd(String userName, String pwd){
        getLoginPage();
        enterUsername(userName);
        enterPwd(pwd);
        clickSignIn();
        checkWelcome("Welcome to Auto Tools");
        userData.updateCredentials(userName,pwd);
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
        // same as users now you have to for regions data
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
    //Employee search
    @Given("Valid user is on employee search page")
     public void employeeSearch() throws UserNotFound{
        goToHomePage();
        List<WebElement> dropDownMenus = theWebDriver.findElements(By.className("dropdown-toggle"));
        for (WebElement aWebElement : dropDownMenus){
            LOG.debug(aWebElement.getText());
            if(aWebElement.getText().trim().equals("Employee Information")){
                aWebElement.click();
                FluentWait<WebDriver> wait = new FluentWait<>(theWebDriver);
                wait.until(ExpectedConditions.presenceOfElementLocated(By.className("open")));
                theWebElement = theWebDriver.findElement(By.xpath("//a[contains(text(),'Employee Search')]"));
                theWebElement.click();
                wait.until(ExpectedConditions.presenceOfElementLocated(By.id("footer")));
            }
        }

    }
    @When("User enters {string} as first name")
    public void enterFirstName(String firstName){
        WebElement userNameInput = theWebDriver.findElement(By.tagName("input"));
        LOG.debug("entering firstname: "+firstName);
        userNameInput.sendKeys(firstName);
    }
    @And("User enters {string} as last name")
    public void enterLastName(String lastName){
        WebElement userNameInput = theWebDriver.findElement(By.id("lastName"));
        LOG.debug("entering lastname: "+lastName);
        userNameInput.sendKeys(lastName);
    }
    @And("User clicks search")
    public void clickSearch(){

        WebElement button = theWebDriver.findElement(By.tagName("button"));
        button.click();
    }

    @Then("User should see employee page with header {string}")
    public void empHeader(String header){
        checkHeader(header);
    }


    private void checkHeader(String header){
        FluentWait<WebDriver> wait = new FluentWait<>(theWebDriver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[contains(text(),'Alexis Bull')]")));
        WebElement msgElement = theWebDriver.findElement(By.xpath("//h1[contains(text(),'Alexis Bull')]"));

        Assert.assertEquals(msgElement.getText(), header);
        // java generic classes

    }
    @Then("user observes label {int} is {string}")
     public void checkSecondLabel(int rowNumber,String expecedRowName)
    {

        theWebElement = theWebDriver.findElement(By.tagName("table"));
        List<WebElement> tableHeaders =  theWebElement.findElements(By.tagName("b"));
        for (WebElement aHeader : tableHeaders){
            LOG.debug(aHeader.getText());
        }
        Assert.assertEquals(tableHeaders.get(rowNumber-1).getText(),
                expecedRowName);
    }

    @And("User closes the browser")
    public void closeBrowser(){
        theWebDriver.close();
    }

}
