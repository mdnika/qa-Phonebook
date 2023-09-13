package com.ait.phonebook;

import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTests extends TestBase{

  @Test
  public void isHomeComponentPresentTest() {
    //css  -> div:nth-child(2)>div>div
   // driver.findElement(By.xpath("//div[2]/div/div"));
   // isHomeComponentPresent();
   // System.out.println("Home Component is: " + isHomeComponentPresent());
   // System.out.println("Home Component is: " + isElementPresent(By.xpath("//div[2]/div/div")));
   // System.out.println("Home Component is: " + isHomeComponentPresent());
    Assert.assertTrue(app.getHomePage().isHomeComponentPresent());
  }

}
