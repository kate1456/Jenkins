package framework.pages;

import framework.managers.PageManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static framework.managers.DriverManager.getDriver;

public class BasePage {

    protected WebDriverWait wait = new WebDriverWait(getDriver(), 10, 1000);
    protected PageManager pageManager = PageManager.getInstance();

    public BasePage() {
        PageFactory.initElements(getDriver(), this);
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
        ((JavascriptExecutor) getDriver()).executeScript(code, element, x, y);
        return element;
    }

    protected WebElement scrollToElementJs(WebElement element) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) getDriver();
        javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
        return element;
    }

    protected WebElement clickToElementJs(WebElement element) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) getDriver();
        javascriptExecutor.executeScript("arguments[0].click();", element);
        return element;
    }

    protected FramePage getFramePage(WebElement element){
        getDriver().switchTo().frame(element);
        return pageManager.getFramePage();
    }

    protected void waitStabilityPage(int maxWaitMillis, int pollDelimiter) {
        double startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() < startTime + maxWaitMillis) {
            String prevState = getDriver().getPageSource();
            wait(pollDelimiter); // <-- would need to wrap in a try catch
            if (prevState.equals(getDriver().getPageSource())) {
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
        Actions actions = new Actions(getDriver());
        for (String charItem: strings) {
            actions.moveToElement(element).pause(100).sendKeys(charItem).pause(100).build().perform();
        }

    }

}
