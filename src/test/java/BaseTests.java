import framework.managers.InitManager;
import framework.managers.PageManager;
import framework.managers.TestPropManager;
import framework.utils.PropsConst;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static framework.managers.DriverManager.getDriver;

public class BaseTests {
    protected PageManager pageManager = PageManager.getInstance();
    private TestPropManager propManager = TestPropManager.getInstance();
    @BeforeAll
    public static void beforeClass(){
       InitManager.initFramework();
    }
    @BeforeEach
    public void before() {
        getDriver().get(propManager.getProperty(PropsConst.BASE_URL));
    }
    @AfterAll
    public static void after() {
        InitManager.quitFramework();
    }


}
