package tests;

import base.TestBase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InceptionTest extends TestBase {

    @Test
    public void testDeeper() throws InterruptedException {
        getDriver().get(BASE_URL + "waitforit.php");
        String parrentWindow = getDriver().getWindowHandle();
        getDriver().findElement(By.id("letsGoDeeper")).click();
        new WebDriverWait(getDriver(),5)
                .until(ExpectedConditions.numberOfWindowsToBe(2));

        for (String handle : getDriver().getWindowHandles()) {
            getDriver().switchTo().window(handle);
        }
        getDriver().findElement(By.xpath("//input[1]")).sendKeys("Dusan Cinkota");
        Thread.sleep(3000);

        getDriver().close();
        getDriver().switchTo().window(parrentWindow);
        getDriver().findElement(By.id("letsGoDeeper")).click();
    }

}
