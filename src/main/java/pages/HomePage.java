package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class HomePage {
    private WebDriver driver;
    private By closePopUpButton = By.className("_1swasop");
    private By startSearch = By.cssSelector("button[data-index='0']");
    private By locationItaly = By.xpath("//*[@id=\"locationInspirationsSectionID\"]/div/div[5]");
    private By chooseCheckInDate = By.xpath("//*[@id=\"search-tabpanel\"]/div/div[3]/div[1]/div/div");
    private By chooseCheckOutDate = By.xpath("//*[@id=\"search-tabpanel\"]/div/div[3]/div[4]/div[1]/div");
    private By chooseGuests = By.xpath("//*[@id=\"search-tabpanel\"]/div/div[5]/div[1]/div[1]/div");
    private By adultsIncreaseButton = By.cssSelector("button[data-testid='stepper-adults-increase-button']");
    private By childrenIncreaseButton = By.cssSelector("button[data-testid='stepper-children-increase-button']");
    private By searchButton = By.cssSelector("button[data-testid='structured-search-input-search-button']");


    public HomePage (WebDriver driver){
        this.driver = driver;
    }

    public void closeHomePopUp(){
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOfElementLocated(closePopUpButton));
        driver.findElement(closePopUpButton).click();
    }

    public void startSearch(){

        driver.findElement(startSearch).click();
    }
    public void chooseItalyLocation(){

        driver.findElement(locationItaly).click();
    }

    public void chooseDates(){
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(chooseCheckInDate)).click(); //Open choose dates section

        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("d, EEEE, MMMM yyyy");
        String checkInDate = sdf.format(c.getTime());
        driver.findElement(By.xpath("//*[contains(@aria-label,'"+checkInDate+"')]")).click(); //set check in date to be today

        actions.moveToElement(driver.findElement(chooseCheckOutDate)).click();
        c.add(Calendar.DATE, 7);
        String checkOutDate = sdf.format(c.getTime());
        driver.findElement(By.xpath("//*[contains(@aria-label,'"+checkOutDate+"')]")).click(); //set checkout date to be after 7 days from days
    }

    public void ChooseGuestsNumber(int adults, int children){
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(chooseGuests)).click().perform(); //Open choose guests number area
        for (int i = 0; i < adults; i++)
            actions.moveToElement(driver.findElement(adultsIncreaseButton)).click().perform(); //Choose number of adult guests

        for (int i = 0; i < children; i++)
            actions.moveToElement(driver.findElement(childrenIncreaseButton)).click().perform(); // Choose number of children
    }

    public SearchResultsPage performSearch(){
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(searchButton)).click().perform(); //Search by assigned filters
        driver.navigate().refresh();  //refresh POM after search
        return new SearchResultsPage(driver);  //Return page of search results
    }

}
