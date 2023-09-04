package automation.base;

import automation.Config;
import automation.Session;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

public class BaseGUITest extends BaseTestNG
{
    @BeforeMethod()
    public void before() {
        this.wd().get(String.format("https://%s/%s",
                Config.HTTP_BASE_URL.value,
                Config.HTTP_PAGE.value
                //Config.HTTP_BASE_PORT.value
        ));
    }

    @AfterTest()
    public void after() {
        Session.get().close();
    }

    protected WebDriver wd() {
        return Session.get().webdriver();
    }
}
