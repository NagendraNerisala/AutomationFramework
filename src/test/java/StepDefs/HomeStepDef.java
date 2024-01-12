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
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        System.out.println("Automation opened the chrome browser successfully");
    }

    @And("launch Brit Insurance portal")
    public void launchBritUrl() throws Throwable {
        try {
            // Launch or navigate to Brit Insurance portal
            driver.get(BritInsurance_Portal);
            sleep(1000);
            System.out.println("Home Page Title is :" + " " + driver.getTitle());
            Assert.assertTrue(driver.getTitle().contains("Brit Insurance"));
            System.out.println("Automation launched Home Page successfully");
        } catch (Exception e) {
            Assert.fail(e.getMessage());
            System.out.println("exception occured" + e);
        }
    }

    @And("Accept Cookies Dialog")
    public void acceptCookies() throws Throwable {
        try {
            HomePage homePage = new HomePage(driver);
            if (homePage.cookiesDialog.isDisplayed()) {
                homePage.acceptAllCookies.click();
                System.out.println("Automation accepted cookies");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
            System.out.println("exception occured" + e);
        }

    }

    @When("click on search button")
    public void clickSearchButton() throws Throwable {
        try {
            HomePage homePage = new HomePage(driver);
            Assert.assertTrue(homePage.searchButton.isDisplayed());
            System.out.println("Is Search button Displayed ? :" + " " + homePage.searchButton.isDisplayed());
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
            wait.until(ExpectedConditions.elementToBeClickable(homePage.searchButton));
            sleep(2000);
            homePage.searchButton.click();
            System.out.println("Automation clicked search button successfully");
        } catch (Exception e) {
            Assert.fail(e.getMessage());
            System.out.println("exception occured" + e);
        }
    }

    @When("enter search term {string}")
    public void enterSearchTerm(String text) throws Throwable {
        try {
            HomePage homePage = new HomePage(driver);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
            wait.until(ExpectedConditions.visibilityOf(homePage.searchBar));
            sleep(1000);
            homePage.searchBar.sendKeys(text);
            System.out.println("Automation entered search term :"+" "+text);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
            System.out.println("exception occured" + e);
        }
    }

    @Then("validate search results")
    public void verifySearchResults() throws Throwable {
        try {
            HomePage homePage = new HomePage(driver);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
            wait.until(ExpectedConditions.visibilityOf(homePage.searchResults));
            Assert.assertTrue(homePage.searchResults.isDisplayed());
            List<WebElement> resultList = driver.findElements(By.xpath(results));
            System.out.println("total number of search results found :" + "" + resultList.size());
            Assert.assertTrue(homePage.result1.isDisplayed());
            System.out.println(homePage.result1.getText());
            Assert.assertTrue(homePage.result2.isDisplayed());
            System.out.println(homePage.result2.getText());
            Assert.assertTrue(homePage.result3.isDisplayed());
            System.out.println(homePage.result3.getText());
            Assert.assertTrue(homePage.result4.isDisplayed());
            System.out.println(homePage.result4.getText());
            System.out.println("search results displayed are verified");
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
