package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import ru.stqa.pft.addressbook.model.GroupData;


public class ContactAddressTest extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();

        if (app.group().all().isEmpty()) {

            app.group().create(new GroupData()

                    .withName("test1"));

        }
        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData().withFirstname("Svetlana").withEmail("test1").withEmail2("test2").withEmail3("test3").withMobilephone("89132222222").withHomephone("123").withWorkphone("123").withAddress("test"));
        }

    }

    @Test
    public void testContactAddess() {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
    }
}