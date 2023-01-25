package framework.pages;

import framework.pages.blocks.BaseMenuBlock;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class StartPage extends BasePage {
    private BaseMenuBlock baseMenuBlock = new BaseMenuBlock();
    @FindBy(xpath = ".//button[@class='kitt-cookie-warning__close']")
    WebElement closeCookie;
    @Step("Закрытие куки")
    public StartPage closeCookie() {
        waitStabilityPage(5000,250);
        waitUntilElementToBeClicable(closeCookie).click();
        return this;
    }

    public BaseMenuBlock getBaseMenuBlock() {
        return baseMenuBlock;
    }

}
