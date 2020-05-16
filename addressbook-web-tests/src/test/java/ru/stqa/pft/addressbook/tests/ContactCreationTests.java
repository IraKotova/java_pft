package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase{

  @Test (enabled = false)
  public void testContactCreation() throws Exception {
    Contacts before = app.contact().all();
    ContactData contact = new ContactData().
            withName("Ivan").withSurname("Ivanov").withJobtitle("tester").withCompanyname("Testcom").withPhone("8123456789").withEmail("test@test.com").
            withDay("1").withMonth("February").withYear("1990").withGroup("test1");
    app.contact().create(contact,true);
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size() + 1));

//    contact.withId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }
}
