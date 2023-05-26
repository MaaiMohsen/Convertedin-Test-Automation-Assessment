package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FirstPropertyPage {
    private WebDriver driver;
    private By showAllFacilitiesButton = By.cssSelector("button[class='l1j9v1wn b65jmrv v7aged4 dir dir-ltr']");
    private By facilitiesSection = By.xpath("//h3[text()='Parking and facilities']");
    private By poolOption = By.xpath("//div[contains(text(),'pool')]");


    public FirstPropertyPage(WebDriver driver) {
        this.driver = driver;
    }

    public void showAllFacilities(){
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOfElementLocated(showAllFacilitiesButton));
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(showAllFacilitiesButton)).click().perform();
    }

    public Boolean isPropertyHasPool(){
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOfElementLocated(facilitiesSection));
        driver.findElement(facilitiesSection);
        try{
        driver.findElement(facilitiesSection).findElement(poolOption);
        return true;
        }
        catch(Exception e){
            return false;
        }
    }
}
