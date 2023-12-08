package com.juaracoding;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    static WebDriver driver;
    static Utils utils;
    private static final String PATH = "C:\\MyTools\\chromedriver.exe";
    private static final String URL = "https://shop.demoqa.com/";
    private static final String DRIVER_KEY = "webdriver.chrome.driver";
    private static final String USERNAME = "bayutb123";
    private static final String PASSWORD = "bayutb123";
    static WebElement dismissBtn;
    static WebElement myAccountBtn;
    static WebElement usernameField;
    static WebElement passwordField;
    static WebElement loginBtn;
    static WebElement itemToBuy;
    static WebElement colorSelect;
    static WebElement sizeSelect;
    static WebElement btnIncreaseQty;
    static WebElement btnAddToCart;

    public static void main(String[] args) {
        System.getProperty(DRIVER_KEY, PATH);

        driver = new ChromeDriver();
        utils = new Utils();
        driver.get(URL);
        driver.manage().window().maximize();

        dismissBtn = utils.getClass(driver, "woocommerce-store-notice__dismiss-link");
        myAccountBtn = utils.getHref(driver, "https://shop.demoqa.com/my-account/");

        navigateToMyAccount();

        usernameField = utils.getId(driver, "username");
        passwordField = utils.getId(driver, "password");
        loginBtn = utils.getName(driver, "login");

        login();

        itemToBuy = utils.getLink(driver, "PLAYBOY X MISSGUIDED PLUS SIZE GREY LIPS PRINT FRONT CROPPED T SHIRT");

        addToCart();

        colorSelect = utils.getId(driver, "pa_color");
        sizeSelect = utils.getId(driver, "pa_size");
        btnIncreaseQty = utils.getClass(driver, "qty-increase");
        btnAddToCart = utils.getClass(driver, "single_add_to_cart_button");

        confirmAddToCart();

        seeCart();

        driver.quit();
    }

    public static void navigateToMyAccount() {
        try {
            dismissBtn.click();
            myAccountBtn.click();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void login() {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", loginBtn);
            usernameField.sendKeys(USERNAME);
            passwordField.sendKeys(PASSWORD);
            loginBtn.click();
            driver.get("https://shop.demoqa.com/");
            for (int i = 0; i < 300; i++) {
                ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,4)", "");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void addToCart() {
        try {
            Thread.sleep(200);
            itemToBuy.click();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void confirmAddToCart() {
        try {
            for (int i = 0; i < 300; i++) {
                ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,3)", "");
            }
            colorSelect.click();
            utils.selectOption(driver, "grey").click();
            sizeSelect.click();
            utils.selectOption(driver, "42").click();
            for (int i = 0; i < 19; i++) {
                btnIncreaseQty.click();
            }
            btnAddToCart.click();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void seeCart() {
        try {
            driver.get("https://shop.demoqa.com/cart/");
            for (int i = 0; i < 150; i++) {
                ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,2)", "");
            }
            Thread.sleep(10000);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}