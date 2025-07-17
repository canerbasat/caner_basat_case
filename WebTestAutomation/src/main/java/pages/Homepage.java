package pages;

import helpers.Helper;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Driver;

public class Homepage {
    public Homepage() {
        PageFactory.initElements(Driver.get(), this);
    }

    @FindBy(id = "announce")
    public WebElement announceItem;

    @FindBy(xpath = "(//a[@class='d-flex flex-column flex-fill'])[12]")
    public WebElement careersColumn;

    @FindBy(id = "wt-cli-accept-btn")
    public WebElement cookiesOnlyNecessaryBtn;

    public void verifyHomepageIsOpen() {
        Helper.assertUrl("https://useinsider.com/");
        Helper.assertElementIsDisplayed(announceItem);
    }

    public void selectFromMenu(String menuItem) {
        Helper.clickElementByLinkText(menuItem);
    }

    public void clickCareers() {
        Helper.clickElementByLinkText("Careers");



    }

    public void closeCookies() {
        cookiesOnlyNecessaryBtn.click();
    }
}
