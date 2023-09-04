package automation.base;

import automation.Session;
import org.openqa.selenium.WebDriver;

public class BasePageObject
{
    protected WebDriver wd() {
        return Session.get().webdriver();
    }
}
