package pageobjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ForgotPasswordPage extends BasePage {

    public ForgotPasswordPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @FindBy(xpath = "//div[@class'breadcrumb clearfix']")
    private WebElement breadCrumb;

    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(xpath = "//div[@class='alert alert-danger']//p")
    private WebElement errorMessageMainText;

    @FindBy(xpath = "//div[@class='alert alert-danger']//ol")
    private WebElement errorMessageDetailsText;

    @FindBy(xpath = "//p[@class='alert alert-success']")
    private WebElement successAlertText;

    @FindBy(xpath = "//a[@title='Back to Login']")
    private WebElement backToLoginButton;


    public void retrivePasswordForEmail(String email) {
        wait.until(ExpectedConditions.elementToBeClickable(emailField));
        emailField.sendKeys(email);
        emailField.sendKeys(Keys.ENTER);
    }

    public String getErrorMessageMainText() {
        wait.until(ExpectedConditions.visibilityOf(errorMessageMainText));
        return errorMessageMainText.getText();
    }

    public String getErrorMessageDetailsText() {
        wait.until(ExpectedConditions.visibilityOf(errorMessageDetailsText));
        return errorMessageDetailsText.getText();
    }

    public String getSuccessAlertText() {
        wait.until(ExpectedConditions.visibilityOf(successAlertText));
        return successAlertText.getText();
    }

    public HomePage backToLoginPageFromForgotPasswordPage() {
        wait.until(ExpectedConditions.elementToBeClickable(backToLoginButton));
        backToLoginButton.click();
        return new HomePage(driver, wait);
    }
}
