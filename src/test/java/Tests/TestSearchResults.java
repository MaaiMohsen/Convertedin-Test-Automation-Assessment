package Tests;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.FirstPropertyPage;
import pages.SearchResultsPage;

import java.text.SimpleDateFormat;
import java.util.Calendar;

//Test are designed to run sequentially
public class TestSearchResults extends BaseTests {
    SearchResultsPage searchResultsPage;

    @Test
    public void test1(){
        homePage.closeHomePopUp();
        homePage.startSearch();
        homePage.chooseItalyLocation();
        homePage.chooseDates();
        homePage.ChooseGuestsNumber(2,1);
        searchResultsPage = homePage.performSearch();

        Assert.assertEquals("Italy",searchResultsPage.getAppliedLocation());
        Assert.assertEquals("3 guests",searchResultsPage.getNumberOfGuests());

        Calendar checkIn = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM d");
        String checkInDate = sdf.format(checkIn.getTime());
        Calendar checkOut = Calendar.getInstance();
        checkOut.add(Calendar.DATE, 7);
        String checkOutDate = sdf.format(checkOut.getTime());
        String date;
        if(checkIn.get(Calendar.MONTH) == checkOut.get(Calendar.MONTH))
            date = checkInDate +" – " + Integer.parseInt(checkOutDate.replaceAll("[^0-9]", ""));
        else
            date = checkInDate +" – " + checkOutDate;
        Assert.assertEquals(date,searchResultsPage.getChosenDates());
    }
    @Test
    public void test2(){
        searchResultsPage.clickMoreFilters();
        searchResultsPage.chooseFiveBedrooms();
        searchResultsPage.choosePoolOption();
        searchResultsPage.searchWithMoreFilters();
        Assert.assertTrue(searchResultsPage.isBedroomsNumberDisplayed());
        FirstPropertyPage firstPropertyPage = searchResultsPage.selectFirstProperty();

        firstPropertyPage.showAllFacilities();
        Assert.assertTrue(firstPropertyPage.isPropertyHasPool());

    }

}
