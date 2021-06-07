package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage{
    public LoginPage(WebDriver driver, WebDriverWait wait){
        super(driver,wait);
    }

    @FindBy(id = "email")
    private WebElement loginField;

    @FindBy(id = "passwd")
    private WebElement passwordField;

    @FindBy(id = "SubmitLogin")
    private WebElement submitLoginButton;

    @FindBy(className = "alert-danger")
    private WebElement authAlert;

    @FindBy(className = "alert-danger")
    private WebElement authAlertPasswordIsRequired;

    @FindBy(className = "icon-home")
    private WebElement houseLogo;

    @FindBy(id = "header_logo")
    private WebElement shopLogoButton;

    @FindBy(className= "alert-warning")
    private WebElement noResultAlert;

    @FindBy(linkText = "Forgot your password?")
    private WebElement forgotPasswordLink;

    @FindBy(id = "email_create")
    private WebElement email_create;

    @FindBy(id = "SubmitCreate")
    private WebElement submitCreate;

    public boolean isNoResultsWereFoundForYourSearchAlertDisplayed(){
        wait.until(ExpectedConditions.visibilityOf(noResultAlert));
        return noResultAlert.getText().contains("No results were found for your search \"Jackie Chan\"");
    }


    public void login(String email, String password) {
        loginField.sendKeys(email);
        passwordField.sendKeys(password);
        submitLoginButton.click();
    }

    public boolean isEmailRequiredAlertDisplayed(){
        wait.until(ExpectedConditions.visibilityOf(authAlert));
        return authAlert.getText().contains("An email address required");
    }

    public boolean isPasswordRequiredAlertDisplayed(){
        wait.until(ExpectedConditions.visibilityOf(authAlert));
        return authAlert.getText().contains("Password is required");
    }

    public HomePage goToHomePageByClickShopLogo() {
        wait.until(ExpectedConditions.elementToBeClickable(shopLogoButton));
        shopLogoButton.click();
        return new HomePage(driver, wait);
    }

    public HomePage goToHomePageByClickTheHouseLogo() {
        wait.until(ExpectedConditions.elementToBeClickable(houseLogo));
        houseLogo.click();
        return new HomePage(driver, wait);
    }

    public ForgotPasswordPage goToForgotPasswordPage() {
        wait.until(ExpectedConditions.elementToBeClickable(forgotPasswordLink));
        forgotPasswordLink.click();
        return new ForgotPasswordPage(driver, wait);
    }

    public boolean isAuthenticationFailedAlertDisplayed(){
        wait.until(ExpectedConditions.visibilityOf(authAlert));
        return authAlert.getText().contains("Authentication failed");
    }

    public RegisterPage goToRegister(String email){
        email_create.sendKeys(email);
        submitCreate.click();
        return new RegisterPage(email, driver, wait);
    }
}
