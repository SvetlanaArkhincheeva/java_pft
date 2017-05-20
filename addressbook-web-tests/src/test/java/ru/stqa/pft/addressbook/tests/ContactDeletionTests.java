package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion() {
        //wd.findElement(By.linkText("home")).click();
        //gotoContactPage();
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteSelectedContacts();


    }
        //returnToContactPage();
        //wd.findElement(By.linkText("home")).click();
    }



