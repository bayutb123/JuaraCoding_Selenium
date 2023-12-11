package com.juaracoding.driver.strategies.browser;

import com.juaracoding.Constants;
import com.juaracoding.driver.strategies.DriverStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class Firefox implements DriverStrategy {

    @Override
    public WebDriver setStrategy() {
        // DRIVER .EXE BELUM ADA
        System.getProperty(Constants.DRIVER_KEY_FIREFOX);

        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("--no-sandbox");

        return new FirefoxDriver(firefoxOptions);
    }
}
