package com.example.myapplication;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;

import static org.junit.Assert.*;



public class MultiTouchTest {

    AndroidDriver<WebElement> driver;
    Dimension size;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("udid", "emulator-5554");
        capabilities.setCapability("deviceName","Pixel 3 API 26");
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("appPackage","com.the511plus.MultiTouchTester");
        capabilities.setCapability("appActivity","com.the511plus.MultiTouchTester.MultiTouchTester");

        
        driver =  new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }
    @Test
    public void mulitTouchAction() {
        size = driver.manage().window().getSize();
        
        int x1 = (int) (size.width * 0.20);
        int y1 = (int) (size.height * 0.20);

        
        int x2 = (int) (size.width * 0.80);
        int y2 = (int) (size.height * 0.20);

        
        int x3 = (int) (size.width * 0.20);
        int y3 = (int) (size.height * 0.80);

        
        int x4 = (int) (size.width * 0.80);
        int y4 = (int) (size.height * 0.80);

       
        int x5 = size.width / 2;
        int y5 = size.height / 2;

       
        MultiTouchAction maction = new MultiTouchAction((AndroidDriver) driver);
       
        TouchAction action1 = new TouchAction((AndroidDriver) driver).longPress(PointOption.point(x1, y1));
       
        TouchAction action2 = new TouchAction((AndroidDriver) driver).longPress(PointOption.point(x2, y2));
       
        TouchAction action3 = new TouchAction((AndroidDriver) driver).longPress(PointOption.point(x3, y3));
       
        TouchAction action4 = new TouchAction((AndroidDriver) driver).longPress(PointOption.point(x4, y4));
       
        TouchAction action5 = new TouchAction((AndroidDriver) driver).longPress(PointOption.point(x5, y5));

       
        maction.add(action1).add(action2).add(action3).add(action4).add(action5).perform();
    }

    @After
    public void endTest() throws IOException {
        Date d = new Date();
        String screenshotFile=d.toString().replace(":", "_").replace(" ","_")+".png";
        File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String path= System.getProperty("user.dir")+ "\\screenshots\\" + screenshotFile;
        FileUtils.copyFile(srcFile, new File(path));
        driver.quit();
    }
}