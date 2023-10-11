package pages.googleCloud;

import base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CalculatePage extends Base {

                                         // ***Locators and selectors***//

    //Google Cloud Pricing Calculator selectors//
    @FindBy(css = ".md-paginated>md-pagination-wrapper div.tab-holder.compute")
    WebElement computerEngineButton; //click

    @FindBy(css = "input[ng-model^=\"listingCtrl.computeServer.quantity\"]")
    WebElement amountOfInstancesInput; //input

    @FindBy(css = "input[ng-model^=\"listingCtrl.computeServer.label\"]")
    WebElement whatInstancesForInput; //input

    @FindBy(css="md-select[ng-model^=\"listingCtrl.computeServer.series\"] .md-select-icon")
    WebElement seriesDropDownButton; //click

    @FindBy(css="md-select[ng-model^=\"listingCtrl.computeServer.instance\"] .md-select-icon")
    WebElement machineTypeDropDownButton; //click

    @FindBy(css="md-checkbox[ng-model^=\"listingCtrl.computeServer.addGPUs\"] .md-container")
    WebElement addGPUsCheckButton; //click

    @FindBy(css ="div[ng-if^=\"listingCtrl.computeServer.addGPUs\"] .layout-column md-select[aria-label^=\"GPU type\"]")
    WebElement typeGPUDropDownButton; //click

    @FindBy(css = "md-select[placeholder^=\"Number of GPUs\"] .md-select-icon")
    WebElement numberOfGPUDropDownButton; //click

    @FindBy(css = "md-select[ng-model^=\"listingCtrl.computeServer.ssd\"] .md-select-icon")
    WebElement localSSDDropDownButton; //click

    @FindBy(css = "md-select[ng-model^=\"listingCtrl.computeServer.location\"] .md-select-icon")
    WebElement datacenterLocationDropDownButton; //Click

    @FindBy(css = "md-select[ng-disabled^=\"listingCtrl.isCudDisabled\"] .md-select-icon")
    WebElement committedUsageDropDownButton; //Click

    @FindBy(css = "button[ng-click^=\"listingCtrl.addComputeServer(ComputeEngineForm);\"]")
    WebElement addToEstimateButton; //Click

                                 //Right Side Estimate Window selectors//
    @FindBy(css = "div.cpc-cart-total .ng-binding")
    WebElement totalPrice; //Take text

    @FindBy(css = "div.cpc-cart-buttons #Email\\ Estimate")
    WebElement emailButton; //click

                                    //New Window Email Your Estimate
    @FindBy(css = "form[name=\"emailForm\"]>md-content input[type^=\"email\"]")
    WebElement estimateEmail; //input

    @FindBy(css = "md-dialog-actions.layout-row .md-raised.md-primary")
    WebElement estimateSendEmailButton; //Click

    public CalculatePage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver,this);
    }

                                                //***Methods***//

    // Google Cloud Pricing Calculator methods//

    public CalculatePage computerEngineClick(){
        iFrame();
        wait.until(ExpectedConditions.elementToBeClickable(computerEngineButton)).click();
        System.out.println("Click Engine Button");
        return this;
    }

    public CalculatePage ofInstancesInput(String amount){
        wait.until(ExpectedConditions.elementToBeClickable(amountOfInstancesInput)).sendKeys(amount);
        System.out.println("Amount Instances");
        return this;
    }

    public CalculatePage whatInstancesInput(String instances){
        wait.until(ExpectedConditions.elementToBeClickable(whatInstancesForInput)).sendKeys(instances);
        System.out.println("What Instances");
        return this;
    }

    public CalculatePage seriesListButtonClick(){
        wait.until(ExpectedConditions.elementToBeClickable(seriesDropDownButton)).click();
        System.out.println("Click Series_ List_Button");
        return this;
    }

    public CalculatePage machineTypeDropDownButtonClick(){
        wait.until(ExpectedConditions.elementToBeClickable(machineTypeDropDownButton)).click();
        System.out.println("Click Machine_Type_List Button");
        return this;
    }

    public CalculatePage GPUsCheckButtonClick(){
        wait.until(ExpectedConditions.elementToBeClickable(addGPUsCheckButton)).click();
        System.out.println("Click GPUs CheckBox");
        return this;
    }

    public CalculatePage typeGPUDropDownButtonClick(){
        wait.until(ExpectedConditions.elementToBeClickable(typeGPUDropDownButton)).click();
        System.out.println("Click GPU List_Button");
        return this;
    }

    public CalculatePage numberOfGPUDropDownButtonClick(){
        wait.until(ExpectedConditions.elementToBeClickable(numberOfGPUDropDownButton)).click();
        System.out.println("Click Number_Of_GPU_List Button");
        return this;
    }

    public CalculatePage localSSDDropDownButtonClick(){
        wait.until(ExpectedConditions.elementToBeClickable(localSSDDropDownButton)).click();
        System.out.println("Click SSD List_Button");
        return this;
    }

    public CalculatePage   datacenterLocationClick(){
        wait.until(ExpectedConditions.elementToBeClickable(datacenterLocationDropDownButton)).click();
        System.out.println("Click Data_Center_Location List_Button");
        return this;
    }

    public CalculatePage  committedUsageDropDownButtonClick(){
        wait.until(ExpectedConditions.elementToBeClickable(committedUsageDropDownButton)).click();
        System.out.println("Click Committed_Usage List_Button");
        return this;
    }

    public CalculatePage  addToEstimateButtonClick(){
        wait.until(ExpectedConditions.elementToBeClickable(addToEstimateButton)).click();
        System.out.println("Click Add_To_Estimate Button");
        return this;
    }

                                     //Right Side Estimate Window methods
    public String readNewPasteTitle(){
        return wait.until(ExpectedConditions.elementToBeClickable(totalPrice)).getText();
    }
    public void emailButtonClick(){
        wait.until(ExpectedConditions.elementToBeClickable(emailButton)).click();
        System.out.println("Click EstimateEmail Button");
    }

                                        //New Window Email Your Estimate
    public void estimateWindowEmailInput(){
        iFrame();
        wait.until(ExpectedConditions.elementToBeClickable(estimateEmail)).sendKeys(Keys.CONTROL + "v");
        System.out.println("Enter generate email in Estimate field");
        driver.switchTo().defaultContent();
    }

    public void estimateSendEmailButtonClick(){
        iFrame();
        wait.until(ExpectedConditions.elementToBeClickable(estimateSendEmailButton)).click();
        System.out.println("Click Send Email Button");
        driver.switchTo().defaultContent();
    }

    public CalculatePage calcOptionsCSS(String locator, String value){
        String selector = String.format(locator, value);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(selector))).click();
        return this;
    }

    public CalculatePage calcOptionsXpath(String locator, String value){
        String selector = String.format(locator, value);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(selector))).click();
        return this;
    }

    public void enterGenerateYopMail(){
        estimateWindowEmailInput();
        estimateSendEmailButtonClick();
    }
    public void iFrame(){
        driver.switchTo().frame(driver.findElement(By.cssSelector("iframe[src*='frame/products/calculator/index']")));
        driver.switchTo().frame(driver.findElement(By.id("myFrame")));
    }
}
