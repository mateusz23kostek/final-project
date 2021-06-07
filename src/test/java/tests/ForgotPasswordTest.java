package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pageobjects.ForgotPasswordPage;
import pageobjects.HomePage;
import pageobjects.LoginPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ForgotPasswordTest extends BaseTest {

    @Test
    void shouldDisplayInvalidEmailAddressErrorWhenIncorrectSyntaxEmailProvided() {
        HomePage homePage = new HomePage(driver, wait);
        homePage.open();
        LoginPage loginPage = homePage.goToLogin();
        ForgotPasswordPage forgotPasswordPage = loginPage.goToForgotPasswordPage();
        forgotPasswordPage.retrivePasswordForEmail("wrong@email@adress@com");
        assertEquals("There is 1 error", forgotPasswordPage.getErrorMessageMainText());
        assertEquals("Invalid email address.", forgotPasswordPage.getErrorMessageDetailsText());
    }

    @Test
    void shouldDisplayInvalidEmailAddressErrorWhenNoEmailIsProvided() {
        HomePage homePage = new HomePage(driver, wait);
        homePage.open();
        LoginPage loginPage = homePage.goToLogin();
        ForgotPasswordPage forgotPasswordPage = loginPage.goToForgotPasswordPage();
        forgotPasswordPage.retrivePasswordForEmail("");
        assertEquals("There is 1 error", forgotPasswordPage.getErrorMessageMainText());
        assertEquals("Invalid email address.", forgotPasswordPage.getErrorMessageDetailsText());
    }

    @Test
    void shouldDisplayNoAccountRegisteredForThisEmailWhenUnregisteredEmailProvided() {
        HomePage homePage = new HomePage(driver, wait);
        homePage.open();
        LoginPage loginPage = homePage.goToLogin();
        ForgotPasswordPage forgotPasswordPage = loginPage.goToForgotPasswordPage();
        forgotPasswordPage.retrivePasswordForEmail("thisemailnotexist@gmail.com");
        assertEquals("There is 1 error", forgotPasswordPage.getErrorMessageMainText());
        assertEquals("There is no account registered for this email address.",
                forgotPasswordPage.getErrorMessageDetailsText());
    }

    @Test
    void shouldDisplaySuccsessAlertWhenRegisteredEmailProvided() {
        HomePage homePage = new HomePage(driver, wait);
        homePage.open();
        LoginPage loginPage = homePage.goToLogin();
        ForgotPasswordPage forgotPasswordPage = loginPage.goToForgotPasswordPage();
        forgotPasswordPage.retrivePasswordForEmail("janherl@02.pl");
        assertEquals("A confirmation email has been sent to your address: janherl@02.pl",
                forgotPasswordPage.getSuccessAlertText());
    }

    @Test
    void shouldBackToLoginPageFromForgotPasswordPage() {
        HomePage homePage = new HomePage(driver, wait);
        homePage.open();
        LoginPage loginPage = homePage.goToLogin();
        ForgotPasswordPage forgotPasswordPage = loginPage.goToForgotPasswordPage();
        forgotPasswordPage.backToLoginPageFromForgotPasswordPage();
        Assertions.assertEquals("Login - My Store", driver.getTitle(),
                "Page title is not a 'Login - My Store' ");
    }
}
