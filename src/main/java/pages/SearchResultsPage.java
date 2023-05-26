package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResultsPage {
     private WebDriver driver;
     private By appliedLocation = By.xpath("//*[@data-index='0']/div");
     private By appliedGuests = By.xpath("//*[@data-index='2']/div");
     private By appliedDates = By.xpath("//*[@data-index='1']/div");
     private By moreFiltersButton = By.cssSelector("button[data-testid='category-bar-filter-button']");
     private By bedRoomsSelections = By.cssSelector("div[aria-label='Bedrooms']");
     private By fiveBedroomsSelection = By.xpath("//*[@id='menuItemButton-5']");
     private By amenitiesArea = By.cssSelector("div[data-plugin-in-point-id='FILTER_SECTION_CONTAINER:MORE_FILTERS_AMENITIES_WITH_SUBCATEGORIES:TAB_ALL_HOMES']");
     private By amenitiesShowMoreButton = By.xpath("//*[@class='_edqkt1v l1j9v1wn dir dir-ltr']");
     private By selectPoolOption = By.id("filter-item-amenities-7-row-title");
     private By confirmSearchWithFiltersButton = By.cssSelector("a[data-testid='filter-modal-confirm']");
     private By allProperties = By.cssSelector("div[class='c5118dh cs12nu1 d1m93iqd dir dir-ltr']");
     private By firstProperty = By.cssSelector("div[class='c4mnd7m dir dir-ltr']");
     private By bedroomsNumber =By.xpath("//span[contains(text(),'bedrooms')]");

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getAppliedLocation(){
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOfElementLocated(appliedLocation));
        return driver.findElement(appliedLocation).getAttribute("textContent");

    }
    public String getNumberOfGuests(){
        return driver.findElement(appliedGuests).getAttribute("textContent");

    }
    public String getChosenDates(){
        return driver.findElement(appliedDates).getAttribute("textContent");

    }

    public void clickMoreFilters(){
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOfElementLocated(moreFiltersButton));
        driver.findElement(moreFiltersButton).click();
    }
    public void chooseFiveBedrooms(){
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOfElementLocated(bedRoomsSelections));
        driver.findElement(moreFiltersButton).findElement(fiveBedroomsSelection).click();
    }

    public void choosePoolOption(){
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOfElementLocated(amenitiesArea));
        driver.findElement(amenitiesArea).findElement(amenitiesShowMoreButton).click();
        driver.findElement(selectPoolOption).click();
    }

    public void searchWithMoreFilters(){
        driver.findElement(confirmSearchWithFiltersButton).click();
    }

    public boolean isBedroomsNumberDisplayed(){
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstProperty));
        try{
            driver.findElement(allProperties).findElement(bedroomsNumber);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
    public FirstPropertyPage selectFirstProperty(){
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(allProperties).findElement(firstProperty)).click().perform();
        for(String winHandle : driver.getWindowHandles()){
            driver.switchTo().window(winHandle);
        }
        return new FirstPropertyPage(driver);
    }
}
