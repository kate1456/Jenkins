package framework.managers;

import framework.utils.PropsConst;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.Map;

import static framework.utils.PropsConst.TYPE_BROWSER;

public class DriverManager {

    private static WebDriver driver;
    private static TestPropManager propManager = TestPropManager.getInstance();

    private DriverManager() {

    }

    public static WebDriver getDriver() {
        if (driver == null) {
            initDriver();
        }
        return driver;
    }

    private static void initDriver() {
        switch (propManager.getProperty(TYPE_BROWSER)){
            case "firefox":
                System.setProperty("webdriver.gecko.driver", propManager.getProperty(PropsConst.PATH_CHROME_DRIVER_WINDOWS));
                driver = new FirefoxDriver();
                break;
            case "edge":
                System.setProperty("webdriver.edge.driver", propManager.getProperty(PropsConst.PATH_EDGE_DRIVER_WINDOWS));
                driver = new EdgeDriver();
                break;
            case "chrome":
                System.setProperty("webdriver.chrome.driver", propManager.getProperty(PropsConst.PATH_CHROME_DRIVER_WINDOWS));
                driver = new ChromeDriver();
                break;
            case "remote":
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setCapability("browserName", "chrome");
                capabilities.setCapability("browserVersion", "108.0");
                capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                        "enableVNC", true,
                        "enableVideo", true
                ));
                try {
                    RemoteWebDriver driver = new RemoteWebDriver(
                            URI.create("http://149.154.71.152:4444/wd/hub").toURL(),
                            capabilities
                    );
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
        }
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }


}
