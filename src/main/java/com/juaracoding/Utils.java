package com.juaracoding;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Utils {
    public WebElement getHref(WebDriver driver, String value) {
        return driver.findElement(By.cssSelector("a[href='" + value + "']"));
    }

    public WebElement getLink(WebDriver driver, String value) {
        return driver.findElement(By.linkText(value));
    }

    public WebElement selectOption(WebDriver driver, String value) {
        return driver.findElement(By.cssSelector("option[value='" + value +"']"));
    }

    public WebElement getClass(WebDriver driver, String value) {
        return driver.findElement(By.className(value));
    }

    public WebElement getId(WebDriver driver, String value) {
        return driver.findElement(By.id(value));
    }

    public WebElement getName(WebDriver driver, String value) {
        return driver.findElement(By.name(value));
    }
}
