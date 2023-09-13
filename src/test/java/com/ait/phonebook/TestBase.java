package com.ait.phonebook;

import com.ait.phonebook.fw.ApplicationManager;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class TestBase {

  protected static ApplicationManager app = new ApplicationManager(System.getProperty("browser",
      BrowserType.CHROME));
  Logger logger = LoggerFactory.getLogger(TestBase.class);

 @BeforeMethod
 public void setUp() {
    app.init();
  }

  @AfterMethod(enabled = true)
  public void terDawn() {
    // close driver
    app.stop();
  }
  @BeforeMethod
  public void startTest(Method m, Object[] p) {
   logger.info("Start test " + m.getName() + "with data: " + Arrays.asList(p));
  }

  @AfterMethod
  public void stopTest(ITestResult result){
   if (result.isSuccess()){
     logger.info("PASSED: " + result.getMethod().getMethodName());
   }
   else {
     logger.error("FAILED: " + result.getMethod().getMethodName() +
     "Screenshot: " + app.getUser().takeScreenShot());
   }
   logger.info("Stop test " );
   logger.info("********************");
  }
}
