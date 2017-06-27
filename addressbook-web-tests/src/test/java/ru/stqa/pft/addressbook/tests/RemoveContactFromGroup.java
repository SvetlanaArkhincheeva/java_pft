package ru.stqa.pft.addressbook.tests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class RemoveContactFromGroup extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.group().all().isEmpty()) {
            app.group().create(new GroupData().withName("Group Name"));
        }

        app.goTo().homePage();
        if (app.contact().all().isEmpty()) {
            app.contact().create(new ContactData().withFirstname("Svetlana").withLastname("Arkhincheeva").withAddress("Lenina street").withMobilephone("54").withEmail("dfdf@sdd.ru"));
        }
    }

    @Test
    public void testRemoveContactFromGroup(){
        Groups groups = app.db().groups();
        Contacts before = app.db().contacts();
        app.goTo().homePage();
        for (GroupData group: groups){
            app.contact().selectDeletedGroupFromList(group);
        }
        Contacts contacts = app.contact().all();
        ContactData initContact = app.db().contactById(contacts.iterator().next().getId());
        app.contact().deleteContactFromGroup(initContact);
        app.goTo().homePage();
        Contacts after = app.db().contacts();
        ContactData contactFromDb = app.db().contactById(initContact.getId());
        assertThat(after, equalTo(before.without(initContact).withAdded(contactFromDb)));
    }
}