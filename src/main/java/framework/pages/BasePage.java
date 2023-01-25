package framework.pages;

import framework.managers.DriverManager;
import framework.managers.PageManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    protected DriverManager driverManager = DriverManager.getInstance();
    protected WebDriverWait wait = new WebDriverWait(driverManager.getDriver(), 10, 1000);
    protected PageManager pageManager = PageManager.getInstance();

    public BasePage() {
        PageFactory.initElements(driverManager.getDriver(), this);
    }

    protected WebElement waitUntilElementToBeClicable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected WebElement waitUntilElementToBeVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected WebElement scrollWithOffset(WebElement element, int x, int y) {
        String code = "window.scroll(" + (element.getLocation().x + x) + ","
                + (element.getLocation().y + y) + ");";
        ((JavascriptExecutor) driverManager.getDriver()).executeScript(code, element, x, y);
        return element;
    }

    protected WebElement scrollToElementJs(WebElement element) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driverManager.getDriver();
        javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
        return element;
    }

    protected WebElement clickToElementJs(WebElement element) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driverManager.getDriver();
        javascriptExecutor.executeScript("arguments[0].click();", element);
        return element;
    }

    protected FramePage getFramePage(WebElement element){
        driverManager.getDriver().switchTo().frame(element);
        return pageManager.getFramePage();
    }

    protected void waitStabilityPage(int maxWaitMillis, int pollDelimiter) {
        double startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() < startTime + maxWaitMillis) {
            String prevState = driverManager.getDriver().getPageSource();
            wait(pollDelimiter); // <-- would need to wrap in a try catch
            if (prevState.equals(driverManager.getDriver().getPageSource())) {
                return;
            }
        }
    }

    protected void wait(int mlSec){
        try {
            Thread.sleep(mlSec);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    protected void sendKeysByOneChar(WebElement element, String value){
        String[] strings = value.split("");
        Actions actions = new Actions(driverManager.getDriver());
        for (String charItem: strings) {
            actions.moveToElement(element).pause(100).click(element).sendKeys(charItem).pause(100).build().perform();
        }

    }

}
