package com.stepdefs;

import com.domain.User;
import com.exceptions.UserNotFound;
import com.google.common.collect.ImmutableSet;
import com.pages.*;
import com.utils.UserDataProvider;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.testng.Assert;

import java.util.Collections;

public class StepDefinitions {

    private static final Logger LOG = Logger.getLogger(StepDefinitions.class);

    UserDataProvider userData = new UserDataProvider();
    LoginPage theLoginPage;
    WelcomePage theWelcomePage;
    UserFormPage theUserFormPage;
    EmployeeDetailsPage theEmployeeDetails;
    RegionDetailsPage theRegionDetailsPage;
    EmployeeSearchPage theEmployeeSearchPage;
    BasePage theCurrentPage;
    String theFirstName;
    String theFormError;
    String theFirstNameError;
    User validUser;


    @Given("User is on Login Page")
    public void getLoginPage(){
        theLoginPage = new LoginPage();
    }
    @When("User enters {string} as username")
    public void enterUsername(String aUserName){
        theLoginPage.enterUsername(aUserName);
    }
    @And("User enters {string} as password")
    public void enterPwd(String aPassword){
        theLoginPage.enterPwd(aPassword);
    }
    @And("User checks remember me check box")
    public void checkRememberMe(){
          theLoginPage.checkRememberMe();
    }
    @And("User clicks Sign In")
    public void clickSignIn(){
        theLoginPage.clickSignIn();
    }

    @Then("User should see Welcome page")
    public void checkAndCloseWelcome(){
        theWelcomePage = new WelcomePage(theLoginPage);
        //Assert.assertEquals(theWelcomePage.getWelcomeMsg(), theWelcomePage.WELCOME_MESSAGE);//doubt
        Assert.assertEquals(theWelcomePage.getWelcomeMsg(),userData.getWelcomeMsg());
        theWelcomePage.takeScreenShot(theWelcomePage.WELCOME_PAGE);
        theWelcomePage.closePage();
    }

