package apptest;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.net.URL;

public class AndroidAppTest {

    AndroidDriver driver = null;
    DesiredCapabilities dc = new DesiredCapabilities();
    String testName = "Testing Android App with Java";
    String accessKey = System.getenv("SEETEST_IO_ACCESS_KEY");

    @Before
    public void setUp() throws IOException {
        dc.setCapability("testName", testName);
        dc.setCapability("accessKey", accessKey);
        //install the app on the device
        dc.setCapability(MobileCapabilityType.APP, "cloud:com.belong.sit/com.belong.MainActivity");
        //get an Android device
        dc.setCapability("platformName", "Android");
        //launch the app
        dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.belong.sit");
        dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".MainActivity");
        driver = new AndroidDriver(new URL("https://telstra.experitest.com:443/wd/hub"), dc);
    }

    @Test
    public void testYourAndroidApp() throws InterruptedException {
        Thread.sleep(3000);
        MobileElement createAccountBtn = (MobileElement) driver.findElementByAccessibilityId("Create account");
        createAccountBtn.click();
        Thread.sleep(3000);

        //MobileElement text = (MobileElement) driver.findElementById("com.belong.at:id/account-given-name-input");
        MobileElement firstNameTxt = (MobileElement) driver.findElementByAccessibilityId("Given name. Required");
        firstNameTxt.click();
        firstNameTxt.sendKeys("Firstname");

        MobileElement lastNameTxt = (MobileElement) driver.findElementByAccessibilityId("Surname. required");
        lastNameTxt.click();
        lastNameTxt.sendKeys("Lastname");

        MobileElement element = (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true).instance(0))" +
                        ".scrollIntoView(new UiSelector().text(\"Password\"))"));

        MobileElement dobTxt = (MobileElement) driver.findElementByAccessibilityId("Date of birth. Must be entered as D D forward slash. M M forward slash. Y Y Y Y. Required., You must be over 18 to create an account. We may also use this to verify your identity.");
        dobTxt.click();
        dobTxt.sendKeys("12/12/2000");
        driver.hideKeyboard();
        Thread.sleep(3000);

        MobileElement emailTxt = (MobileElement) driver.findElementByAccessibilityId("Email address. required");
        //MobileElement emailTxt = (MobileElement) driver.findElement(By.id("account-email-input")); //- can't locate
        //MobileElement emailTxt = (MobileElement) driver.findElementById("account-email-input"); //- can't locate
        //MobileElement emailTxt = (MobileElement) driver.findElementById("com.belong.at:id/account-email-input"); //- can't locate
        emailTxt.click();
        emailTxt.sendKeys("appiumTest@mailinator.com");
        driver.hideKeyboard();
        Thread.sleep(3000);

        MobileElement passwordTxt = (MobileElement) driver.findElementByAccessibilityId("Password. required, Passwords must contain at least 8 characters with a mix of uppercase and lowercase letters (a to z), numbers (0 to 9) and special characters. (exclamation mark, @ sign, # sign, $ sign, % sign, ^ sign, & sign, * sign)");
        passwordTxt.click();
        passwordTxt.sendKeys("Password@1234");
        driver.hideKeyboard();
        Thread.sleep(3000);

        MobileElement submitBtn = (MobileElement) driver.findElementByAccessibilityId("Create account");
        submitBtn.click();
        submitBtn.click();
        Thread.sleep(3000);
    }

    @After
    public void tearDown() {
        if (driver != null)
        {
            driver.quit();
            System.out.println("Report URL : " + driver.getCapabilities().getCapability("reportUrl"));

        }
    }

}
