package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
public class AddContactToGroup extends TestBase {
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
    public void AddContact() {
        app.goTo().homePage();
        Contacts before = app.db().contacts();
        ContactData rowWithContact = null;
        List<ContactData> list = new ArrayList<ContactData>(before);
        for (int i = 0; i < list.size(); i++) {
            rowWithContact = list.get(i);

        }
        app.contact().contactToGroup(rowWithContact);
        app.goTo().homePage();
        Contacts after = app.db().contacts();
        ContactData contactFromDb = app.db().contactById(rowWithContact.getId());
        assertThat(after, equalTo(before.without(rowWithContact).withAdded(contactFromDb)));
    }
}


