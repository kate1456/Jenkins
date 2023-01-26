package framework.utils;

import io.qameta.allure.Attachment;
import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static framework.managers.DriverManager.getDriver;

public class MyAllureListener extends AllureJunit5 implements AfterTestExecutionCallback {

    @Attachment(value = "Screenshot", type = "image/png", fileExtension = "png")
    public byte[] getScreenshot() {
        return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
    }

    @Override
    public void afterTestExecution(ExtensionContext extensionContext) {
        if (extensionContext.getExecutionException().isPresent()) {
            getScreenshot();

        }
    }
}
