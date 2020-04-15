package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UpdateContactData;

public class ContactModificationTests extends TestBase {
    @Test
    public void testContactModification() {
        app.getContactHelper().selectContact();
        app.getContactHelper().initContactModification();
        app.getContactHelper().updateContactForm(new UpdateContactData("Petr", "Petrov", "tester", "Testcom", "8123456789", "test@test.com", "1", "February", "1990"));
        app.getContactHelper().submitContactModification();
    }
}
