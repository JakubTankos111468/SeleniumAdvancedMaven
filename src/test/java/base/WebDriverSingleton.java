package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

public class WebDriverSingleton {
    private static WebDriver driver;
    private static void initialize() {
        //initializePhantomJS();
        //initializeGoogle();
        /*FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("general.useragent.override", "Mozilla/5.0 (Android 4.4; mobile; rv:41.0) Gecko/41.0 Firefox/41.0");*/
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
        //System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
        driver = new ChromeDriver();
    }

    private static void initializePhantomJS() {
        System.setProperty("phantomjs.binary.path", "src/main/resources/phantomjs.exe");
        driver = new PhantomJSDriver();
    }
}
