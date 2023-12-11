import com.juaracoding.Constants;
import com.juaracoding.Utils;
import com.juaracoding.driver.DriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class WebTestMultipleItem {

    static WebDriver driver;
    static Utils utils;
    static WebElement dismissBtn;
    static WebElement myAccountBtn;
    static WebElement usernameField;
    static WebElement passwordField;
    static WebElement loginBtn;
    static WebElement goHomeLink;
    static WebElement itemToBuy;
    static WebElement colorSelect;
    static WebElement sizeSelect;
    static WebElement btnIncreaseQty;
    static WebElement btnAddToCart;
    static WebElement btnSeeCart;
    DriverSingleton driverSingleton = DriverSingleton.getInstance(Constants.DRIVER_KEY_CHROME);
    boolean isFinished = false;

    String[] items = {"BLACK LUX GRAPHIC T-SHIRT", "PLAYBOY X MISSGUIDED PLUS SIZE GREY LIPS PRINT FRONT CROPPED T SHIRT", "PINK DROP SHOULDER OVERSIZED T SHIRT"};
    String[] itemSizes = {"36", "42", "36"};

    @BeforeClass
    public void setup() {
        System.getProperty(Constants.DRIVER_KEY_CHROME, Constants.PATH_CHROME_DRIVER);
        driver = driverSingleton.getDriver();
        driver.manage().window().maximize();
        driver.get(Constants.URL);
        utils = new Utils();
    }

    @Test
    public void testNavigateToLogin() {
        dismissBtn = utils.getClass(driver, "woocommerce-store-notice__dismiss-link");
        myAccountBtn = utils.getHref(driver, "https://shop.demoqa.com/my-account/");

        try {
            dismissBtn.click();
            myAccountBtn.click();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        String expectedUrl = "https://shop.demoqa.com/my-account/";
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);

    }

    @Test(dependsOnMethods = "testNavigateToLogin")
    public void testLogin() {
        try {
            loginBtn = utils.getName(driver, "login");
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", loginBtn);
            usernameField = utils.getId(driver, "username");
            passwordField = utils.getId(driver, "password");
            usernameField.sendKeys(Constants.USERNAME);
            passwordField.sendKeys(Constants.PASSWORD);
            loginBtn.click();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        String userNameDisplay = driver.findElement(By.tagName("strong")).getText();
        Assert.assertEquals(userNameDisplay, Constants.USERNAME);
    }

    @Test(dependsOnMethods = "testLogin")
    public void testMultipleItemSelect() {
        for (int i = 0; i < items.length; i++) {
            isFinished = i == items.length - 1;

            selectItem(items[i]);
            addToCart(itemSizes[i]);
        }
    }

    public void selectItem(String item) {
        goHomeLink = utils.getClass(driver, "home");
        goHomeLink.click();
        try {
            itemToBuy = utils.getLink(driver, item);
            for (int i = 0; i < 300; i++) {
                ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,4)", "");
            }
            Thread.sleep(200);
            itemToBuy.click();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void addToCart(String size) {
        try {
            colorSelect = utils.getId(driver, "pa_color");
            sizeSelect = utils.getId(driver, "pa_size");
            btnIncreaseQty = utils.getClass(driver, "qty-increase");
            btnAddToCart = utils.getClass(driver, "single_add_to_cart_button");
            for (int i = 0; i < 300; i++) {
                ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,3)", "");
            }

            colorSelect.click();
            utils.getClass(driver, "enabled").click();
            sizeSelect.click();
            utils.selectOption(driver, size).click();
            for (int i = 0; i < 19; i++) {
                btnIncreaseQty.click();
            }
            btnAddToCart.click();

            if (!isFinished) {
                goHomeLink = utils.getClass(driver, "home");
                goHomeLink.click();
            } else {
                btnSeeCart = utils.getClass(driver, "cart-button");
                btnSeeCart.click();

                for (int i = 0; i < 150; i++) {
                    ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,2)", "");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @AfterTest
    public void exitTest() {
        try {
            Thread.sleep(10000);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        driverSingleton.quitDriver();
    }
}
