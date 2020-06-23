package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase{

  @Test (enabled = true)
  public void testContactCreation() throws Exception {
    File photo = new File("src/test/resources/avatar.jpg");
    Contacts before = app.contact().all();
    ContactData contact = new ContactData().
            withName("Ivan").withSurname("Ivanov")
            .withJobtitle("tester").withCompanyname("Testcom").withHomePhone("123").withMobilePhone("456").withWorkPhone("789")
            .withAddress("123 Street House")
            .withEmail1("test@test.com").withEmail2("test2@").withEmail3("test")
            .withDay("1").withMonth("February").withYear("1990")
            .withGroup("test1")
            .withPhoto(photo);
    app.contact().create(contact,true);
    Contacts after = app.contact().all();
    assertThat(app.contact().count(), equalTo(before.size() + 1));
//    contact.withId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }

  @Test (enabled = false)
  public void testBadContactCreation() throws Exception {
    Contacts before = app.contact().all();
    ContactData contact = new ContactData().
            withName("Ivan'").withSurname("Ivanov")
            .withJobtitle("tester").withCompanyname("Testcom").withHomePhone("123").withMobilePhone("456").withWorkPhone("789")
            .withAddress("123 Street House")
            .withEmail1("test@test.com").withEmail2("test2@").withEmail3("test")
            .withDay("1").withMonth("February").withYear("1990").withGroup("test1");
    app.contact().create(contact,true);
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before));
  }
}
