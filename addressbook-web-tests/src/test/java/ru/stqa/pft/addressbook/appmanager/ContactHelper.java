package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    public void modify(ContactData contact) {

        initContactModification(contact.getId());
        fillContactForm(contact, false);
        submitContactModification();
        contactCache = null;
        isAlertPresent();

    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("home"), contactData.getPhonenumber());
        type(By.name("email"), contactData.getEmail());

        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void submitContactCreation() {
        click(By.name("submit"));
    }

    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }
    public void selectContactById(int id) {
        wd.findElement (By.cssSelector("input[value='" + id + "']")).click();
    }


    public void deleteSelectedContacts() {
        click(By.xpath("//input[@value='Delete']"));
        wd.switchTo().alert().accept();
    }

    public void initContactModification(int id) {

        wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();

    }


    public void submitContactModification() {
        click(By.name("update"));
    }

    public void create(ContactData contact, boolean creation) {
        initContactCreation();
        fillContactForm(contact, true);
        submitContactCreation();
        contactCache = null;
    }


    public boolean isThereContact() {
        return isElementPresent((By.name("selected[]")));
    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    private Contacts contactCache = null;



    public Contacts all() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }

        contactCache = new Contacts();

        List<WebElement> elements = wd.findElements(By.name("entry"));

        for (WebElement element : elements) {

            String firstname = element.findElement(By.xpath(".//td[3]")).getText();

            String lastname = element.findElement(By.xpath(".//td[2]")).getText();

            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            contactCache.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname));
        }
        return new Contacts(contactCache);
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteSelectedContacts();
        contactCache = null;

    }
}
