package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0){
        app.goTo().homePage();
        app.contact().create(new ContactData().
                    withName("Ivan").withSurname("Ivanov").withJobtitle("tester").withCompanyname("Testcom").withHomePhone("8123456789").withEmail1("test@test.com").
                    withDay("1").withMonth("February").withYear("1990").withGroup("test 2"),true);
        }
    }

    @Test (enabled = true)
    public void testContactCreation() throws Exception {
        Contacts before = app.db().contacts();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        app.goTo().homePage();
        assertThat(app.contact().count(), equalTo(before.size() - 1));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(deletedContact)));
        verifyContactListUI();
    }
}
