package com.juaracoding.driver.strategies;

import com.juaracoding.Constants;
import com.juaracoding.driver.strategies.browser.Chrome;
import com.juaracoding.driver.strategies.browser.Firefox;
import org.openqa.selenium.WebDriver;

public class DriverStrategyImplementer {

    public static DriverStrategy getStrategy(String key) {
        switch (key) {
            case Constants.DRIVER_KEY_CHROME -> {
                return new Chrome();
            }

            case Constants.DRIVER_KEY_FIREFOX -> {
                return new Firefox();
            }

            default -> {
                return null;
            }
        }
    }

}
