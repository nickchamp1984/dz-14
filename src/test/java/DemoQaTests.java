import automation.base.BaseGUITest;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class DemoQaTests extends BaseGUITest {

    // Constants -> to move to Constants.java class in utils package
    public static final String BASE_URL = "demoqa.com/";
    public static final String ELEMENTS = "Elements";
    public static final String TABLES = "Web Tables";
    public static final String BUTTONS = "Buttons";
    public static final String ADD = "//button[@id='addNewRecordButton']";
    public static final String FORM_MODAL = "//div[@class='modal-content']";
    public static final String CLICK_ME_BUTTON = "//button[text()='Click Me']";
    public static final String CLICK_MESSAGE_DIV = "//p[@id]";
    public static final String CLICK_MESSAGE = "You have done a dynamic click";
    public static final String EDIT_RECORD = "//span[contains(@id, 'edit-record')]";
    public static final String FIRST_NAME_INPUT_ID = "firstName";
    public static final String LAST_NAME_INPUT_ID = "lastName";
    public static final String EMAIL_INPUT_ID = "userEmail";
    public static final String AGE_INPUT_ID = "age";
    public static final String SALARY_INPUT_ID = "salary";
    public static final String DEPARTMENT_INPUT_ID = "department";
    public static final String SUBMIT_BUTTON = "//button[@id='submit']";
    public static int records;

    // Steps -> to move to Steps.java class in utils package
    @Step
    public WebElement findElementByXpath(String locator) {
        return wd().findElement(By.xpath(locator));
    }

    @Step
    public void selectCategory(String cardName) {
        findElementByXpath("//span[text()='" + cardName + "']/parent::li")
                .click();
    }

    @Step
    public void waitForVisibilityOfElement(String locator) {
        WebDriverWait wait = new WebDriverWait(wd(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
    }

    @Step
    public int countRows(String locator) {
        return wd().findElements(By.xpath(locator)).size();
    }

    @Step
    public WebElement formInput(String inputId) {
        return findElementByXpath("//input[@id='" + inputId + "']");
    }

    @Step
    public void fillInput(String inputId, String inputData) {
        formInput(inputId).clear();
        formInput(inputId).sendKeys(inputData);
    }

    @Step
    public void clickSubmit() {
        findElementByXpath(SUBMIT_BUTTON).click();
    }

    @Step
    public void editRecord(int index) {
        wd().findElements(By.xpath(EDIT_RECORD)).get(index).click();
    }

    // Tests

    @Test
    void testButtons() {
        selectCategory(BUTTONS);
        waitForVisibilityOfElement(CLICK_ME_BUTTON);
        findElementByXpath(CLICK_ME_BUTTON).click();
        waitForVisibilityOfElement(CLICK_MESSAGE_DIV);
        String text = findElementByXpath(CLICK_MESSAGE_DIV).getText();
        System.out.println(text);
        Assert.assertEquals(CLICK_MESSAGE, text, "Text differs");
    }

    @Test
    void addForm() {
        selectCategory(TABLES);
        waitForVisibilityOfElement(ADD);
        records = countRows(EDIT_RECORD);
        findElementByXpath(ADD).click();
        waitForVisibilityOfElement(FORM_MODAL);
        fillInput(FIRST_NAME_INPUT_ID, "Vasya");
        fillInput(LAST_NAME_INPUT_ID, "Pupkin");
        fillInput(AGE_INPUT_ID, "39");
        fillInput(SALARY_INPUT_ID, "1000");
        fillInput(EMAIL_INPUT_ID, "vp@mail.ua");
        fillInput(DEPARTMENT_INPUT_ID, "IT");
        clickSubmit();
        waitForVisibilityOfElement(EDIT_RECORD);
        Assert.assertEquals(records + 1, countRows(EDIT_RECORD),
                "Record was not added");
        editRecord(records);
        waitForVisibilityOfElement(FORM_MODAL);
        String previousValue = formInput(FIRST_NAME_INPUT_ID).getAttribute("value");
        fillInput(FIRST_NAME_INPUT_ID, "Petya");
        clickSubmit();
        editRecord(records);
        Assert.assertNotEquals(previousValue, formInput(FIRST_NAME_INPUT_ID).getAttribute("value"),
                "Record was not updated");
    }
}
