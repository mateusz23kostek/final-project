package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pageobjects.HomePage;
import pageobjects.LoginPage;

public class NavigateTest extends BaseTest{

    @Test
    void shouldGotoContactUsPageFromHomePage(){
        HomePage homePage = new HomePage(driver, wait);
        homePage.open();
        homePage.goToContactUs();
        Assertions.assertEquals("Contact us - My Store", driver.getTitle(),
                "Page title is not a 'Contact us - My Store' ");
    }

    @Test
    void shouldGotoHomePageFromLoginPage(){
        HomePage homePage = new HomePage(driver, wait);
        homePage.open();
        LoginPage loginPage = homePage.goToLogin();
        loginPage.goToHomePageByClickShopLogo();
        Assertions.assertEquals("My Store", driver.getTitle(),
                "Page title is not a 'My Store' ");
        homePage.goToLogin();
        loginPage.goToHomePageByClickTheHouseLogo();
        Assertions.assertEquals("My Store", driver.getTitle(),
                "Page title is not a 'My Store' ");
    }
}
