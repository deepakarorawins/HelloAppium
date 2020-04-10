package steps;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;


public class StartingSteps extends BaseSteps {

    private AppiumDriverLocalService appiumService;

    @Before
    public void startAppiumServer() throws MalformedURLException {
        int port = 4723;
        String nodeJS_Path = "C:/Program Files/NodeJS/node.exe";
        String appiumJS_Path = "C:/Program Files/Appium/node_modules/appium/bin/appium.js";
        String osName = System.getProperty("os.name");

        if(osName.contains("Mac")) {
            appiumService = AppiumDriverLocalService.buildService(
                    new AppiumServiceBuilder()
                            .usingDriverExecutable(new File("/usr/local/bin/node"))
                            .withAppiumJS(new File("/usr/local/bin/appium"))
                            .withIPAddress("0.0.0.0")
                            .usingPort(port)
                            .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
                            .withLogFile(new File("build/appium.log"))
            );
        } else if(osName.contains("Windows")){
            appiumService = AppiumDriverLocalService.buildService(
                    new AppiumServiceBuilder()
                            .usingDriverExecutable(new File(nodeJS_Path))
                            .withAppiumJS(new File(appiumJS_Path))
                            .withIPAddress("0.0.0.0")
                            .usingPort(port)
                            .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
                            .withLogFile(new File("build/appium.log"))
            );
        }
        appiumService.start();

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("platformVersion", "9.0");
        desiredCapabilities.setCapability("deviceName", "S9");
        desiredCapabilities.setCapability("automationName", "UiAutomator2");
        desiredCapabilities.setCapability("noReset", "false");
        desiredCapabilities.setCapability("appPackage", "com.quikr");
        desiredCapabilities.setCapability("appActivity", "com.quikr.old.SplashActivity");
        desiredCapabilities.setCapability("newCommandTimeout", 120);
        // Below to launch the Android Virtual Device and waits for 120 seconds.
        desiredCapabilities.setCapability("avd", "Pixel_2_API_28");
        desiredCapabilities.setCapability("avdReadyTimeout", 120000);


        URL remoteUrl = new URL("http://localhost:4723/wd/hub");
        appiumDriver = new AppiumDriver(remoteUrl, desiredCapabilities);
        appiumDriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }


    @After
    public void stopAppiumServer(){
        appiumDriver.quit();
        appiumService.stop();
    }
}
