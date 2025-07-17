package steps;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import pages.CareerPage;
import pages.Homepage;

public class CaseStudyTests {

    static Hooks hooks = new Hooks();
    Homepage homepage = new Homepage();
    CareerPage careerPage = new CareerPage();

    @Test
    public void firstStep() {
        homepage.verifyHomepageIsOpen();
        homepage.closeCookies();
        homepage.selectFromMenu("Company");
        homepage.clickCareers();
        careerPage.assertCareerPageIsOpen();
        careerPage.assertTeams();
        careerPage.assertLocations();
        careerPage.assertLifeAtInsider();
        careerPage.clickSeeAllTeams();
        careerPage.clickQualityAssuranceTeam();
    }

    @BeforeAll
    public static void setup() {
        hooks.setDriver();
    }

    @AfterAll
    public static void teardown() {
        hooks.closeDriver();
    }
}
