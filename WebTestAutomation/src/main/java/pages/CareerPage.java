package pages;

import helpers.Helper;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Driver;

public class CareerPage {
    public CareerPage() {
        PageFactory.initElements(Driver.get(), this);
    }

    public void assertCareerPageIsOpen() {
        Helper.assertUrl("https://useinsider.com/careers/");
    }

    @FindBy(id = "career-find-our-calling")
    public WebElement teamsSection;

    @FindBy(linkText = "See all teams")
    public WebElement seeAllTeamsBtn;

    @FindBy(id = "career-our-location")
    public WebElement locationSection;

    @FindBy(xpath = "//section[@data-id='a8e7b90']")
    public WebElement lifeAtInsiderSection;


    @FindBy(xpath = "//h3[text()='Quality Assurance']")
    public WebElement qualityAssuranceTeam;

    public void assertTeams() {
        Helper.assertElementIsDisplayed(teamsSection);
    }

    public void assertLocations() {
        Helper.assertElementIsDisplayed(locationSection);
    }

    public void assertLifeAtInsider() {
        Helper.assertElementIsDisplayed(lifeAtInsiderSection);
    }

    public void clickSeeAllTeams() {
        Helper.click(seeAllTeamsBtn);
    }

    public void clickQualityAssuranceTeam() {
        Helper.scrollToElement(qualityAssuranceTeam);
        Helper.click(qualityAssuranceTeam);
    }

}
