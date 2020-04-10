package steps;

import io.appium.java_client.AppiumDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.*;
import pages.CarResultPage;
import pages.HomePage;
import pages.LandingPage;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class HomePageSteps extends BaseSteps {

    @When("I launch the Onekey app")
    public void iLaunchTheOnekeyApp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("platformVersion", "9.0");
        desiredCapabilities.setCapability("deviceName", "S9");
        desiredCapabilities.setCapability("app", "/Users/deepakarora/git/HelloAppium/app/onekey-naStage-internalRelease.apk");
        desiredCapabilities.setCapability("noReset", "false");
        desiredCapabilities.setCapability("fullReset", "true");
        desiredCapabilities.setCapability("newCommandTimeout", 120);
        desiredCapabilities.setCapability("automationName", "UiAutomator2");

        desiredCapabilities.setCapability("appPackage", "com.milwaukeetool.mymilwaukee_STAGE");
        desiredCapabilities.setCapability("appActivity", "com.milwaukeetool.mymilwaukee.activity.RouterActivity");

        // Below to launch the Android Virtual Device and waits for 120 seconds.
        desiredCapabilities.setCapability("avd", "Pixel_2_API_28");
        desiredCapabilities.setCapability("avdReadyTimeout", 120000);


        URL remoteUrl = new URL("http://localhost:4723/wd/hub");
        appiumDriver = new AppiumDriver(remoteUrl, desiredCapabilities);
        appiumDriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }


    @Then("I see Home page screen with {string} button")
    public void iSeeHomePageScreenWithButton(String expected) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assert.assertEquals("Button text matches", expected, appiumDriver.findElement(By.id("tvSignIn")).getText());

    }


    @When("I launch the Quikr app")
    public void iLaunchTheQuikrApp() throws MalformedURLException {
        appiumDriver.findElement(By.xpath("//android.widget.Button[@text='ACCEPT']")).click();
        appiumDriver.findElement(By.id("login_register_view")).isDisplayed();
    }

    @And("I choose {string} as my city")
    public void iChooseAsMyCity(String city) throws InterruptedException {
        new LandingPage(appiumDriver).skipToHomePage();
        appiumDriver.findElement(By.xpath("//android.widget.Button[@text='DENY']")).click();
        try {
            if (appiumDriver.findElement(By.xpath("//android.widget.Button[@text='Later']")).isDisplayed()) {
                appiumDriver.findElement(By.xpath("//android.widget.Button[@text='Later']")).click();
            }
        } catch (Exception e) {
            // Do nothing
        }
        new HomePage(appiumDriver).selectCity(city);


    }


    @And("I search for {string} under Used Cars")
    public void iSearchForUnderUsedCars(String carName) {
        appiumDriver.findElement(By.xpath("//android.widget.TextView[@text='Cars']")).click();
        appiumDriver.findElement(By.id("menu_ad_list_search")).click();

        // appiumDriver.findElement(By.id("search_ET")).click();
        appiumDriver.findElement(By.id("search_ET")).sendKeys(carName);
        WebDriverWait wait = new WebDriverWait(appiumDriver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("suggestion")));
        List<WebElement> results = appiumDriver.findElements(By.id("suggestion"));
        for (WebElement result : results) {
            if (result.getText().contains(carName)) {
                result.click();
                break;
            }
        }


    }

    @Then("I should see the first car search result with {string}")
    public void iShouldSeeTheFirstCarSearchResultWith(String searchInput) {
        String searchResult = new CarResultPage(appiumDriver).getFirstSearchResult();
        Assert.assertTrue(searchResult.startsWith(searchInput));
    }

}
