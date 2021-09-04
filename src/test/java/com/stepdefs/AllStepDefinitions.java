package com.stepdefs;

import com.pages.*;
import com.utils.ApplicationProperties;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class AllStepDefinitions {
    ApplicationProperties appProps = new ApplicationProperties();
    BasePage theCurrentPage;
    LoginPage theLoginPage;
    WelcomePage theWelcomePage;
    UserFormPage theUserFormPage;
    LogoutPage theLogoutPage;

    @Given("User is on Login Page")
    public void getLoginPage() {
        theLoginPage = new LoginPage();
        theCurrentPage = theLoginPage;
    }

    @Given("User enters the url in the browser")
    public void userOpensTheBrowser() {
        theLoginPage = new LoginPage();
        theCurrentPage = theLoginPage;
    }

    @Then("User should see the header {string}")
    public void checkHeader(String header) {
        Assert.assertEquals(theLoginPage.getHeader(), header);
    }

    @Then("User should see login page header font size is {string}")
    public void loginHeaderFont(String fontSize) {
        Assert.assertEquals(theLoginPage.getLoginHeaderFont(),fontSize);
    }

    @Then("User should see the label {string}")
    public void chkEmailLabel(String expectedLabel) {
       Assert.assertEquals(theLoginPage.getEmailLabel(),expectedLabel);
    }

    @Then("User should see the {string} label")
    public void checkPasswordLabel(String expectedLabel) {
        String actualLabel = theLoginPage.getPasswordLabel();
        Assert.assertEquals(actualLabel, expectedLabel);
    }

    @Then("User should see email input box")
    public void EmailInputBox() {
        Assert.assertTrue(theLoginPage.isEmailInputBoxPresent());
    }

    @Then("User should see password input box")
    public void passwordInputBox(){
        Assert.assertTrue(theLoginPage.isPasswordInputBox());
    }

    @When("User enters {string} as username")
    public void enterUsername(String aUserName) {
        theLoginPage.enterUsername(aUserName);
    }

    @And("User enters {string} as password")
    public void enterPwd(String aPassword) {

        theLoginPage.enterPwd(aPassword);
    }

    @And("User clicks Sign In")
    public void clickSignIn() {
        theLoginPage.clickSignIn();
    }

    @Then("User should see Welcome page")
    public void checkAndCloseWelcome() {
        theWelcomePage = new WelcomePage(theLoginPage);
        Assert.assertEquals(theWelcomePage.getWelcomeMsg(), appProps.getWelcomeMsg());
        theWelcomePage.takeScreenShot(theWelcomePage.WELCOME_PAGE);
    }

    @Then("User should see error message")
    public void checkErrorMsg() {
        theLoginPage.getErrorMsg();
    }

    @Then("User should see the login menu item")
    public void loginMenu() {
        theLoginPage.loginMenu();
    }

    @When("User clicks the login menu item")
    public void clickLogin() {
        theLoginPage.clickLogin();
    }

    @Then("User should see sign in button with background color {string}")
    public void verifyButtonColor(String buttonColor) {
        theLoginPage.verifyButtonColor(buttonColor);
        theLoginPage.takeScreenShot(theLoginPage.LOGIN_FAIL_ERROR_MSG);
        theLoginPage.closePage();
    }

    @Then("User should stay in login page")
    public void stayInLogin() {

    }

    @Then("User shoud see the footer {string} at the bottom of the page")
    public void seeFooter(String footer) {
        String actualFooter = null;
        actualFooter = theLoginPage.seeFooter();
        Assert.assertEquals(actualFooter, footer);

    }

    @And("User checks remember me check box")
    public void checkRememberMe() {
        theLoginPage.checkRememberMe();
        theLoginPage.takeScreenShot("rememberPage");
    }

    @And("user navigates to user form page from welcome page")
    public void goToUserForm() {
        theWelcomePage.clickMenuItem(BasePage.MYPROFILE);
        theUserFormPage = new UserFormPage(theWelcomePage);
        theCurrentPage = theUserFormPage;
    }

    @And("logout")
    public void logOut() {
        theWelcomePage.clickMenuItem(BasePage.LOGOUT);
        theLogoutPage = new LogoutPage(theWelcomePage);

    }

    @Then("User form direct call should take to login page with {string}")
    public void userFormToLogin(String loginHeader) {
        theUserFormPage.userFormToLogin(loginHeader);
    }

    @And("User closes the browser")
    public void closeBrowser() {
        theCurrentPage.closeBrowser();
    }

    @Then("User should see the header in bold")
    public void userShouldSeeTheHeaderInBold() {
        Assert.assertEquals(theLoginPage.getHeaderTag(),"h1");
    }

    @And("User is in Welcome page")
    public void welcomepage(){
        theWelcomePage = new WelcomePage(theLoginPage);
        theWelcomePage.getWelcomeMsg();
    }

    @Then("User should see the menubar on the top of the welcome page header")
    public void welcomePageMenuBar(){

    }


}
