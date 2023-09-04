package automation.app;

import automation.base.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class IndexPage extends BasePageObject
{
    WebElement personalCabinet = this.wd().findElement(By.xpath("(//nav[@id='top']//div[contains(@class, 'nav')]/ul)[2]/li[2]/div/a"));
    WebElement registrationLink = personalCabinet.findElement(By.xpath("../ul/li/a[contains(@href,'route=account/register')]"));

    public RegistrationPage goToRegistrationPage() {
        if (!registrationLink.isDisplayed())
            personalCabinet.click();
        registrationLink.click();

        return new RegistrationPage();
    }
}
