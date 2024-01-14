package StepDefs;

import Pages.HomePage;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static StepDefs.allVariables.*;
import static java.lang.Thread.sleep;


public class HomeStepDef {

    private WebDriver driver;

    public HomeStepDef() {
        WebDriverManager.chromedriver().setup();
    }

    @Given("open chrome browser")
    public void openChromeBrowser() throws InterruptedException {
        // instantiate a new ChromeDriver object
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @And("launch Brit Insurance portal")
    public void launchBritUrl() throws Throwable {
        try {
            // Launch or navigate to Brit Insurance portal and assert on Home Page Title
            driver.get(BritInsurance_Portal);
            Assert.assertEquals("Brit Insurance", driver.getTitle());
        } catch (Exception e) {
            Assert.fail(e.getMessage());
            System.out.println("exception occured" + e);
        }
    }

    @And("Accept Cookies Dialog")
    public void acceptCookies() throws Throwable {
        try {
            HomePage homePage = new HomePage(driver);
            // Accept Cookies
            if (homePage.cookiesDialog.isDisplayed()) {
                homePage.acceptAllCookies.click();
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
            System.out.println("exception occured" + e);
        }
    }

    @When("click on search button")
    public void clickSearchButton() throws Throwable {
        try {
            // Assert search button and click
            HomePage homePage = new HomePage(driver);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
            wait.until(ExpectedConditions.elementToBeClickable(homePage.searchButton));
            Assert.assertTrue(homePage.searchButton.isDisplayed());
            sleep(2000);
            homePage.searchButton.click();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
            System.out.println("exception occured" + e);
        }
    }

    @When("enter search term {string}")
    public void enterSearchTerm(String text) throws Throwable {
        try {
            // enter search term in search bar
            HomePage homePage = new HomePage(driver);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
            wait.until(ExpectedConditions.visibilityOf(homePage.searchBar));
            homePage.searchBar.sendKeys(text);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
            System.out.println("exception occured" + e);
        }
    }

    @Then("validate search results")
    public void verifySearchResults() throws Throwable {
        try {
            // Wait for the search results and assert each result.
            HomePage homePage = new HomePage(driver);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
            wait.until(ExpectedConditions.visibilityOf(homePage.searchResults));
            Assert.assertTrue(homePage.searchResults.isDisplayed());
            List<WebElement> resultList = driver.findElements(By.xpath(results));
            System.out.println("total search results found :" + "" + resultList.size());
            Assert.assertTrue(homePage.result1.isDisplayed());
            Assert.assertTrue(homePage.result2.isDisplayed());
            Assert.assertTrue(homePage.result3.isDisplayed());
            Assert.assertTrue(homePage.result4.isDisplayed());
        } catch (Exception e) {
            Assert.fail(e.getMessage());
            System.out.println("exception occured" + e);
        }
    }

    @Given("close browser")
    public void closeChromeBrowser() throws InterruptedException {
        // close the browser
        driver.quit();
    }

}
