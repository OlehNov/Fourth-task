package tests;

import base.Base;
import base.Var;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import pages.googleCloud.CalculatePage;
import pages.googleCloud.CloudMainPage;
import pages.googleCloud.CloudSearchResult;
import pages.yopMail.Yopmail;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.Set;

public class CloudTest{

    public static final WebDriver driver = Base.getDriver("chrome");
    public  static final CloudMainPage cloudMainPage = new CloudMainPage(driver);
    public  static final CloudSearchResult searchResult = new CloudSearchResult(driver);
    public  static final CalculatePage calculatePage = new CalculatePage(driver);
    public  static final Yopmail yopmail = new Yopmail(driver);
    public  static final Base base = new Base(driver);
    public static final Properties properties = new Properties();
    public static final String CLOUD_URL = "https://cloud.google.com/";
    public static final String YOP_MAIL = "https://yopmail.com/en/email-generator";
    public String search_text;
    public String estimated_bill;
    public String expect_monthly_cost;
    public String instances;
    public String amount;
    public String serious_value;
    public String machine_standard;
    public String card_model;
    public String gpu_amount;
    public String ssd_amount;
    public String address;
    public String years_amount;

    @Before
    public void start() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("src/test/java/test_properties/config.properties");
        properties.load(fileInputStream);
        fileInputStream.close();

        search_text = properties.getProperty("search.text");
        estimated_bill = properties.getProperty("estimated.bill");
        expect_monthly_cost = properties.getProperty("expect.monthly.cost");
        instances = properties.getProperty("instances");
        amount = properties.getProperty("amount");
        serious_value = properties.getProperty("serious.value");
        machine_standard = properties.getProperty("machine.standard");
        card_model = properties.getProperty("card.model");
        gpu_amount = properties.getProperty("gpu.amount");
        ssd_amount = properties.getProperty("ssd.amount");
        address = properties.getProperty("address");
        years_amount = properties.getProperty("years.amount");

        driver.get(CLOUD_URL);
        driver.manage().window().maximize();
    }

    @Test
    public void createNewPasteTest(){
        try{
            cloudMainPage.calculatorSearch(search_text);
            searchResult.firstSearchResultClick();
            calculatePage
                    .computerEngineClick()
                    .whatInstancesInput(instances)
                    .ofInstancesInput(amount)
                    .seriesListButtonClick()
                    .calcOptionsCSS(Var.SERIOUS,serious_value)
                    .machineTypeDropDownButtonClick()
                    .calcOptionsXpath(Var.MACHINE,machine_standard)
                    .GPUsCheckButtonClick()
                    .typeGPUDropDownButtonClick()
                    .calcOptionsCSS(Var.VIDEO_CARD, card_model)
                    .numberOfGPUDropDownButtonClick()
                    .calcOptionsCSS(Var.GPU, gpu_amount)
                    .localSSDDropDownButtonClick()
                    .calcOptionsCSS(Var.SSD,ssd_amount)
                    .datacenterLocationClick()
                    .calcOptionsCSS(Var.LOCATION, address)
                    .committedUsageDropDownButtonClick()
                    .calcOptionsCSS(Var.YEARS, years_amount)
                    .addToEstimateButtonClick()
                    .emailButtonClick();
               //Compare Total Estimated Cost
               Assert.assertEquals("Unexpected result", expect_monthly_cost, calculatePage.readNewPasteTitle());
               //Change tab in browser
               ((JavascriptExecutor)driver).executeScript("window.open()");
               ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
               driver.switchTo().window(tabs.get(1));
               //Open yopEmail page and generate email
               driver.get(YOP_MAIL);
               yopmail.generateEmail();
               //Return to google_cloud page and input generated email
               driver.switchTo().window(tabs.get(0));
               calculatePage.enterGenerateYopMail();
               //Return to previous tab(yopEmail page):
               Set<String> windows = driver.getWindowHandles();
               driver.switchTo().window((String) windows.toArray()[1]);
               //If pop-up
            try {
                WebElement popup = driver.findElement(By.xpath("//div[@id=\"card\"]"));
                if (popup.isDisplayed()) {
                    popup.findElement(By.xpath("//div[@id=\"dismiss-button\"]")).click();
                }
            } catch (org.openqa.selenium.NoSuchElementException continue_test) {
                //Check payment cost
                yopmail.checkCost();
                Assert.assertEquals("Unexpected result", estimated_bill, yopmail.checkYopMailCost());
            }
        } catch (Exception test_fall){
            makeScreenshot();
        }
    }
    @After
    public void end(){
        driver.quit();
    }

    public void makeScreenshot() {
        try {
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String time = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String screenshotName = "src/test/java/screenshots/screenshot_" + time + ".png";
            File targetFile = new File(screenshotName);
            FileUtils.copyFile(screenshotFile, targetFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

