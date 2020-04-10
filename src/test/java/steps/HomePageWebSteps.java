package steps;

import io.appium.java_client.AppiumDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class HomePageWebSteps {
    private AppiumDriver appiumDriver;

    @When("I launch the Onekey mobile web app")
    public void iLaunchTheOnekeyMobileWebApp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("deviceName", "S9");
        desiredCapabilities.setCapability("browserName", "Chrome");

        URL remoteUrl = new URL("http://localhost:4723/wd/hub");
        appiumDriver = new AppiumDriver(remoteUrl, desiredCapabilities);
        appiumDriver.get("https://onekeystaging.milwaukeetool.com/app");
        //appiumDriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }

    @And("I choose to register")
    public void iChooseToRegister() {
        try { Thread.sleep(15*1000); }
        catch (InterruptedException e) { e.printStackTrace(); }
        appiumDriver.findElement(By.cssSelector(".auth0-lock-tabs a")).click();

    }

    @Then("I should see {string} button")
    public void iShouldSeeButton(String arg0) {
        Assert.assertTrue(appiumDriver.findElement(By.xpath("//button/span[text()= 'Sign Up']")).isDisplayed());
    }
}
