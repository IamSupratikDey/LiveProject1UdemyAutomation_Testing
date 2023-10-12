package com.liveproject1.testcases;

import com.liveproject1.base.TestBase;
import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AddCustomerTest extends TestBase {


    @Test(dataProvider = "getData")
    public void AddCustomerTest(String firstName, String LastName, String postCode) throws InterruptedException {
        click("addCustBtn_CSS");
        type("firstname_CSS",firstName);
        type("lastname_XPATH",LastName);
        driver.findElement(By.cssSelector(OR.getProperty("postcode_CSS"))).sendKeys(postCode);
        driver.findElement(By.cssSelector(OR.getProperty("addbtn_CSS"))).click();
        Thread.sleep(2000);
        driver.switchTo().alert().accept();
        Thread.sleep(2000);

    }

    @DataProvider
    public Object[][] getData(){

        String sheetName = "AddCustomerTest";
        int rows = excel.getRowCount(sheetName);
        int cols = excel.getColumnCount(sheetName);

        Object [][] data = new Object[rows-1][cols] ;

           for(int rowNum=2 ; rowNum <=rows; rowNum++)
           {
                 for(int colNum = 0; colNum < cols; colNum++)
                 {
                     data[rowNum -2][colNum] = excel.getCellData(sheetName, colNum, rowNum);
                 }
           }

           return data;
    }
}
