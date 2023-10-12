package com.liveproject1.base;

import com.liveproject1.utils.ExcelReader;
import com.liveproject1.utils.ExtentManager;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;




public class TestBase {
    /**
     * WebDriver
     * Properties
     * logs
     * ExtentReports
     * DB
     * Excel Reading
     * Mailing part
     * log4jproperties
     *
     */

    public static WebDriver driver;

    public static String browser;
    public static Properties config = new Properties();
    public static Properties OR = new Properties();
    public static FileInputStream fis;

    public static Logger log = Logger.getLogger("devpinoylogger");

    public static ExcelReader excel = new ExcelReader("D:\\Live Project 1 Udemy\\src\\main\\resources\\excels\\testdata.xlsx");

    public ExtentReports rep = ExtentManager.getInstance();

    public static ExtentTest test;
    @BeforeSuite
    public void setup() throws IOException {

        if(driver==null)
        {
            FileInputStream fis = new FileInputStream("D:\\Live Project 1 Udemy\\src\\main\\resources\\properties\\Config.properties");
            config.load(fis);
            log.debug("Config File Loaded successfully");
            fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\resources\\properties\\OR.properties");
            OR.load(fis);
        }


        if(System.getenv("browser") !=null && !System.getenv("browser").isEmpty())
        {
            browser = System.getenv("browser");

        } else {
            browser = config.getProperty("browser");
        }

        config.setProperty("browser",browser);

        if(config.getProperty("browser").equals("firefox"))
        {
            System.setProperty("webdriver.gecko.driver", "D:\\Live Project 1 Udemy\\src\\main\\resources\\executables\\geckodriver.exe");
            driver = new FirefoxDriver();
            log.debug("Firefox Launched");
        }
        else if(config.getProperty("browser").equals("chrome"))
        {
              System.setProperty("webdriver.chrome.driver", "D:\\Live Project 1 Udemy\\src\\main\\resources\\executables\\chromedriver.exe");
              driver = new ChromeDriver();
            log.debug("Chrome Launched");
        }
        else if(config.getProperty("browser").equals("IE"))
        {
            System.setProperty("webdriver.ie.driver", "D:\\Live Project 1 Udemy\\src\\main\\resources\\executables\\IEDriverServer.exe");
            driver = new InternetExplorerDriver();
        }

        driver.get(config.getProperty("testsiteurl"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")), TimeUnit.SECONDS);
    }



    public void click(String locator) {

        if (locator.endsWith("_CSS")) {
            driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
        } else if (locator.endsWith("_XPATH")) {
            driver.findElement(By.xpath(OR.getProperty(locator))).click();
        } else if (locator.endsWith("_ID")) {
            driver.findElement(By.id(OR.getProperty(locator))).click();
        }

    }

    public void type(String locator, String value) {

        if (locator.endsWith("_CSS")) {
            driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(value);
        } else if (locator.endsWith("_XPATH")) {
            driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
        } else if (locator.endsWith("_ID")) {
            driver.findElement(By.id(OR.getProperty(locator))).sendKeys(value);
        }
    }
        WebElement dropdown;

        public void select(String locator, String value)
        {
            if (locator.endsWith("_CSS")) {
                dropdown = driver.findElement(By.cssSelector(OR.getProperty(locator)));
            } else if (locator.endsWith("_XPATH")) {
                dropdown = driver.findElement(By.xpath(OR.getProperty(locator)));
            } else if (locator.endsWith("_ID")) {
                dropdown = driver.findElement(By.id(OR.getProperty(locator)));
            }

            Select select = new Select(dropdown);
            select.selectByVisibleText(value);
        }


    public boolean isElementPresent(By by)
    {
        try
        {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {

            return false;
        }
    }
    @AfterSuite
    public void tearDown(){

        if(driver!=null) {
            driver.quit();
        }

        log.debug("Successfully executed");
    }
}
