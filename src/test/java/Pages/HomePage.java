package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "component--header__search")
    public WebElement searchButton;

    @FindBy(xpath = "//input[@type='search']")
    public WebElement searchBar;

    @FindBy(id = "CybotCookiebotDialogBody")
    public WebElement cookiesDialog;

    @FindBy(xpath = "//*[@id='CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll']")
    public WebElement acceptAllCookies;

    @FindBy(className = "header--search__results")
    public WebElement searchResults;

    @FindBy(xpath = "//div[@class='header--search__results']/div[@class='result']")
    public WebElement results;

    @FindBy(xpath = "//a[contains(text(),'Interim results for the six months ended 30 June 2022')]")
    public WebElement result1;

    @FindBy(xpath = "//a[contains(text(),'Interim Report 2023')]")
    public WebElement result2;

    @FindBy(xpath = "//a[contains(text(),'Gavin Wilkinson')]")
    public WebElement result3;

    @FindBy(xpath = "//a[contains(text(),'John King')]")
    public WebElement result4;


}
