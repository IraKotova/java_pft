package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {
    @Test
    public void testContactModification() {
        app.getContactHelper().selectContact();
        if (! app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData("Ivan", "Ivanov", "tester", "Testcom", "8123456789", "test@test.com", "1", "February", "1990", "test1"),true);
        }
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("Petr", "Petrov", null, null, null, null, null, null, null, null),false);
        app.getContactHelper().submitContactModification();
    }
}