package framework.managers;

import framework.utils.PropsConst;
import org.apache.commons.exec.OS;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class DriverManager {

    private static DriverManager INSTANCE = null;
    private WebDriver driver;
    private TestPropManager propManager = TestPropManager.getInstance();

    private DriverManager() {

    }

    public static DriverManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DriverManager();
        }
        return INSTANCE;
    }

    public WebDriver getDriver() {
        if (driver == null) {
            initDriver();
        }
        return driver;
    }

    private void initDriver() {
        System.setProperty("webdriver.chrome.driver", propManager.getProperty(PropsConst.PATH_CROME_DRIVER_WINDOWS));
        driver = new ChromeDriver();
    }

    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }


}
