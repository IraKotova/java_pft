package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import org.openqa.selenium.json.TypeToken;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase{

  @DataProvider
  public Iterator<Object[]> validContactsFromXml() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")))) {
      String xml = "";
      String line = reader.readLine();
      while (line != null) {
        xml += line;
        line = reader.readLine();
      }
      XStream xstream = new XStream();
      xstream.processAnnotations(ContactData.class);
      List<ContactData> contacts = (List<ContactData>) xstream.fromXML(xml);
      return contacts.stream().map((c) -> new Object[] {c}).collect(Collectors.toList()).iterator();
    }
  }
  @DataProvider
  public Iterator<Object[]> validContactsFromJson() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>(){}.getType());
      return contacts.stream().map((c) -> new Object[] {c}).collect(Collectors.toList()).iterator();
    }
  }

  @Test (dataProvider = "validContactsFromJson")
  public void testContactCreation(ContactData contact) throws Exception {
    Contacts before = app.contact().all();
    File photo = new File("src/test/resources/avatar.png");
/*    ContactData contact = new ContactData(contact)
            .withId(id)
            .withName(name).withSurname(surname)
           .withJobtitle("tester").withCompanyname("Testcom").withHomePhone("123").withMobilePhone("456").withWorkPhone("789")
            .withAddress("123 Street House")
            .withEmail1("test@test.com").withEmail2("test2@").withEmail3("test")
            .withDay("1").withMonth("February").withYear("1990")
             .withPhoto(photo)
            .withGroup(group)
            ;*/
    app.contact().create(contact,true);
    Contacts after = app.contact().all();
    assertThat(app.contact().count(), equalTo(before.size() + 1));
//    contact.withId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    verifyContactListUI();
  }

  @Test (enabled = false)
  public void testBadContactCreation() throws Exception {
    Contacts before = app.db().contacts();
    ContactData contact = new ContactData().
            withName("Ivan'").withSurname("Ivanov")
            .withJobtitle("tester").withCompanyname("Testcom").withHomePhone("123").withMobilePhone("456").withWorkPhone("789")
            .withAddress("123 Street House")
            .withEmail1("test@test.com").withEmail2("test2@").withEmail3("test")
            .withDay("1").withMonth("February").withYear("1990").withGroup("test1");
    app.contact().create(contact,true);
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before));
  }

  @Test (enabled = false)
    public void testCurrDir() {
      File currDir = new File (".");
      System.out.println(currDir.getAbsolutePath());
  }
}
