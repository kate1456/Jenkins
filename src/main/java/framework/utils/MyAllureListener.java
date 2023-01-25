package framework.utils;

import framework.managers.DriverManager;
import io.qameta.allure.Attachment;
import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class MyAllureListener extends AllureJunit5 implements /*TestWatcher*/AfterTestExecutionCallback {

@Attachment(value = "Screenshot", type = "image/png",fileExtension = "png")
public byte[] getScreenshot(){
    return ((TakesScreenshot) DriverManager.getInstance().getDriver()).getScreenshotAs(OutputType.BYTES);
}

//@Override
//    public void testFailed(ExtensionContext context, Throwable cause){
//    getScreenshot();
//
//}
    @Override
    public void afterTestExecution(ExtensionContext extensionContext) {
        if(extensionContext.getExecutionException().isPresent()){
            getScreenshot();

        }
    }
}
