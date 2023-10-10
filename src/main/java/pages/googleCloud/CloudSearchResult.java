package pages.googleCloud;

import base.Base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CloudSearchResult extends Base {

    @FindBy(css = "div.gsc-thumbnail-inside .gs-title>a[data-ctorig^=\"https://cloud.google.com/products/calculator\"]")
    WebElement firstSearchResult; //click

    public void firstSearchResultClick(){
        wait.until(ExpectedConditions.elementToBeClickable(firstSearchResult)).click();
        System.out.println("Click First_Search_Result");
    }
    public CloudSearchResult(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }
}
