package ru.stqa.pft.addressbook;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase{

    @Test
    public void testContactDeletion() {
        //wd.findElement(By.linkText("home")).click();
        //gotoContactPage();
        selectContact();
        deleteSelectedContacts();


    }
        //returnToContactPage();
        //wd.findElement(By.linkText("home")).click();
    }



