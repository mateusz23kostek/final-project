package pageobjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;

public class ShoppingPage extends BasePage {

    private final String CATEGORY_PAGE_URL = "http://automationpractice.com/index.php?id_category=3&controller=category";

    Random random = new Random();

    public ShoppingPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void open() {
        driver.get(CATEGORY_PAGE_URL);
    }

    @FindBy(xpath = "//a[contains(@class,'ajax_add_to_cart_button')]")
    private List<WebElement> addToCartButtons;

    @FindBy(xpath = "//div[@class='product-container']")
    private List<WebElement> products;

    @FindBy(xpath = "//div[@class='layer_cart_product col-xs-12 col-md-6']")
    private WebElement productAddedAlert;

    @FindBy(xpath = "//a[@title='Proceed to checkout']")
    private WebElement proceedToCheckoutButton;

    @FindBy(className = "cart_quantity_delete")
    private WebElement trashIcon;

    @FindBy(className = "alert-warning")
    private WebElement emptyCartAlert;

    @FindBy(xpath = "//span[@title='Continue shopping']")
    private WebElement continueShoppingButton;

    @FindBy(xpath = "//div[@class='shopping_cart']//b")
    private WebElement cartIcon;

    @FindBy(id = "button_order_cart")
    private WebElement placeOrderButton;

    @FindBy(xpath = "//p[@class='cart_navigation clearfix']//a[@title='Proceed to checkout']")
    private WebElement firstStepProceedButton;

    @FindBy(xpath = "//p[@class='cart_navigation clearfix']/button[@type='submit']")
    private WebElement nextStepsProceedButton;

    @FindBy(xpath = "//button[@name='processCarrier']")
    private WebElement processOrderButton;

    @FindBy(id = "cgv")
    private WebElement termsCheckbox;

    @FindBy(xpath = "//a[@class='bankwire']")
    private WebElement payByBankWireButton;

    @FindBy(xpath = "//div[@class='box']")
    private WebElement orderDetailsBox;

    @FindBy(className = "cheque-indent")
    private WebElement orderProcesIsComplete;


    public void addRandomProductsToTheCart(int productNumber) {
        for (int i = 0; i < productNumber; i++) {
            int randomProductIndex = random.nextInt(products.size() - 1);
            moveMouseToProduct(randomProductIndex);
            clickOnAddToCartButton(randomProductIndex);
            clickOnContinueShoppingButton();
        }
    }

    public void addRandomProductsToTheCartAndAfterDeleteProduct(int productNumber) {
        for (int i = 0; i < productNumber; i++) {
            int randomProductIndex = random.nextInt(products.size() - 1);
            moveMouseToProduct(randomProductIndex);
            clickOnAddToCartButton(randomProductIndex);
            proceedToCheckoutClick();
            trashIconClick();
        }
    }

    private void moveMouseToProduct(int index) {
        scrollToElement(products.get(index));
        wait.until(ExpectedConditions.visibilityOf(products.get(index)));
        Actions builder = new Actions(driver);
        builder.moveToElement(products.get(index)).moveToElement(products.get(index)).build().perform();
    }

    private void clickOnAddToCartButton(int index) {
        wait.until(ExpectedConditions.visibilityOf(addToCartButtons.get(index)));
        addToCartButtons.get(index).click();
    }

    private void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public boolean isProductSuccessfullyAddedToYourShoppingCartAlertDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(productAddedAlert));
        return productAddedAlert.getText().contains("Product successfully added to your shopping cart");
    }

    public boolean isYourShoppingCartIsEmptyAlertDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(emptyCartAlert));
        return emptyCartAlert.getText().contains("Your shopping cart is empty.");
    }

    public void proceedToCheckoutClick() {
        wait.until(ExpectedConditions.elementToBeClickable(proceedToCheckoutButton));
        proceedToCheckoutButton.click();
    }

    public void trashIconClick() {
        wait.until(ExpectedConditions.elementToBeClickable(trashIcon));
        trashIcon.click();
    }

    private void clickOnContinueShoppingButton() {
        wait.until(ExpectedConditions.elementToBeClickable(continueShoppingButton));
        continueShoppingButton.click();
    }

    public void placeOrderForCurrentCart() {
        clickOnCartPlaceOrderButton();
        proceedToPayment();
        payByBankWireAndConfirmOrder();
    }

    private void clickOnCartPlaceOrderButton() {
        scrollToElement(cartIcon);
        Actions builder = new Actions(driver);
        builder.moveToElement(cartIcon).build().perform();
        wait.until(ExpectedConditions.visibilityOf(placeOrderButton));
        placeOrderButton.click();
    }

    private void proceedToPayment() {
        wait.until(ExpectedConditions.elementToBeClickable(firstStepProceedButton));
        firstStepProceedButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(nextStepsProceedButton));
        nextStepsProceedButton.click();
        termsCheckbox.click();
        wait.until(ExpectedConditions.elementToBeClickable(processOrderButton));
        processOrderButton.click();
    }

    private void payByBankWireAndConfirmOrder() {
        wait.until(ExpectedConditions.elementToBeClickable(payByBankWireButton));
        payByBankWireButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(nextStepsProceedButton));
        nextStepsProceedButton.click();
    }

    public boolean orderProcesIsComplete(){
        wait.until(ExpectedConditions.visibilityOf(orderProcesIsComplete));
        return orderProcesIsComplete.getText().contains("Your order on My Store is complete");
    }


}
