package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pageobjects.ShoppingPage;

public class CartTest extends BaseTest {

    @Test
    void shouldAddProductToCart() {
        ShoppingPage shoppingPage = new ShoppingPage(driver, wait);
        shoppingPage.open();
        shoppingPage.addRandomProductsToTheCart(1);
        Assertions.assertTrue(shoppingPage.isProductSuccessfullyAddedToYourShoppingCartAlertDisplayed(),
                "Product successfully added to your shopping cart Alert is not Displayed");
    }

    @Test
    void shouldAddProductToCartAndAfterDeleteProduct() {
        ShoppingPage shoppingPage = new ShoppingPage(driver, wait);
        shoppingPage.open();
        shoppingPage.addRandomProductsToTheCartAndAfterDeleteProduct(1);
        Assertions.assertTrue(shoppingPage.isYourShoppingCartIsEmptyAlertDisplayed(),
                "Your shopping cart is empty Alert is not Displayed");
    }

}
