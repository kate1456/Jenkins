import framework.managers.InitManager;
import framework.managers.PageManager;
import framework.managers.TestPropManager;
import framework.utils.MyAllureListener;
import framework.utils.PropsConst;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

import static framework.managers.DriverManager.getDriver;
@ExtendWith(MyAllureListener.class)
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
