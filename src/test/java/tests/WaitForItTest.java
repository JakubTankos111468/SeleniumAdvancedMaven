package tests;

import categories.SmokeTest;
import org.junit.*;
import org.junit.experimental.categories.Category;
import org.junit.rules.ExpectedException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WaitForItTest {
    private WebDriver driver;
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost/waitforit.php");
    }

    @Category(SmokeTest.class)
    @Test
    public void chceckTitle() {
        expectedException.expect(ComparisonFailure.class);
        expectedException.expectMessage("nesedi title");

        Assert.assertEquals("nesedi title","WAIT FOR IT !!!",
                driver.findElement(By.xpath("//h1")).getText());
    }

    @Test
    @Ignore
    public void checkLegendary() {
        driver.findElement(By.id("startWaitForText")).click();
        Assert.assertEquals("dary !!!", driver.findElement(By.id("waitForTextInput")).getAttribute("value"));

    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
