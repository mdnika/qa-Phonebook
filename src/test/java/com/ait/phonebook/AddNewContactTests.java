package com.ait.phonebook;

import com.ait.phonebook.fw.Dataproviders;
import com.ait.phonebook.models.Contact;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddNewContactTests extends TestBase{

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
  }

  @Test
  public void addNewContactPositiveTest() {
    //fill in all input fields - input:nth-child(1) - css
    app.getContact().fillContactForm(
        new Contact()
            .setName("Ada")
            .setSurname("Karoli")
            .setPhone("1234567890")
            .setEmail("karoli@gm.com")
            .setAddress("Mainz")
            .setDescription("Schauspielerin"));
    //click on Save button - .add_form__2rsm2 button - css
    app.getContact().clickOnSaveButton();
    //assert new contact added - h2
    Assert.assertTrue(app.getContact().isContactAdded("Ada"));
  }

  @AfterMethod
  public void postCondition() {
    app.getContact().removeContact();
  }

  @Test(dataProvider = "addNewContact", dataProviderClass = Dataproviders.class)
  public void addNewContactPositiveFromDataProviderTest(String name, String surname, String phone, String email, String address, String desc) {
    //fill in all input fields - input:nth-child(1) - css
    app.getContact().fillContactForm(
        new Contact()
            .setName(name)
            .setSurname(surname)
            .setPhone(phone)
            .setEmail(email)
            .setAddress(address)
            .setDescription(desc));
    //click on Save button - .add_form__2rsm2 button - css
    app.getContact().clickOnSaveButton();
    //assert new contact added - h2
    Assert.assertTrue(app.getContact().isContactAdded(name));
  }
  @Test(dataProvider = "addNewContactFromCSV", dataProviderClass = Dataproviders.class)
  public void addNewContactPositiveFromDataProviderWithCSVTest(Contact contact) {
    //fill in all input fields - input:nth-child(1) - css
    app.getContact().fillContactForm(contact);

    app.getContact().clickOnSaveButton();
    //assert new contact added - h2
    Assert.assertEquals(Integer.toString(app.getContact().sizeOfContacts()),"1");
  }

}