    @Then("User should see error message")
    public void checkErrorMessage(){
        Assert.assertEquals(theLoginPage.getErrorMsg(),theLoginPage.LOGIN_FAIL_ERROR_MSG);
        theLoginPage.closePage();
    }
    @Then("User should see sign in button with background color {string}")
    public void verifyButtonColor(String buttonColor){
        theLoginPage.verifyButtonColor(buttonColor);
        theLoginPage.takeScreenShot(theLoginPage.LOGIN_FAIL_ERROR_MSG);
        theLoginPage.closePage();
    }
//    //User form
    @Given("User logged in as {string}")
    public void loginUser(String userName) throws UserNotFound {
        theLoginPage =  new  LoginPage();
        validUser = userData.getValidUser(userName);
        theLoginPage.loginUser(validUser.getUserName(), validUser.getPassword());
        // Welcome message get from a properties files (done)
        theWelcomePage = new WelcomePage(theLoginPage);
        Assert.assertEquals(theWelcomePage.getWelcomeMsg(), userData.getWelcomeMsg());
    }
    @And("the same user to login with old password")
    public void loginWithOldPwd() throws UserNotFound {

        theLoginPage = new  LoginPage();
        theLoginPage.loginUser(validUser.getUserName(), validUser.getPassword());

    }
    @And("user navigates to user form page from welcome page")
    public void goToUserForm(){
        theWelcomePage.clickMenuItem(BasePage.MYPROFILE);
        theUserFormPage = new UserFormPage(theWelcomePage);
    }
    @When("User inspects the first name field value")
    public void inspectFirstName(){
        theFirstName = theUserFormPage.inspectFirstName();
    }
    @And("the first name field value should be {string}")
    public void validateFirstName(String expectedFirstName){
        Assert.assertEquals(theFirstName,expectedFirstName);
        theUserFormPage.closePage();
    }
    @And("logout")
    public void logOut(){
       theUserFormPage.logOut();
    }
    @Then("User form direct call should take to login page with {string}")
    public void userFormToLogin(String loginHeader){
        theUserFormPage.userFormToLogin(loginHeader);
    }
//    @Then("User form direct call should take to userform page {string}")
//    public void directToUserForm(String userFormHeader){
//        callUserFormDirect();
//        FluentWait<WebDriver> wait = new FluentWait<>(theWebDriver);
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("strong")));
//        WebElement userFormElement=theWebElement.findElement(By.tagName("h1"));
//        // TODO get userformheader text from UserDataprovider.
//        Assert.assertEquals(userFormElement.getText(),userFormHeader);
//    }
//
    private void callUserFormDirect(){
        theUserFormPage.callUserFormDirect();
    }

    @And("Change the password as {string}")
    public void changePassword(String newPassword) {
        theUserFormPage.enterPwd(newPassword);
        theUserFormPage.enterVerifyPwd(newPassword);
        theUserFormPage.clickSave();
        theUserFormPage.getSuccessMsg();
        //TODO get success msg from file
        LOG.debug(theUserFormPage.getSuccessMsg());
    }

    @And("Change the password to new password")
    public void changePassword() {
        theUserFormPage.enterPwd(validUser.getNewPwd());
        theUserFormPage.enterVerifyPwd(validUser.getNewPwd());
        theUserFormPage.clickSave();
        theUserFormPage.getSuccessMsg();
        //TODO get success msg from file
        LOG.debug(theUserFormPage.getSuccessMsg());
    }

    @And("Check error with old password")
    public void errorCheckWithOldPwd()throws UserNotFound{
        // TODO get error message from file.
//       Assert.assertEquals(theLoginPage.getErrorMsg(), "");
        LOG.debug(theLoginPage.getErrorMsg());
        theLoginPage.closePage();
    }

    @And("the same user logs in with new pwd")
    public void enterNewPwd(){
        theLoginPage = new LoginPage();
        theLoginPage.enterUsername(validUser.getUserName());
        theLoginPage.enterPwd(validUser.getNewPwd());
        theLoginPage.clickSignIn();
        theWelcomePage = new WelcomePage(theLoginPage);
        userData.flipPasswords(validUser.getUserName());

        Assert.assertEquals(theWelcomePage.getWelcomeMsg(),userData.getWelcomeMsg());

        theWelcomePage.closePage();
    }


    @And("user clears the FirstName field")
    public void clearFirstName(){
        theUserFormPage.clearFirstName();
    }
    @And("user clicks save button")
    public void clickSave(){
        theUserFormPage.clickSave();
    }

    @Then("an error message {string} should show on top of the table")
    public void formError(String errorMsg){
        theFormError=theUserFormPage.getFormError();
//        Assert.assertEquals(theFormError,userData.getFormError());
        Assert.assertEquals(theFormError, errorMsg);
    }

    @And("an error message should show next to firstname field")
    public void getFirstNameError(){
        theFirstNameError=theUserFormPage.getFirstNameError();
        // Assert.assertEquals(theUserFormPage.getFirstNameError(),firstNameError);
        Assert.assertEquals(theFirstNameError,userData.getFirstNameError());
        theUserFormPage.closePage();
    }

    //employee details
    @Given("Valid User is on employee details page")
    public void goToEmpDetails() throws UserNotFound{
        theLoginPage= new LoginPage();
        validUser = userData.getValidUser();
        theLoginPage.loginUser(validUser.getUserName(), validUser.getPassword());
        theWelcomePage= new WelcomePage(theLoginPage);
        Assert.assertEquals(theWelcomePage.getWelcomeMsg(),userData.getWelcomeMsg());

        theWelcomePage.clickMenuItem(BasePage.ALL_EMPLOYEE_DETAILS);

        theEmployeeDetails=new EmployeeDetailsPage(theWelcomePage);
        theCurrentPage = theEmployeeDetails;
        //theEmployeeDetails.goToEmpDetails();
    }

    @And("user observes {int} column is {string}")
    public void inspectSecondColumn(int columnNumber, String expectedColumnName){
        theCurrentPage.inspectSecondColumn(columnNumber,expectedColumnName);
       // Assert.assertEquals(tableHeaders.get(columnNumber-1).getText(),expectedColumnName);
        theCurrentPage.closePage();
    }
    @Then("{int} employees should display in employees table")
    public void employeeCount(int totalEmployees){
       theEmployeeDetails.employeeCount();
        Assert.assertEquals(theEmployeeDetails.employeeCount(),totalEmployees);
    }

    @Then("user observes the last employee name is {string}")
    public void vrifyLastEmployee(String employeeName){


    }
    //Region details
    @Given("Valid User is on region details page")
    public void goToRegionDetails() throws UserNotFound {

        theLoginPage=new LoginPage();
        validUser = userData.getValidUser();
        theLoginPage.loginUser(validUser.getUserName(), validUser.getPassword());
        theWelcomePage = new WelcomePage(theLoginPage);
        theWelcomePage.clickMenuItem(BasePage.REGIONS);
        theRegionDetailsPage = new RegionDetailsPage(theWelcomePage);
        theCurrentPage = theRegionDetailsPage;

    }
