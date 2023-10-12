package com.liveproject1.utils;


import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;

import java.io.File;

public class ExtentManager {

   public static ExtentReports extend;

   public static ExtentReports getInstance(){

       if(extend==null)
       {
           extend = new ExtentReports("D:\\Live Project 1 Udemy\\target\\surefire-reports\\html\\extent.html",true, DisplayOrder.OLDEST_FIRST);
           extend.loadConfig(new File("D:\\Live Project 1 Udemy\\src\\main\\resources\\extentconfig\\ReportsConfig.xml"));

       }

       return extend;
   }
}
