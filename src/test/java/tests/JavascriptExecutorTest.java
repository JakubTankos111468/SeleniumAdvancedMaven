package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;

public class JavascriptExecutorTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost/tabulka.php");
    }

    @Test
    public void testHighLight() {
        List<WebElement> rows = driver.findElements(By.xpath("//table//tbody/tr"));

        for (WebElement row: rows) {
            if (row.getText().contains("Florian")) {
                JavascriptExecutor js = ((JavascriptExecutor) driver);
                js.executeScript("arguments[0].style.border='3px solid red'",row);
            }

            System.out.println(row.getText());
        }
    }

    @After
    public void tearDown(){
        //driver.quit();
    }

}
