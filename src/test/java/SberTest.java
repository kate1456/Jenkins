import io.qameta.allure.Step;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SberTest extends BaseTests {
    @Test
    @DisplayName("Проверка ипотечного калькулятора на вторичное жилье")
    public void sberTest() {
        pageManager.getStartPage()
                .closeCookie()
                .getBaseMenuBlock()
                .checkMainMenu()
                .findElementOfBaseMenu("Ипотека")
                .findElementOfSecondBaseMenu("Ипотека на вторичное жильё")
                .checkPage()
                .goToFormPage()
                //неккоректный ввод данных
                .fillForm("Стоимость недвижимости","5180000")
                .fillForm("Первоначальный взнос", "3058000")
                .fillForm("Срок кредита","30")
                .clickCheckbox("Страхование жизни и здоровья")
                .checkParameters("Ежемесячный платеж", "21664")
                .checkParameters("Сумма кредита","2122000")
                .checkParameters("Необходимый доход","36829")
                .checkParameters("Процентная ставка","11");
    }
}
