package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {


    @Test
    public void testContactCreation() {

        app.getContactHelper().initContactCreation();
        app.getContactHelper().fillContactForm(new ContactData("Ivan", "Petrov", "89132223366", "test@mail.ru", "test1"), true);
        app.getContactHelper().submitContactCreation();
        app.getNavigationHelper().gotoHomePage();
    }

}