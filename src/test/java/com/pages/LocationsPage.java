package com.pages;

import com.exceptions.UserNotFound;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

public class LocationsPage extends BasePage{

    private String countryName;
    private By xpathBy=By.xpath("//a[contains(text(),'"+countryName+"')]");
    public void goToLocationsInCountry(String countryName) throws UserNotFound {
        // TODO find region from the properties file countries
        // create a map of regions and countries and get the region from country
        // same as users now you have to for regions data
//        goToCountriesInRegion(userData.getRegionName(countryName));
        theWebElement=theWebDriver.findElement(xpathBy);
        theWebElement.click();
        FluentWait<WebDriver> wait = new FluentWait<>(theWebDriver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("footer")));
    }
}
