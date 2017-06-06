package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

    @Test

    public void testContactModification() {
       app.getNavigationHelper().gotoHomePage();
        if (! app.getContactHelper().isThereContact()) {
            app.getContactHelper().createContact(new ContactData("Tsdfsf", "dfdg", "dfdf", "dfdf","test1"), true);
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("Нина", "Петрова", "8913456366", "test@gmail.com",null), false);
        app.getContactHelper().submitContactModification();
        app.getContactHelper().isAlertPresent();


    }
}
