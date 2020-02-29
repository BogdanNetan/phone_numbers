package org.fasttrackit;



import org.fasttrackit.domain.Contact;
import org.fasttrackit.persistance.ContactRepository;
import org.fasttrackit.transfer.CreateContactRequest;
import org.fasttrackit.transfer.UpdateContactRequest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws SQLException, IOException, ClassNotFoundException {
        ContactRepository contactRepository = new ContactRepository();

//        CreateContactRequest request = new CreateContactRequest();
//        request.setFirstName("Silas");
//        request.setSecondName("Adrian");
//        request.setPhoneNumber("0749364340");
//        request.setFirstName("Bogdan");
//        request.setSecondName("Netan");
//        request.setPhoneNumber("0749364340");
//        request.setFirstName("Loredana");
//        request.setSecondName("Netan");
//
//
//
//        contactRepository.createContact(request);


////
//        UpdateContactRequest request = new UpdateContactRequest();
//        request.setFirstName("Bogdan");
//        contactRepository.selectContact(8, request);

//        contactRepository.deleteContact(2);

        List<Contact> contacts = contactRepository.getContact();
        System.out.println(contacts);





















    }}

