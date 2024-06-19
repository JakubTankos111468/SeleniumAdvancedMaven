package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

public class WebDriverSingleton {
    private static WebDriver driver;
    private static void initialize() {
        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
        driver = new FirefoxDriver();
    }
    public static WebDriver getWebDriverInstance() {
        if (driver == null ){
            initialize();
        }
        return driver;
    }

    private static void initializeGoogle() {
        driver = new ChromeDriver();
    }

    private static void initializePhantomJS() {
        System.setProperty("phantomjs.binary.path", "src/main/resources/phantomjs.exe");
        driver = new PhantomJSDriver();
    }
}
