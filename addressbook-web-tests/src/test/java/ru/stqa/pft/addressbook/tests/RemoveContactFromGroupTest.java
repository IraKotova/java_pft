package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class RemoveContactFromGroupTest extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {

        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test2"));
        }
        if (app.db().contacts().size() == 0) {
            app.contact().create(new ContactData()
                    .withName("Ivan").withSurname("Ivanov"),true);
            app.contact().selectContact();
            app.contact().submitAddingContact();
            app.goTo().homePage();
        }
        Contacts contacts = app.db().contacts();
        int ContactsWithGroupsCount = 0;
        for (int i = 0; i < contacts.size(); i++) {
            if (app.db().result().get(i).getGroups().size() != 0) {
                ContactsWithGroupsCount++;
            }
        }
        if(ContactsWithGroupsCount == 0){
            app.contact().ContactFilter();
            app.contact().selectContact();
            app.contact().submitAddingContact();
        }
    }

    @Test
    public void removeContact() {

        app.goTo().groupPage();
        int GroupId =  app.group().getGroupID();
        app.goTo().homePage();
        app.contact().filterContactsInGroups(GroupId);
        int id = app.contact().selectContact();
        ContactData removedContact =  app.db().selectedContact(id);
        Groups contactGroupsBefore = removedContact.getGroups();
        app.contact().submitContactRemoving();
        ContactData contactOutOfGroup = app.db().selectedContact(id);
        Groups contactGroupsAfter = contactOutOfGroup.getGroups();
        GroupData removedGroup = app.db().modifiedGroup(GroupId);
        assertThat(contactGroupsAfter, equalTo(contactGroupsBefore.without(removedGroup)));
        System.out.println("ok");
    }
}
