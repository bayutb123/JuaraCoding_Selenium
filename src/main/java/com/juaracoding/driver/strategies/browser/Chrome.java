package com.juaracoding.driver.strategies.browser;

import com.juaracoding.Constants;
import com.juaracoding.driver.strategies.DriverStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Chrome implements DriverStrategy {

    @Override
    public WebDriver setStrategy() {
        System.getProperty(Constants.DRIVER_KEY_CHROME, Constants.PATH_CHROME_DRIVER);

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("useAutomationExtension", false);
        chromeOptions.addArguments("--no-sandbox");

        return new ChromeDriver(chromeOptions);
    }

}
