package tests;

import helpers.ExcelReader;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;

public class PrimeTest {
    private static final String TEST_DATA_PATH = "src/test/resources/data.xlsx";
    private static final String SHEET = "prime";
    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost/primenumber.php");
    }

    @Test
    public void testPrime() throws IOException {
        WebElement numberInput = driver.findElement(By.xpath("//input[@type='number']"));
        WebElement button = driver.findElement(By.cssSelector("button.btn"));
        ExcelReader primeExcelReader = new ExcelReader(TEST_DATA_PATH);
        Sheet sheet = primeExcelReader.getSheetByName(SHEET);
        for (Row cells : sheet) {
            if (cells.getRowNum() == 0){
                continue;
            }
            numberInput.clear();
            numberInput.sendKeys(String.valueOf((int) cells.getCell(0).getNumericCellValue()));
            button.click();

            boolean expectedPrime = cells.getCell(1).getBooleanCellValue();

            if (expectedPrime) {
                new WebDriverWait(driver, 5)
                        .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Optimus approves']")));
            }else {
                new WebDriverWait(driver, 5)
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Optimus is sad']")));
            }

        }

    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
