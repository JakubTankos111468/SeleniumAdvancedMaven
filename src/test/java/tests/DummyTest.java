package tests;

import categories.ReleaseTest;
import categories.SmokeTest;
import com.google.code.tempusfugit.concurrency.ConcurrentTestRunner;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

@RunWith(ConcurrentTestRunner.class)
public class DummyTest {
    static  int numberOfFailedTest;
    int failed = 0;
    WebDriver driver;

    @BeforeClass
    public static void setUpClass() {
        System.out.println("setup class");
        numberOfFailedTest = 0;
    }

    @Before
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
        driver = new FirefoxDriver();
        driver.get("http://localhost/waitforit.php");
        driver.manage().window().maximize();
    }


    @Category(SmokeTest.class)
    @Test
    public void testA(){
        System.out.println("A");
        System.out.println("Static" + numberOfFailedTest);
        failed++;
        System.out.println(failed);
    }

    @Category({SmokeTest.class, ReleaseTest.class})
    @Test
    public void testB(){
        System.out.println("B");
        numberOfFailedTest++;
        System.out.println("Static" + numberOfFailedTest);
        failed = 10;
        System.out.println(failed);
    }

    @Category(ReleaseTest.class)
    @Test
    public void testC(){
        System.out.println("C");
        System.out.println("Static" + numberOfFailedTest);
        System.out.println(failed);
    }

    @AfterClass
    public static void tearDown() {
        System.out.println("Static" + numberOfFailedTest);
    }

}
