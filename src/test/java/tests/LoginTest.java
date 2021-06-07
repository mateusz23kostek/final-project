package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pageobjects.HomePage;
import pageobjects.LoginPage;

public class LoginTest extends BaseTest {

    @Test
    void shouldDisplayEmailRequiredAlertWhenNoEmailIsProvided() {
        HomePage homePage = new HomePage(driver, wait);
        homePage.open();
        LoginPage loginPage = homePage.goToLogin();
        loginPage.login("", "1qaz!QAZ");
        Assertions.assertTrue(loginPage.isEmailRequiredAlertDisplayed(),
                "Email Required Alert is not Displayed");
    }

    @Test
    void shouldDisplayEmailRequiredAlertWhenNoEmailAndNoPasswordIsProvided() {
        HomePage homePage = new HomePage(driver, wait);
        homePage.open();
        LoginPage loginPage = homePage.goToLogin();
        loginPage.login("", "");
        Assertions.assertTrue(loginPage.isEmailRequiredAlertDisplayed(),
                "Email Required Alert is not Displayed");
    }

    @Test
    void shouldDisplayPasswordRequiredAlertWhenNoPasswordIsProvided() {
        HomePage homePage = new HomePage(driver, wait);
        homePage.open();
        LoginPage loginPage = homePage.goToLogin();
        loginPage.login("test@softie.pl", "");
        Assertions.assertTrue(loginPage.isPasswordRequiredAlertDisplayed(),
                "Password Required Alert is not Displayed");
    }

    @Test
    void shouldLoginUserWhenCorrectCredentialsAreProvided() {
        HomePage homePage = new HomePage(driver, wait);
        homePage.open();
        LoginPage loginPage = homePage.goToLogin();
        loginPage.login("test@softie.pl", "1qaz!QAZ");
        Assertions.assertTrue(loginPage.isSignOutButtonDisplayed(), "SignOut Button is not Displayed");
    }

    @Test
    void shouldDispalyAuthenticationFailedWhenWrongCredentialsAreProvided() {
        HomePage homePage = new HomePage(driver, wait);
        homePage.open();
        LoginPage loginPage = homePage.goToLogin();
        loginPage.login("test@softie.pl", "wrongPass");
        Assertions.assertTrue(loginPage.isAuthenticationFailedAlertDisplayed());
    }

}
