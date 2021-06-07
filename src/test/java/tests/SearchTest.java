package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pageobjects.BasePage;
import pageobjects.SearchResultsPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchTest extends BaseTest {

    @Test
    void verifyIsLogoAndSearchFieldExist() {
        BasePage basePage = new BasePage(driver, wait);
        basePage.open();
        Assertions.assertTrue(basePage.isSearchInputFieldDisplayed(),
                "Search Input Field is not Displayed");
        Assertions.assertTrue(basePage.isSearchInputButtonDisplayed(),
                "Search Input Button is not Displayed");
        basePage.goToLogin();
        Assertions.assertTrue(basePage.isSearchInputFieldDisplayed(),
                "Search Input Field is not Displayed");
        Assertions.assertTrue(basePage.isSearchInputButtonDisplayed(),
                "Search Input Button is not Displayed");
    }

    @Test
    void checkThatLogoAndSearchFieldIsWorksOnHomePageAndLoginPage() {
        BasePage basePage = new BasePage(driver, wait);
        basePage.open();
        basePage.searchFor("Jackie Chan");
        Assertions.assertTrue(basePage.isNoResultsWereFoundForYourSearchAlertDisplayed());
        basePage.goToLogin();
        basePage.searchFor("Jackie Chan");
        Assertions.assertTrue(basePage.isNoResultsWereFoundForYourSearchAlertDisplayed());
    }

    @Test
    void shouldReturnNonEmptySearchResultsWhenLookingForExistingProduct() {
        BasePage basePage = new BasePage(driver, wait);
        basePage.open();
        SearchResultsPage searchResultsPage = basePage.searchFor("Dress");
        assertTrue(searchResultsPage.getSearchResultsProductsNumber() > 0);
    }

    @Test
    void shouldReturnEmptySearchResultsWhenLookingForNonExistingProduct() {
        BasePage basePage = new BasePage(driver, wait);
        basePage.open();
        SearchResultsPage searchResultsPage = basePage.searchFor("Pizza");
        assertEquals("0 results have been found.", searchResultsPage.getSearchResultsSummaryText());
        assertTrue(searchResultsPage.getSearchResultsProductsNumber() == 0);
    }

}
