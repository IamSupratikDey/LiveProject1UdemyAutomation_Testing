package com.liveproject1.testcases;

import com.liveproject1.base.TestBase;
import com.liveproject1.utils.TestUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class OpenAccountTest extends TestBase {



    @Test(dataProviderClass = TestUtils.class,dataProvider = "dp")
    public void OpenAccountTest(String customer,String currency) throws InterruptedException {

        click("openaccount_CSS");
        select("customer_CSS", customer);
        select("currency_CSS", currency);
        click("process_CSS");
        Thread.sleep(2000);

       driver.switchTo().alert().accept();


    }


}
