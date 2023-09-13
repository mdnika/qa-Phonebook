package com.ait.phonebook;

import com.ait.phonebook.models.User;
import java.awt.AWTException;
import java.io.IOException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase{

  @BeforeMethod
  public void ensurePrecondition(){
    //precondition user should be logged out
    if (!app.getUser().isLoginLinkPresent()) {
      app.getUser().clickOnSingOutButton();
    }
    //click on login link - //a[contains(.,'LOGIN')] - xpath
    app.getUser().clickOnLoginLink();
  }

  @Test
  public void loginPositiveTest() {
    //enter email - [placeholder='Email'] - css
    app.getUser().fillLoginRegisterForm(new User().setEmail("trassa@gmail.com").setPassword("Trassa1234$"));
    //click on Login button
    app.getUser().clickOnLoginButton();
    //assert is Sign out button present
    Assert.assertTrue(app.getUser().isElementPresent(By.xpath("//button[contains(.,'Sign Out')]")));

  }
  @Test
  public void loginNegativeTest() {
    //enter email - [placeholder='Email'] - css
    app.getUser().fillLoginRegisterForm(new User().setEmail("trassa@gmail.com"));
    //click on Login button
    app.getUser().clickOnLoginButton();
    //assert is Sign out button present
    Assert.assertTrue(app.getUser().isAlertPresent());

  }
  @Test
  public void loginPositiveTestWithScreenCast() throws IOException, AWTException {
  app.getUser().deleteScreenCast("record");
   app.getUser().startRecording();
    app.getUser().fillLoginRegisterForm(new User().setEmail("trassa@gmail.com").setPassword("Trassa1234$"));
    //click on Login button
    app.getUser().clickOnLoginButton();
    app.getUser().pause(2000);
    app.getUser().stopRecording();
    //assert is Sign out button present
   // Assert.assertTrue(app.getUser().isElementPresent(By.xpath("//button[contains(.,'Sign Out')]")));

  }

}
