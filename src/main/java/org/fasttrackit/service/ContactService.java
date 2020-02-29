package org.fasttrackit.service;

import org.fasttrackit.domain.Contact;
import org.fasttrackit.persistance.ContactRepository;
import org.fasttrackit.transfer.CreateContactRequest;
import org.fasttrackit.transfer.GetContactRequest;
import org.fasttrackit.transfer.UpdateContactRequest;

import javax.servlet.FilterConfig;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ContactService {

    private ContactRepository contactRepository = new ContactRepository();

    public void createContact(CreateContactRequest request) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Creating contact: " + request);
        contactRepository.createContact(request);
    }
    public void updateContact( long id, UpdateContactRequest request) throws IOException, SQLException, ClassNotFoundException {
        System.out.println(" Updating contact " + id + " : " + request);
        contactRepository.updateContact(id, request);
    }
    public void deleteContact(long id) throws IOException, SQLException, ClassNotFoundException {
        System.out.println(" Deleting contact " + id);
        contactRepository.deleteContact(id);
    }



    public List<Contact> getContacts() throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Retrieving all contact");
        return contactRepository.getContact();
    }
    public List<Contact> getContactByFirstName(CreateContactRequest request) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("The contact is:");
        return contactRepository.getContactByFirstName(request);
    }

}
