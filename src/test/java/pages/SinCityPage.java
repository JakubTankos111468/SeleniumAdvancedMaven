package pages;

import base.WebDriverSingleton;
import enumerators.SinType;
import models.Sin;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static base.TestBase.BASE_URL;

public class SinCityPage {
    private final WebDriver driver;
    private final static String PAGE_URL = "sincity.php";
    @FindBy(name = "title")
    private WebElement titleInput;

    @FindBy(name = "author")
    private WebElement authorInput;

    @FindBy(name = "message")
    private WebElement messageInput;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement confessButton;
    @FindBy(css = "div.sinsListArea")
    private WebElement sinListSection;
    public SinCityPage() {
        driver = WebDriverSingleton.getWebDriverInstance();
        PageFactory.initElements(driver,this);
    }
    public void openPage(){
        driver.get(BASE_URL + PAGE_URL);
    }
    public void fillSinInformation(Sin sin) {
        titleInput.sendKeys(sin.getTitle());
        authorInput.sendKeys(sin.getAuthor());
        messageInput.sendKeys(sin.getMessage());
    }

    public void markTag(List<SinType> tags) {
        for (SinType tag : tags) {
            driver.findElement(By.xpath("//input[@value='" + tag.getXpathValue() + "']")).click();
        }
    }

    public void confessSin(){
        confessButton.click();
    }

    public void openSinDetail(Sin spiderSin) {
        WebElement listOfSins = sinListSection.findElement(By.cssSelector("ul.list-of-sins"));
        listOfSins.findElement(By.xpath("./li[contains(text(),'" + spiderSin.getTitle() + "')]")).click();
    }

    public void checkSinStatus(Sin spiderSin) {
        WebElement actualSin = getWebElement(spiderSin);
        String actualText = actualSin.findElement(By.cssSelector("p")).getText().trim();
        if (spiderSin.isForgiven()) {
            Assert.assertEquals("forgiven", actualText);
        } else {
            Assert.assertEquals("pending", actualText);
        }
    }

    private WebElement getWebElement(Sin spiderSin) {
        WebElement listOfSins = driver.findElement(By.cssSelector("ul.list-of-sins"));
        WebElement actualSin = listOfSins.findElement(By.xpath("./li[contains(text(),'" + spiderSin.getTitle() + "')]"));
        return actualSin;
    }

    public void openDetail(Sin spiderSin) {
        getWebElement(spiderSin).click();
    }

    public void checkDetail(Sin spiderSin) {
        WebElement sinDetail = driver.findElement(By.cssSelector("div.detail"));

        new WebDriverWait(driver,10)
                .until(ExpectedConditions.textToBePresentInElement(
                        sinDetail.findElement(By.cssSelector("p")),
                        spiderSin.getMessage()));

        String actualTitle =sinDetail.findElement(By.cssSelector("h4")).getText();
        Assert.assertTrue(actualTitle.contains(spiderSin.getTitle()));
        Assert.assertTrue(actualTitle.contains(spiderSin.getAuthor()));
    }
}
