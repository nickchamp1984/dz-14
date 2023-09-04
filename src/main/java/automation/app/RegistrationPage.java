package automation.app;

import automation.base.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class RegistrationPage extends BasePageObject
{
    final public WebElement firstName      = this.wd().findElement(By.xpath("//input[@id='input-firstname']"));
    final public WebElement lastName       = this.wd().findElement(By.xpath("//input[@id='input-lastname']"));
    final public WebElement email          = this.wd().findElement(By.xpath("//input[@id='input-email']"));
    final public WebElement password       = this.wd().findElement(By.xpath("//input[@id='input-password']"));
    final public WebElement newsLetter     = this.wd().findElement(By.xpath("//input[@id='input-newsletter']"));
    final public WebElement policyAgree    = this.wd().findElement(By.xpath("//form[@id='form-register']//input[@name='agree']"));
    final public WebElement continueButton = this.wd().findElement(By.xpath("//form[@id='form-register']//button[@type='submit']"));

}
