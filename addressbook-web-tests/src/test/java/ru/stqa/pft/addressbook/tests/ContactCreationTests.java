package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {


    @Test
    public void testContactCreation() {
        app.getContactHelper().createContact(new ContactData("Ssdfdf", null, "123", "dfgfg", "test1"), true);
       app.getNavigationHelper().gotoHomePage();
    }

}