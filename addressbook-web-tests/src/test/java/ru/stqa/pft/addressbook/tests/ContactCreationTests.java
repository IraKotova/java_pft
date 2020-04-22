package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase{

  @Test
  public void testContactCreation() throws Exception {
    app.getContactHelper().initContactCreation();
    app.getContactHelper().fillContactForm(new ContactData("Ivan", "Ivanov", "tester", "Testcom", "8123456789", "test@test.com", "1", "February", "1990", "test1"),true);
    app.getContactHelper().submitNewContact();
    app.getNavigationHelper().returnToHomePage();
  }
}
