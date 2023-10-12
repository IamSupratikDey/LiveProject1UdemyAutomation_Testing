package com.liveproject1.rough;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestProperties {

    public static void main(String[] args) throws IOException {

        /**
         * Read property file
         */

        Properties config = new Properties();
        Properties OR = new Properties();
        FileInputStream fis = new FileInputStream("D:\\Live Project 1 Udemy\\src\\main\\resources\\properties\\Config.properties");
        config.load(fis);



        fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\resources\\properties\\OR.properties");
        OR.load(fis);

        config.getProperty("browser");
        OR.getProperty("bmloginbutton");
    }
}
