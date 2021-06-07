package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SearchResultsPage extends BasePage {

    public SearchResultsPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @FindBy(xpath = "//span[@class='heading-counter']")
    private WebElement searchResultsSummaryText;

    @FindBy(xpath = "//div[@class='product-container']")
    private List<WebElement> searchResults;
    public String getSearchResultsSummaryText(){
        wait.until(ExpectedConditions.visibilityOf(searchResultsSummaryText));
        return searchResultsSummaryText.getText();
    }

    public int getSearchResultsProductsNumber(){
        wait.until(ExpectedConditions.visibilityOf(searchResultsSummaryText));
        return searchResults.size();
    }
}