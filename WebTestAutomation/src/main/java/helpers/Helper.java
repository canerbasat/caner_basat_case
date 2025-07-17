package helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.jupiter.api.Assertions;
import utils.Driver;

import java.time.Duration;

public class Helper {
    private static final WebDriver driver = Driver.get();
    private static WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    public static void assertUrl(String expectedUrl) {
        String currentUrl = driver.getCurrentUrl();
        Assertions.assertEquals(expectedUrl, currentUrl);
    }

    public static void assertElementIsDisplayed(WebElement element) {
        Assertions.assertTrue(element.isDisplayed());
    }



    public static void clickElementByLinkText(String linkText) {
        driver.findElement(By.linkText(linkText)).click();
    }


    public static void click(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }




    public static void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void waitForElement(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }


}
