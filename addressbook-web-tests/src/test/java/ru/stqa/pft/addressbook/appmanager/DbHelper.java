package ru.stqa.pft.addressbook.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import java.util.List;

public class DbHelper {
    private final SessionFactory sessionFactory;
    public DbHelper() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() 
                .build();
        sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
    }

    public Groups groups() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<GroupData> result = session.createQuery("from GroupData").list();
        session.getTransaction().commit();
        session.close();
        return new Groups(result);
    }
    public Contacts contacts() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<ContactData> result = session.createQuery( "from ContactData where deprecated = '0000-00-00'" ).list();
        session.getTransaction().commit();
        session.close();
        return new Contacts(result);
    }

    public GroupData groupById(int id){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        GroupData group = (GroupData) session.createQuery( "from GroupData where group_id = '" + id + "'").getSingleResult();
        session.getTransaction().commit();
        session.close();
        return group;
    }

    public ContactData contactById(int id){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        ContactData contact = (ContactData) session.createQuery( "from ContactData where id = '" + id + "'").getSingleResult();
        session.getTransaction().commit();
        session.close();
        return contact;
    }
    public Contacts contactInGroup() {
        Contacts result = new Contacts();
        Groups groupsFull = groups();
        Contacts contactsFull = contacts();
        for (ContactData contact : contactsFull) {
            if (contact.getGroups().size() < groupsFull.size()) {

               result.add(contact);
            }
        }
        return new Contacts(result);
    }

    public Contacts contactNotInGroup() {
        Contacts result = new Contacts();
        Contacts contacts = contacts();
        for (ContactData contact : contacts) {
            if (contact.getGroups().size() > 0) {
                result.add(contact);
            }
        }
        return new Contacts(result);
    }
}