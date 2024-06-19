package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class ParameterTest {
    WebDriver driver;
    int number;
    boolean expectedPrime;

    @Parameterized.Parameters
    public static List<Integer> getData() {
        return Arrays.asList(1,2,6,9,482);
    }

    public ParameterTest(int number) {
        this.number = number;
    }

    @Before
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost/primenumber.php");
    }

    @Test
    public void testOptimus() throws IOException {
        WebElement numberInput = driver.findElement(By.xpath("//input[@type='number']"));
        WebElement button = driver.findElement(By.cssSelector("button.btn"));

            numberInput.clear();
            numberInput.sendKeys(String.valueOf(number));
            button.click();


          /*  if (expectedPrime) {
                new WebDriverWait(driver, 5)
                        .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Optimus approves']")));
            }else {
                new WebDriverWait(driver, 5)
                        .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Optimus is sad']")));
            }

        }*/

    }
    @After
    public void tearDown(){
        driver.quit();
    }
}
