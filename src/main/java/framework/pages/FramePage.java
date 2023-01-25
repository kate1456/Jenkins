package framework.pages;

import framework.pages.blocks.BaseMenuBlock;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class FramePage extends BasePage {
    private BaseMenuBlock baseMenuBlock = new BaseMenuBlock();
    @FindBy(xpath = "//div[@class='inpt-root__input-container-6-3-4-beta-1-for-calculator']")
    List<WebElement> listOfFormFields;
    @FindBy(xpath = ".//div[contains(@class,'gRWVZjEp676wfaZSnbjVR')]")
    List<WebElement> listOfCheckbox;

    @FindBy(xpath = ".//div[@data-test-id='main-results-block']/../..//span")
    List<WebElement> listOfResultMenu;

    @Step("Заполнение формы {nameOfForm} и проверка значения")
    public FramePage fillForm(String nameOfForm, String parametersToFill) {
        for (WebElement form : listOfFormFields) {
            WebElement inputForm = form.findElement(By.xpath(".//input"));
            if (form.findElement(By.xpath("./label")).getText().contains(nameOfForm)) {
                inputForm.click();
                inputForm.sendKeys(Keys.CONTROL + "a");
                inputForm.sendKeys(Keys.BACK_SPACE);
                sendKeysByOneChar(inputForm, parametersToFill);
                waitStabilityPage(5000, 250);
                Assertions.assertEquals(parametersToFill, inputForm.getAttribute("value")
                                .replaceAll("[^0-9]", ""),
                        "Поле зполнилось не корректно");
                return this;
            }
        }
        Assertions.fail("Форма не найдена");
        return this;
    }

    @Step("Клик по чекбоксу {nameOfCheckbox}")
    public FramePage clickCheckbox(String nameOfCheckbox) {
        for (WebElement form : listOfCheckbox) {
            if (form.findElement(By.xpath("./div/span")).getText().contains(nameOfCheckbox)) {
                clickToElementJs(form.findElement(By.xpath(".//input")));
                return this;
            }
        }
        Assertions.fail("Checkbox не найден");
        return this;
    }

    @Step("Проверка значения поля {nameOfParameter}")
    public FramePage checkParameters(String nameOfParameter, String meaning) {
        for (WebElement parameter : listOfResultMenu) {
            if (parameter.getText().contains(nameOfParameter)) {
                waitStabilityPage(5000, 250);
                String parameterInString = parameter
                        .findElement(By.xpath("./..//span/span"))
                        .getText()
                        .replaceAll("[^0-9]", "");
                Assertions.assertEquals(meaning, parameterInString,
                        "Параметр " + nameOfParameter + " не верный");
                return this;
            }
        }
        Assertions.fail("Параметр не найден");
        return this;
    }


    public BaseMenuBlock getBaseMenuBlock() {
        return baseMenuBlock;
    }
}
