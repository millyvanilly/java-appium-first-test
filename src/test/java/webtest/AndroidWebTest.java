package webtest;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileBrowserType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class AndroidWebTest {
    ChromeOptions chromeOptions;
    AndroidDriver driver = null;
    DesiredCapabilities dc = new DesiredCapabilities();
    String testName = "Testing Website on Android Chrome with Java";
    String accessKey = System.getenv("SEETEST_IO_ACCESS_KEY");

    @Before
    public void setUp() throws MalformedURLException {
        dc.setCapability("testName", testName);
        dc.setCapability("accessKey",accessKey);
        dc.setBrowserName(MobileBrowserType.CHROME);
        driver = new AndroidDriver(new URL("https://telstra.experitest.com:443/wd/hub"),dc);
    }

    @Test
    public void testYourAndroidApp() throws InterruptedException {
        driver.get("https://amazon.com");
        System.out.println(driver.getTitle());
        if( driver.getCapabilities().getCapability("device.category").equals("TABLET")){

            driver.findElement(By.xpath("//*[@name='field-keywords']")).sendKeys("iPhone");
            driver.findElement(By.xpath("//*[@text='Go']")).click();
        }
        else{
            driver.findElement(By.xpath("//*[@name='k']")).sendKeys("iPhone");
            driver.findElement(By.xpath("//*[@value='Go']")).click();
        }
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
