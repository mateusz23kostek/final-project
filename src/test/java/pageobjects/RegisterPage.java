package pageobjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.RandomUser;

public class RegisterPage extends BasePage {

    private String createEmail;

    @FindBy(id = "customer_firstname")
    private WebElement customer_firstname;

    @FindBy(id = "customer_lastname")
    private WebElement customer_lastname;

    @FindBy(id = "email")
    private WebElement email;

    @FindBy(id = "passwd")
    private WebElement passwd;

    @FindBy(id = "address1")
    private WebElement address1;

    @FindBy(id = "city")
    private WebElement city;

    @FindBy(id = "id_state")
    private WebElement id_state;

    @FindBy(id = "postcode")
    private WebElement postcode;

    @FindBy(id = "phone_mobile")
    private WebElement phone_mobile;

    @FindBy(xpath = "//div[@class='alert alert-danger']//p")
    private WebElement registerAlertMainText;

    @FindBy(xpath = "//div[@class='alert alert-danger']//ol")
    private WebElement registerAlertDetailsText;

    public RegisterPage(String createEmail, WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        this.createEmail = createEmail;
    }

    public void register(RandomUser user) {
        customer_firstname.sendKeys(user.firstName);
        customer_lastname.sendKeys(user.lastName);
        email.clear();
        email.sendKeys(user.email);
        passwd.sendKeys(user.password);
        this.address1.sendKeys(user.address1);
        this.city.sendKeys(user.city);
        Select stateSelect = new Select(this.id_state);
        stateSelect.selectByVisibleText(user.state);
        this.postcode.sendKeys(user.postcode);
        this.phone_mobile.sendKeys(user.phone);
        this.phone_mobile.sendKeys(Keys.ENTER);
    }

    public String getRegisterErrorMainText() {
        wait.until(ExpectedConditions.visibilityOf(registerAlertMainText));
        return registerAlertMainText.getText();
    }

    public String getRegisterErrorDetailsText() {
        wait.until(ExpectedConditions.visibilityOf(registerAlertDetailsText));
        return registerAlertDetailsText.getText();
    }

}
