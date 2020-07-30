package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class AddContactToGroupTests extends TestBase{
    private Iterator<ContactData> iterator;

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test2"));
        }

        Contacts contacts = app.db().contacts();
        if (contacts.size() == 0) {
            app.contact().create(new ContactData()
                    .withName("Ivan").withSurname("Ivanov"),true);
            app.goTo().homePage();
        }

        int ContactsWithoutGroupsCount = 0;
        for (int i = 0; i < contacts.size(); i++) {
            if  (app.db().result().get(i).getGroups().size() == 0)
            {
                ContactsWithoutGroupsCount ++;
            }
        }

        if (ContactsWithoutGroupsCount == 0)
        {
            app.contact().create(new ContactData()
                    .withName("Ivan").withSurname("Ivanov"),true);
            app.goTo().homePage();
        }
    }


    @Test
    public void contactAddingToGroupTest() {
        app.goTo().groupPage();
        int groupId = app.group().getGroupID();
        app.goTo().homePage();
        app.contact().ContactFilter();
        int id = app.contact().selectContact();
        ContactData modifiedContact =  app.db().selectedContact(id);
        Groups contactGroupsBefore = modifiedContact.getGroups();
        app.contact().submitAddingContact();
        ContactData contactInGroup = app.db().selectedContact(id);
        Groups contactGroupsAfter = contactInGroup.getGroups();
        GroupData addedGroup = app.db().modifiedGroup(groupId);
        assertThat(contactGroupsAfter, equalTo(contactGroupsBefore.withAdded(addedGroup)));
        System.out.println("ok");
    }
}
