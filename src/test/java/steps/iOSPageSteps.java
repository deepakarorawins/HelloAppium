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

public class iOSPageSteps {
    private AppiumDriver appiumDriver;

    @When("I launch ios app")
    public void iLaunchIosApp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "iOS");
        desiredCapabilities.setCapability("platformVersion", "13.4");
        desiredCapabilities.setCapability("deviceName", "iPhone 11 Pro Max");
        desiredCapabilities.setCapability("app", "/Users/deepakarora/git/HelloAppium/app/ios/TestApp-iphonesimulator.app");
        //desiredCapabilities.setCapability("automationName", "UiAutomator1");
       // desiredCapabilities.setCapability("noReset", "true");
        //desiredCapabilities.setCapability("appPackage", "com.milwaukeetool.mymilwaukee_STAGE");
        //desiredCapabilities.setCapability("appActivity", "com.milwaukeetool.mymilwaukee.activity.RouterActivity");


        URL remoteUrl = new URL("http://localhost:4723/wd/hub");
        appiumDriver = new AppiumDriver(remoteUrl, desiredCapabilities);
        appiumDriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }

    @And("I choose to enter {string} and {string}")
    public void iChooseToEnterAnd(String num1, String num2) {
        appiumDriver.findElement(By.id("IntegerA")).sendKeys(num1);
        appiumDriver.findElement(By.id("IntegerB")).sendKeys(num2);
    }



    @And("I tap on Compute Sum")
    public void iTapOnComputeSum() {
        appiumDriver.findElement(By.id("ComputeSumButton")).click();
    }

    @Then("I should see the result {string}")
    public void iShouldSeeTheResult(String result) {
        Assert.assertEquals(result, appiumDriver.findElement(By.id("Answer")).getText());
    }
}
