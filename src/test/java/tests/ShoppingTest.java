package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pageobjects.HomePage;
import pageobjects.LoginPage;
import pageobjects.ShoppingPage;

public class ShoppingTest extends BaseTest {

    @Test
    void shouldPlaceAnOrderForAddedProducts() {
        HomePage homePage = new HomePage(driver, wait);
        homePage.open();

        LoginPage loginPage = homePage.goToLogin();
        loginPage.login("test@softie.pl", "1qaz!QAZ");

        ShoppingPage shoppingPage = loginPage.goShoppingPage();
        shoppingPage.addRandomProductsToTheCart(3);
        shoppingPage.placeOrderForCurrentCart();
        Assertions.assertTrue(shoppingPage.orderProcesIsComplete(), "Order proces is not complete");
    }
}
