package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.contact().all().size() == 0){
            app.contact().create(new ContactData().
                    withName("Makar").withSurname("Ivanov").withJobtitle("tester").withCompanyname("Testcom").withPhone("8123456789").withEmail("test@test.com").
                    withDay("1").withMonth("February").withYear("1990").withGroup("test1"),true);
        }
    }

    @Test (enabled = false)
    public void testContactModification() {
        Contacts before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact= new ContactData().withId(modifiedContact.getId()).withName("Petr").withSurname("Petrov");
        app.contact().modify(contact);
        Contacts after = app.contact().all();
        assertEquals(after.size(), before.size());
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }
}
