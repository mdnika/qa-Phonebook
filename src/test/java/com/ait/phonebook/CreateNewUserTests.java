package com.ait.phonebook;

import com.ait.phonebook.models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CreateNewUserTests extends TestBase{
  @BeforeMethod
  public void ensurePrecondition(){
    //precondition user should be logged out
    if (!app.getUser().isLoginLinkPresent()) {
      app.getUser().clickOnSingOutButton();
    }
    //click on login link
    app.getUser().clickOnLoginLink();
  }

  @Test
  public void createNewUserPositiveTest() {
    //enter email & password
    app.getUser().fillLoginRegisterForm(new User().setEmail("trassa@gmail.com").setPassword("Trassa1234$"));

    //click on Registration button - registration - name
    app.getUser().clickOnRegistrationButton();
    //assert Sign out button present - //button[contains(.,'Sign Out')] - xpath
    //Assert.assertTrue(isElementPresent(By.xpath("//button[contains(.,'Sign Out')]")));
    Assert.assertTrue(app.getUser().isAlertPresent());
    //Registration failed with code 409
  }

}
