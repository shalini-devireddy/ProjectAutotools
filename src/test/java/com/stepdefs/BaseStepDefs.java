package com.stepdefs;

import com.domain.User;
import com.oldstepdefs.StepDefinitions;
import com.pages.*;
import com.utils.ApplicationProperties;
import com.utils.TestDataProvider;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.apache.log4j.Logger;

public class BaseStepDefs {

    private static final Logger LOG = Logger.getLogger(StepDefinitions.class);

    TestDataProvider testDataProvider=new TestDataProvider();
    ApplicationProperties appProps = new ApplicationProperties();
    LoginPage theLoginPage;
    WelcomePage theWelcomePage;
    UserFormPage theUserFormPage;
    EmployeeDetailsPage theEmployeeDetails;
    RegionDetailsPage theRegionDetailsPage;
    EmployeeSearchPage theEmployeeSearchPage;
    CountriesInRegionPage theCountriesPage;
    BasePage theCurrentPage;
    LogoutPage theLogoutPage;

    @Given("User is on Login Page")
    public void getLoginPage()
    {
        theLoginPage = new LoginPage();
    }

    @Given("User enters the url in the browser")
    public void userOpensTheBrowser() {
        theLoginPage=new LoginPage();
        theCurrentPage = theLoginPage;
    }

    @And("User closes the browser")
    public void closeBrowser(){
        theCurrentPage.closeBrowser();
    }
}
