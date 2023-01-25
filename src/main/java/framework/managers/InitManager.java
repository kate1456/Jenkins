package framework.managers;

import framework.utils.PropsConst;

import java.util.concurrent.TimeUnit;

import static framework.managers.DriverManager.*;

public class InitManager {
    private static final TestPropManager props = TestPropManager.getInstance();


    public static void initFramework() {
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts()
                .implicitlyWait(Integer.parseInt(props.getProperty(PropsConst.IMPLICITLY_WAIT)), TimeUnit.SECONDS);
       getDriver().manage().timeouts()
                .pageLoadTimeout(Integer.parseInt(props.getProperty(PropsConst.PAGE_LOAD_TIMEOUT)), TimeUnit.SECONDS);
    }

    public static void quitFramework() {
        quitDriver();
    }
}
