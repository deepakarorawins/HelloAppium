package pages;

import io.appium.java_client.AppiumDriver;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CarResultPage extends BasePage {
    AppiumDriver appiumDriver;

    @FindBy(id="Category")
    private WebElement categoryChooser;

    @FindBy(id="inspected_checkbox")
    private WebElement inspectedCheckbox;

    @FindBy(xpath="//android.widget.TextView[@text='SORT'")
    private WebElement softLink;

    @FindBy(xpath="//android.widget.TextView[@text='FILTER'")
    private WebElement filterLink;

    @FindBy(id="cars_ad_list_title_tv")
    private List<WebElement> searchResultText;


    public CarResultPage(AppiumDriver driver) {
        super(driver);
        this.appiumDriver=driver;
        PageFactory.initElements(appiumDriver, this);
    }

    // Approch 1: -
    public void verifySearchResult(String text) {
        for (WebElement result : searchResultText) {
            Assert.assertTrue(result.getText().contains(text));
        }
    }

    // Approch 2: - Implementing assertion in test script
    public String getFirstSearchResult(){
        return searchResultText.get(0).getText();
    }






}
