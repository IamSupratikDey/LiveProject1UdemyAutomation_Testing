package com.liveproject1.testcases;

import com.liveproject1.base.TestBase;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTest extends TestBase {

    @Test
    public void LoginTest ()
    {
        log.debug("Inside Login Test");
        click("bmlBtn_CSS");

        Assert.assertTrue(isElementPresent(By.cssSelector(OR.getProperty("addCustBtn_CSS"))), "Login not successful");

    }
}
