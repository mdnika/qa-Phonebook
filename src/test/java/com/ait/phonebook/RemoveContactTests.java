package com.ait.phonebook;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RemoveContactTests extends TestBase{

  @BeforeMethod
  public void ensurePrecondition(){
    if (!app.getUser().isLoginLinkPresent()) {
      app.getUser().clickOnSingOutButton();
    }
    //click on login link - //a[contains(.,'LOGIN')] - xpath
    app.getUser().clickOnLoginLink();
    //enter email - [placeholder='Email'] - css
    app.getUser().login();
    // click on ADD link
    app.getContact().clickOnAddLink();
    app.getContact().addContact();
    //click on Save button - .add_form__2rsm2 button - css
    app.getContact().clickOnSaveButton();
  }

  @Test
  public void removeContactPositiveTest() {
    int sizeBefore = app.getContact().sizeOfContacts();
    // text()='Ada'
    //click on contact card - .contact-item_card__2SOIM - css
    app.getContact().removeContact();
    app.getContact().pause(1000);
    int sizeAfter = app.getContact().sizeOfContacts();
    //assert contact card is removed
    Assert.assertEquals(sizeAfter,sizeBefore-1);
  }

}

