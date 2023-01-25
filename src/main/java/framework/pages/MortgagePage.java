package framework.pages;

import framework.pages.blocks.BaseMenuBlock;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MortgagePage extends BasePage {
    private BaseMenuBlock baseMenuBlock = new BaseMenuBlock();

    @FindBy(xpath = ".//div[contains(@class,'product-teaser-full-width__image_md')]")
    WebElement firstBlock;
    @FindBy(xpath = ".//iframe[contains(@sandbox,'allow-top-navigation')]")
    WebElement iframeForm;

    @Step("Проверка прогрузилась ли страничка вторичная ипотека")
    public MortgagePage checkPage() {
        Assertions.assertTrue(waitUntilElementToBeVisible(firstBlock).isDisplayed(), "Страничка ипотеки не прогрузилась");
        return this;
    }

    @Step("Переход во фрейм для заполнения формы")
    public FramePage goToFormPage() {
        return getFramePage(iframeForm);
    }


    public BaseMenuBlock getBaseMenuBlock() {
        return baseMenuBlock;
    }
}
