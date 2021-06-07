package tests;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import pageobjects.HomePage;
import pageobjects.LoginPage;
import pageobjects.RegisterPage;
import utils.RandomUser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegisterTest extends BaseTest {

    Faker faker = new Faker();
    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();


    @Test
    void shouldRegisterUserWhenAllMandatoryDataProvided() {
        HomePage basePage = new HomePage(driver, wait);
        basePage.open();
        LoginPage loginPage = basePage.goToLogin();
        RandomUser randomUser = new RandomUser();
        RegisterPage registerPage = loginPage.goToRegister(randomUser.email);
        registerPage.register(randomUser);
        assertTrue(basePage.isSignOutButtonDisplayed());
    }

    @Test
    void shouldDisplayMissingMandatoryDataErrorWhenIncompleteDataProvided()  {
        HomePage basePage = new HomePage(driver, wait);
        basePage.open();
        LoginPage loginPage = basePage.goToLogin();

        RandomUser randomUser = new RandomUser();
        randomUser.phone = "";
        randomUser.address1 = "";
        randomUser.city = "";
        randomUser.postcode = "";

        RegisterPage registerPage = loginPage.goToRegister(randomUser.email);
        registerPage.register(randomUser);

        assertEquals("There are 4 errors", registerPage.getRegisterErrorMainText());
        assertTrue(registerPage.getRegisterErrorDetailsText().contains("You must register at least one phone number."));
        assertTrue(registerPage.getRegisterErrorDetailsText().contains("address1 is required."));
        assertTrue(registerPage.getRegisterErrorDetailsText().contains("city is required."));
        assertTrue(registerPage.getRegisterErrorDetailsText().contains("The Zip/Postal code you've entered is invalid."));
    }

    @Test
    void shouldDisplayIncorrectPostcodeErrorWhenTooLongPostcodeProvided() {
        HomePage basePage = new HomePage(driver, wait);
        basePage.open();
        LoginPage loginPage = basePage.goToLogin();

        RandomUser randomUser = new RandomUser();
        randomUser.postcode = "4636373";

        RegisterPage registerPage = loginPage.goToRegister(randomUser.email);
        registerPage.register(randomUser);
        assertEquals("The Zip/Postal code you've entered is invalid. It must follow this format: 00000",
                registerPage.getRegisterErrorDetailsText());

    }
}
