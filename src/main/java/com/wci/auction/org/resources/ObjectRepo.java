package com.wci.auction.org.resources;

import com.aventstack.extentreports.ExtentTest;

public class ObjectRepo {
    
    public static ExtentTest getTest() {
        return ExtentReporterNG.getCurrentTest();
    }
}