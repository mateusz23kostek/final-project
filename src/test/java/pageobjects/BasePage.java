package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    final String MAIN_URL = "http://automationpractice.com/index.php";
    protected WebDriver driver;
    protected WebDriverWait wait;

    @FindBy(linkText = "Sign in")
    private WebElement signInButton;

    @FindBy(linkText = "Contact us")
    private WebElement contactUsButton;

    @FindBy(id = "search_query_top")
    private WebElement searchInputField;

    @FindBy(name = "submit_search")
    private WebElement searchInputButton;

    @FindBy(className = "logout")
    private WebElement signOutButton;

    @FindBy(xpath = "//a[@title='Women']")
    private WebElement goToDressesButton;

    @FindBy(className= "alert-warning")
    private WebElement noResultAlert;


    public BasePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.get(MAIN_URL);
    }

    public SearchResultsPage searchFor(String searchPhrase) {
        wait.until(ExpectedConditions.elementToBeClickable(searchInputField));
        searchInputField.sendKeys(searchPhrase);
        searchInputButton.click();
        return new SearchResultsPage(driver, wait);
    }


    public LoginPage goToLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(signInButton));
        signInButton.click();
        return new LoginPage(driver, wait);
    }

    public ContactUs goToContactUs() {
        wait.until(ExpectedConditions.elementToBeClickable(contactUsButton));
        contactUsButton.click();
        return new ContactUs(driver, wait);
    }

    public boolean isSignOutButtonDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(signOutButton));
        return signOutButton.isDisplayed();
    }

    public boolean isSearchInputFieldDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(searchInputField));
        return searchInputField.isDisplayed();
    }

    public boolean isSearchInputButtonDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(searchInputButton));
        return searchInputButton.isDisplayed();
    }

    public boolean isNoResultsWereFoundForYourSearchAlertDisplayed(){
        wait.until(ExpectedConditions.visibilityOf(noResultAlert));
        return noResultAlert.getText().contains("No results were found for your search \"Jackie Chan\"");
    }

    public void clickOnSignOutButton() {
        wait.until(ExpectedConditions.elementToBeClickable(signOutButton));
        signOutButton.click();
    }

    public boolean isSignInButtonDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(signInButton));
        return signInButton.isDisplayed();
    }

    public ShoppingPage goShoppingPage() {
        wait.until(ExpectedConditions.elementToBeClickable(goToDressesButton));
        goToDressesButton.click();
        return new ShoppingPage(driver, wait);
    }

}
