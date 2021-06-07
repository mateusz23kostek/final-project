package tests;

import org.junit.jupiter.api.Test;
import pageobjects.BasePage;
import pageobjects.LoginPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LogoutTest extends BaseTest {
    @Test
    void shouldLogoutUserWhenSignOutButtonIsUsed() {
        BasePage basePage = new BasePage(driver, wait);
        basePage.open();
        LoginPage loginPage = basePage.goToLogin();
        loginPage.login("test@softie.pl", "1qaz!QAZ");
        basePage.clickOnSignOutButton();
        assertTrue(basePage.isSignInButtonDisplayed());
    }
}
