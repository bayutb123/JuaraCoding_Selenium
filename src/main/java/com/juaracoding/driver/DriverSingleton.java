package com.juaracoding.driver;

import com.juaracoding.driver.strategies.DriverStrategy;
import com.juaracoding.driver.strategies.DriverStrategyImplementer;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

@SuppressWarnings("deprecation")
public class DriverSingleton {

    private static DriverSingleton instance = null;
    private WebDriver webDriver;

    private DriverSingleton(String key) {
        instantiate(key);
    }

    public static synchronized DriverSingleton getInstance(String key) {
        if (instance == null) {
            instance = new DriverSingleton(key);
        }
        return instance;
    }

    public WebDriver getDriver() {
        if (webDriver == null) {
            throw new IllegalStateException("WebDriver has not been instantiated. Call instantiate() first.");
        }
        return webDriver;
    }

    private void instantiate(String key) {
        if (webDriver == null) {
            DriverStrategy driverStrategy = DriverStrategyImplementer.getStrategy(key);
            if (driverStrategy != null) {
                webDriver = driverStrategy.setStrategy();
                webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                webDriver.manage().window().maximize();
            } else {
                throw new IllegalArgumentException("Invalid driver key: " + key);
            }
        }
    }

    public void quitDriver() {
        if (webDriver != null) {
            webDriver.quit();
            webDriver = null;
        }
    }
}
