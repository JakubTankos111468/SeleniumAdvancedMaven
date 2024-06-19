package pages;

import base.WebDriverSingleton;
import models.Sin;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;

import static base.TestBase.BASE_URL;

public class SpartaPage {
    private final WebDriver driver;
    private final static String PAGE_URL = "sparta.php";

    public SpartaPage() throws MalformedURLException {
        driver = WebDriverSingleton.getWebDriverInstance();
        PageFactory.initElements(driver, this);

    }
    
    public void openPage() {
        driver.get(BASE_URL + PAGE_URL);
    }

    private WebElement getMainSinElement(Sin sin) {
        return driver.findElement(By.xpath("//article[p[text()='" + sin.getMessage() + "']]"));
    }


    public void forgiveSin(Sin evaSpieva) {
        WebElement article = getMainSinElement(evaSpieva);
        article.findElement(By.cssSelector("button")).click();

        new WebDriverWait(driver,10)
                .until(ExpectedConditions.elementToBeClickable(By.id("confirm")));
        driver.findElement(By.id("confirm")).click();
        evaSpieva.setForgiven(true);
    }
}
