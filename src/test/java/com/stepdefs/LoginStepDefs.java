package com.stepdefs;

import com.pages.LoginPage;
import io.cucumber.java.en.Then;
import org.testng.Assert;

public class LoginStepDefs {

    LoginPage theLoginPage;

    @Then("User should see the header {string}")
    public void checkHeader(String header) {
        theLoginPage = new LoginPage();
        Assert.assertEquals(theLoginPage.getHeader(), header);
    }

    @Then("User should see the header in bold")
    public void userShouldSeeTheHeaderInBold() {
        Assert.assertEquals(theLoginPage.getHeaderTag(),"h1");
    }
}
