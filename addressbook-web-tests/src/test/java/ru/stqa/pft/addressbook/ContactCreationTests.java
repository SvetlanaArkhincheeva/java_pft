package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {


    @Test
    public void testContactCreation() {

        initContactCreation();
        fillContactForm(new ContactData("Denis", "Volynkin", "3333333", "test@mail.ru"));
        submitContactCreation();
        returnToContactPage();
    }

}