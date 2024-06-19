package tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class WaitForMinions {

    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
        driver = new FirefoxDriver();
        driver.get("http://localhost/minions.php");
    }

    @Test
    public void waitForMinions() {
        int numberOfMinions = 5;
        driver.findElement(By.xpath("//input[@type='number']")).sendKeys(String.valueOf(numberOfMinions));
        driver.findElement(By.xpath("//button[contains(@class, 'btn-warning')]")).click();
        new WebDriverWait(driver, 10)
                .withMessage("Timeout waiting for number of minions to be" + numberOfMinions)
                .until(ExpectedConditions.numberOfElementsToBe(By.xpath("//div[@class='minions']//img"), numberOfMinions));

        List<WebElement> minionsImages = driver.findElements(By.xpath("//div[@class='minions']//img"));
        Assert.assertEquals(numberOfMinions, minionsImages.size());
    }



}
