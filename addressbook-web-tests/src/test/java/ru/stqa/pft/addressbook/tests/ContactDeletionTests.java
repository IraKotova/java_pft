package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        if (app.contact().list().size() == 0){
            app.contact().create(new ContactData().
                    withName("Ivan").withSurname("Ivanov").withJobtitle("tester").withCompanyname("Testcom").withPhone("8123456789").withEmail("test@test.com").
                    withDay("1").withMonth("February").withYear("1990").withGroup("test1"),true);
        }
    }

    @Test (enabled = true)
    public void testContactCreation() throws Exception {
        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;
        app.contact().delete(index);
        app.goTo().homePage();
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(index);
        Assert.assertEquals(before, after);
    }
}