//    //Countries in region
    @Given("Valid User is on countries in {string} region page")
    public void goToCountriesInRegion(String regionName) throws UserNotFound{
        goToRegionDetails();

    }
//    //locations in country
//    @Given("Valid User navigates to locations in country {string}")
//     public void goToLocationsInCountry(String countryName) throws UserNotFound{
//        // TODO find region from the properties file countries
//        // create a map of regions and countries and get the region from country
//        // same as users now you have to for regions data
//        goToCountriesInRegion(userData.getRegionName(countryName));
//        theWebElement=theWebDriver.findElement(By.xpath("//a[contains(text(),'"+countryName+"')]"));
//        theWebElement.click();
//        FluentWait<WebDriver> wait = new FluentWait<>(theWebDriver);
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("footer")));
//     }
//     //Departments in location
//    @Given("Valid User is on departments in location page")
//    public void goToDepertmentsInLocations() throws UserNotFound{
//
//        goToLocationsInCountry("");
//        theWebElement=theWebDriver.findElement(By.xpath("//a[contains(text(),'2500')]"));
//        theWebElement.click();
//        FluentWait<WebDriver> wait = new FluentWait<>(theWebDriver);
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("footer")));
//    }

//    //Employee search
    @Given("Valid user is on employee search page")
     public void employeeSearch() throws UserNotFound{
       // goToHomePage();
       theLoginPage=new LoginPage();
        validUser = userData.getValidUser();
        theLoginPage.loginUser(validUser.getUserName(), validUser.getPassword());
        theWelcomePage= new WelcomePage(theLoginPage);
        theWelcomePage.clickMenuItem(BasePage.EMPLOYEE_SEARCH);

        //Assert.assertEquals(theWelcomePage.getWelcomeMsg(),userData.getWelcomeMsg());
        theEmployeeSearchPage= new EmployeeSearchPage(theWelcomePage);
        theCurrentPage=theEmployeeSearchPage;

    }

//    @When("User enters {string} as first name")
//    public void enterFirstName(String firstName){
//
//        theEmployeeSearchPage.enterFirstName(firstName);
//    }
//
//    @And("User enters {string} as last name")
//    public void enterLastName(String lastName){
//        theEmployeeSearchPage.enterLastName(lastName);
//    }
//
//    @And("User clicks search")
//    public void clickSearch(){
//        theEmployeeSearchPage.clickSearch();
//    }
//
//    @Then("User should see employee page with header {string}")
//    public void empHeader(String header){
//       theEmployeeSearchPage.checkHeader(header);
//    }


//    private void checkHeader(String header){
//        theEmployeeSearchPage.checkHeader(header);
//    }
//    @Then("user observes label {int} is {string}")
//     public void checkSecondLabel(int rowNumber,String expecedRowName)
//    {
//
//        theEmployeeSearchPage.checkSecondLabel(rowNumber,expecedRowName);
//    }

}
