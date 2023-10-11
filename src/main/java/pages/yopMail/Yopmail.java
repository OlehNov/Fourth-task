package pages.yopMail;

import base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Yopmail extends Base {

    // ***Locators and selectors***//
    @FindBy(css = ".nw>button[onclick^=\"newgen();\"]")
    public static WebElement newButton; //click
    @FindBy(css = ".nw .tooltip")
    WebElement copyButton; //click
    @FindBy(css = ".nw>button[onclick^=\"egengo();\"]")
    WebElement checkInbox; //click
    @FindBy(css = "td>h2")
    WebElement yopMailMonthlyCost; //text
    @FindBy(xpath = "//button[@id=\"refresh\"]")
    WebElement refreshButton;//click

    //Methods
    public Yopmail(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void newRefreshButtonClick() {
        wait.until(ExpectedConditions.elementToBeClickable(newButton)).click();
        System.out.println("Click new Button");
    }

    public void copyButtonClick() {
        wait.until(ExpectedConditions.elementToBeClickable(copyButton)).click();
        System.out.println("Click copy Button");
    }

    public void checkInboxClick() {
        wait.until(ExpectedConditions.elementToBeClickable(checkInbox)).click();
        System.out.println("Click check inbox Button");
    }

    public void emailRefreshClick() {
        wait.until(ExpectedConditions.elementToBeClickable(refreshButton)).click();
        System.out.println("Click refresh email Button");
    }

    public String checkYopMailCost(){
        iFrameYop();
        return wait.until(ExpectedConditions.elementToBeClickable(yopMailMonthlyCost)).getText();
    }

    public void generateEmail(){
        //Scroll page to the generate button
        js.executeScript("arguments[0].scrollIntoView(true);", newButton);
        //Generate email
        newRefreshButtonClick();
        copyButtonClick();
    }

    public void checkCost(){
        checkInboxClick();
        emailRefreshClick();
    }
    public void iFrameYop(){
        driver.switchTo().frame(driver.findElement(By.cssSelector("iframe[name^='ifmail']")));
    }
}
