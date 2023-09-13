package com.ait.phonebook.fw;

import com.ait.phonebook.fw.ApplicationManager;
import com.ait.phonebook.fw.HelperBase;
import com.ait.phonebook.models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserHelper extends HelperBase {

  public UserHelper(WebDriver driver) {
    super(driver);
  }

  public void clickOnLoginLink() {
    driver.findElement(By.xpath("//a[contains(.,'LOGIN')]")).click();
  }

  public void clickOnSingOutButton() {
    driver.findElement(By.xpath("//button[contains(.,'Sign Out')]")).click();
  }

  public boolean isLoginLinkPresent() {
    return isElementPresent((By.xpath("//a[contains(.,'LOGIN')]")));
  }

  public void clickOnRegistrationButton() {
    driver.findElement(By.name("registration")).click();
  }

  public void fillLoginRegisterForm(User user) {
    type(By.cssSelector("[placeholder='Email']"), user.getEmail());
    //enter password - [placeholder='Password'] - css
    type(By.cssSelector("[placeholder='Password']"), user.getPassword());
  }

  public void fillLoginRegisterFormForScreenCast(User user) {
    type(By.cssSelector("[placeholder='Email']"), user.getEmail());
    //enter password - [placeholder='Password'] - css
    type(By.cssSelector("[placeholder='Password']"), user.getPassword());
    pause(1000);
  }
  public void clickOnLoginButton() {
    click(ApplicationManager.isSignoutButtonPresent());
  }

  public void login() {
    fillLoginRegisterForm(new User().setEmail("trassa@gmail.com").setPassword("Trassa1234$"));
    //click on Login button
    click(ApplicationManager.isSignoutButtonPresent());
  }
}
