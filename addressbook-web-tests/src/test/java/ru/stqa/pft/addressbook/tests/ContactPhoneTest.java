package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.Arrays;
import java.util.stream.Collectors;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTest extends TestBase{@BeforeMethod

public void ensurePreconditions() {app.goTo().homePage();
    if (app.contact().all().size() == 0) {
        app.contact().create(new ContactData().withFirstname("test1").withEmail("test@mail.ru").withMobilephone("891322222").withAddress("test").withMobilephone("89132222222"), true);
    }

}
    @Test

    public void testContactPhones() {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
    }

    private String mergePhones(ContactData contact) {

    return Arrays.asList(contact.getHomephone(), contact.getMobilephone(), contact.getWorkphone()).stream().filter((s) -> !s.equals("")).map(ContactPhoneTest::cleaned).collect(Collectors.joining("\n"));

    }

    public static String cleaned(String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }
}