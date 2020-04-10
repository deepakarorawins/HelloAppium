package pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {
    AppiumDriver appiumDriver;

    @FindAll ({
    @FindBy(id="citySpinner"),
    @FindBy(id="tv_city_dropdown")})
    private WebElement dropDownCitySpinner;

    @FindBy(id="search_ET")
    private WebElement citySearchBox;

    @FindBy(id="city_name")
    private WebElement cityName;

    @FindBy(id="citySpinner")
    private WebElement mobileOrEmailField;

    public HomePage(AppiumDriver driver) {
        super(driver);
        this.appiumDriver=driver;
        PageFactory.initElements(appiumDriver, this);
    }


    public void selectCity(String city){
    dropDownCitySpinner.click();
    citySearchBox.click();
    citySearchBox.sendKeys(city);
    waitForElementToBeVisible(cityName);
    cityName.click();
    }
}
