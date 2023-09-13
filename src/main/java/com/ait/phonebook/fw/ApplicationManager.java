package com.ait.phonebook.fw;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class ApplicationManager {

  String browser;
  WebDriver driver;
  UserHelper user;
  ContactHelper contact;
  HomePageHelper homePage;

  public ApplicationManager(String browser) {
    this.browser = browser;
  }

  public static By isSignoutButtonPresent() {
    return By.xpath("//button[text()='Login']");
  }

  public void init() {
if (browser.equalsIgnoreCase("chrome")) {
    driver = new ChromeDriver();
  } else if (browser.equalsIgnoreCase("microsoftedge")) {
  driver = new EdgeDriver();
}
    driver.get("https://telranedu.web.app");
// maximize browser window
    driver.manage().window().maximize();
// set implicit timeout
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    user = new UserHelper(driver);
    contact = new ContactHelper(driver);
    homePage = new HomePageHelper(driver);
  }

  public UserHelper getUser() {
    return user;
  }

  public ContactHelper getContact() {
    return contact;
  }

  public HomePageHelper getHomePage() {
    return homePage;
  }

  public void stop() {
    driver.quit();
  }

}
