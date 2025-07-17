package steps;


import org.openqa.selenium.WebDriver;
import utils.Driver;
import java.time.Duration;

public class Hooks {
    public static WebDriver driver;

    public void setDriver() {
        driver = Driver.get();
        driver.get("https://useinsider.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    public void closeDriver() {
        Driver.closeDriver();
    }



}
