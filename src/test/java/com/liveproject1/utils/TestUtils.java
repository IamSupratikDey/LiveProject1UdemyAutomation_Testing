package com.liveproject1.utils;

import com.liveproject1.base.TestBase;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;

public class TestUtils extends TestBase {


    public static String screenshotpath;
    public static String screenshotname;
    public static void capturescreenshot() throws IOException {
        Date d=new Date();
        screenshotname= d.toString().replace(":","_").replace(" ","_")+".jpg";
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile,new File("D:\\Live Project 1 Udemy\\target\\surefire-reports\\html\\"+screenshotname));
    }

    @DataProvider(name="dp")
    public Object[][] getData(Method m){

        String sheetName = m.getName();
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

    public static boolean isTestRunnable(String testName, ExcelReader excel){

        String sheetName="test_suite";
        int rows = excel.getRowCount(sheetName);


        for(int rNum=2; rNum<=rows; rNum++){

            String testCase = excel.getCellData(sheetName, "TCID", rNum);

            if(testCase.equalsIgnoreCase(testName)){

                String runmode = excel.getCellData(sheetName, "Runmode", rNum);

                if(runmode.equalsIgnoreCase("Y"))
                    return true;
                else
                    return false;
            }


        }
        return false;
    }
}
