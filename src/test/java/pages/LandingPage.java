package pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import steps.BaseSteps;

public class LandingPage extends BasePage {
    AppiumDriver appiumDriver;

    @FindBy(id="skip")
    private WebElement lnkSkip;

    @FindBy(id="login_register_view")
    private WebElement txtMobileOrEmail;

    @FindBy(id="continue_login")
    private WebElement btnContinue;

    @FindBy(id="fb")
    private WebElement btnFb;

    @FindBy(id="sign_in_button")
    private WebElement btnGoogle;

    public LandingPage(AppiumDriver driver) {
        super(driver);
        this.appiumDriver = driver;
        PageFactory.initElements(appiumDriver, this);
    }

    public void skipToHomePage(){
        waitForElementToBeVisible(lnkSkip);
        lnkSkip.click();
    }

    public void registerByMobileOrEmail(String mobileOrEmail){
    txtMobileOrEmail.sendKeys(mobileOrEmail);
    btnContinue.click();
    }

    public void signInByFacebook(){
        btnFb.click();
    }

    public void signInByGoogle(){
        btnGoogle.click();
    }
}
