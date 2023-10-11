package pages.googleCloud;

import base.Base;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CloudMainPage extends Base {

    // ***Locators and selectors***
    @FindBy(css = ".ND91id .p1o4Hf>form[method^=\"GET\"]")
    WebElement searchButton; //click

    @FindBy(css = ".ND91id .p1o4Hf .YSM5S>input.mb2a7b")
    WebElement searchInput; //input

    public CloudMainPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    //Methods
    public void searchButtonClick(){
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
        System.out.println("Click Search_Button");
    }

    public void searchTextInput(String searchText){
        wait.until(ExpectedConditions.elementToBeClickable(searchInput)).sendKeys(searchText);
        System.out.println("Text entered into the Search_Field");
    }

    public void pressEnterButtonInput(){
        wait.until(ExpectedConditions.elementToBeClickable(searchInput)).sendKeys(Keys.ENTER);
        System.out.println("Click Enter_Button");
    }

    public void calculatorSearch(String searchText){
        searchButtonClick();
        searchTextInput(searchText);
        pressEnterButtonInput();
    }
}
