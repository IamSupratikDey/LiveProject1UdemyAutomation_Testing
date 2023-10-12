package com.liveproject1.listeners;

import com.liveproject1.base.TestBase;
import com.liveproject1.utils.TestUtils;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.*;

import java.io.IOException;

public class CustomListeners extends TestBase implements ITestListener {


    @Override
    public void onTestStart(ITestResult result) {
      test = rep.startTest(result.getName().toUpperCase());
      if(!(TestUtils.isTestRunnable(result.getName(),excel)))
      {
          throw new SkipException("Skipping the test as the runmode is no");
      }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        TestBase.test.log(LogStatus.PASS,result.getName().toUpperCase()+" "+"Pass");
        rep.endTest(test);
        rep.flush();
    }

    @Override
    public void onTestFailure(ITestResult result) {

        System.setProperty("org.uncommons.reportng.escape-output","false");
        try {
            TestUtils.capturescreenshot();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        TestBase.test.log(LogStatus.FAIL,result.getName().toUpperCase()+"Fail"+result.getThrowable());
        TestBase.test.log(LogStatus.FAIL, test.addScreenCapture(TestUtils.screenshotname));
        Reporter.log("Capturing screenshot");
        Reporter.log("<a target= \"_blank\" href=\""+TestUtils.screenshotname+">Screenshot</a>");
        rep.endTest(test);
        rep.flush();

    }

    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {
       test = rep.startTest(context.getName().toUpperCase());
    }

    @Override
    public void onFinish(ITestContext context) {

    }
}
