package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {
    @DataProvider
    public Iterator<Object[]> validContacts() {
        List<Object[]> list = new ArrayList<Object[]>();
        list.add(new Object[] {"firstname1", "lastname1", "address1", "home1", "mobile1", "work1", "email 11", "email2", "email3"});
        //list.add(new Object[] {"firstname2", "lastname2","test2", "phone 2", "email 2", "group2", "address2", "mobile2"});
        //list.add(new Object[] {"firstname3", "lastname3","test3", "phone 3", "email 3", "group3", "address3", "mobile3"});
        return list.iterator();
    }


    @Test(dataProvider = "validContacts")
    public void testContactCreation(String firstname, String lastname, String address, String home, String mobile, String work, String email, String email2, String email3) {
        //File photo = new File("src/test/resources/stru.png");
        ContactData contact = new ContactData().withFirstname(firstname).withLastname(lastname).withAddress(address).withHomephone(home).withMobilephone(mobile).withWorkphone(work).withtEmail(email).withEmail2(email2).withEmail3(email3);
        app.goTo().homePage();
        Contacts before = app.contact().all();


        app.contact().create(contact, true);
        app.goTo().homePage();
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.contact().all();
        assertThat(after.size(), equalTo(before.size() + 1));
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));

    }

    @Test (enabled = false)
    public void testBadContactCreation() {
        app.goTo().homePage();
        Contacts before = app.contact().all();
        ContactData contact = new ContactData().withFirstname("Svetlana'").withLastname("Arkhincheeva").withPhonenumber("123").withtEmail("test@test.ru").withGroup("test1");
        app.contact().create(contact, true);
        app.goTo().homePage();
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before));
    }
}