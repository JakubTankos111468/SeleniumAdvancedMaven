package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ThereAndBackAgainTest {
    private WebDriver driver;
    JavascriptExecutor js;

    @Before
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost/tabulka.php");
        js = (JavascriptExecutor) driver;
    }

    @Test
    public void testScroll() {
        for (int i = 0; i < 4; i++) {
            js.executeScript("window.scrollBy(0,200)");
        }

    }

    @Test
    public void testScrollToEnd() {
        Long bodyheight = (Long) js.executeScript("return document.body.scrollHeight");
        System.out.println(bodyheight);
        js.executeScript("window.scrollBy(0,"+ bodyheight +")");
    }


    @After
    public void tearDown(){
        driver.quit();
    }
}
