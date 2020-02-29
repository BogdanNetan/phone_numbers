package org.fasttrackit.persistance;

import com.mysql.cj.x.protobuf.MysqlxCrud;
import org.fasttrackit.domain.Contact;
import org.fasttrackit.transfer.CreateContactRequest;
import org.fasttrackit.transfer.GetContactRequest;
import org.fasttrackit.transfer.UpdateContactRequest;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactRepository {
    public void createContact(CreateContactRequest request) throws SQLException, IOException, ClassNotFoundException {

        String sql = "INSERT INTO phone_numbers(first_name, second_name,phone_number) VALUES(?,?,?)";

        try (Connection connection = DatabaseConfiguration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, request.getFirstName());
            preparedStatement.setString(2, request.getSecondName());
            preparedStatement.setString(3, request.getPhoneNumber());

            preparedStatement.executeUpdate();
        }

    }

    public void updateContact( long id, UpdateContactRequest request) throws SQLException, IOException,
            ClassNotFoundException {

        String sql = " UPDATE phone_numbers SET first_name=?, second_name=?, phone_number=? WHERE id =? ";

        try (Connection connection = DatabaseConfiguration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1,request.getFirstName());
            preparedStatement.setString(2,request.getSecondName());
            preparedStatement.setString(3,request.getPhoneNumber());
            preparedStatement.setLong(4, id);

            preparedStatement.executeUpdate();
}
    }



    public void deleteContact(long id) throws IOException, SQLException, ClassNotFoundException {
// PREVENTING SQL INJECTION by avoiding concatenation and using prepared statement
        String sql = "DELETE FROM phone_numbers WHERE id = ?";

        try (Connection connection = DatabaseConfiguration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
    }
}

                public List<Contact> getContact() throws SQLException, IOException, ClassNotFoundException {
                    String sql = "  SELECT  * FROM phone_numbers ";
                    try(Connection connection = DatabaseConfiguration.getConnection();
                        Statement statement = connection.createStatement();
                        ResultSet resultSet = statement.executeQuery(sql)) {


                        List<Contact> contacts = new ArrayList<>();
                        while (resultSet.next()) {
                            Contact contact = new Contact();
                            contact.setId(resultSet.getLong("id"));
                    contact.setFirstName(resultSet.getString("first_name"));
                    contact.setSecondName(resultSet.getString("second_name"));
                    contact.setPhoneNumber(resultSet.getString("phone_number"));

                    contacts.add(contact);
                }
                return contacts;
            }
        }


    public List<Contact> getContactByFirstName(CreateContactRequest request) throws SQLException, IOException, ClassNotFoundException {
        String sql = " SELECT first_name , second_name,phone_number FROM phone_numbers WHERE first_name LIKE? ";
        Connection connection = DatabaseConfiguration.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, request.getFirstName());
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Contact> singlecontact = new ArrayList<>();
        while (resultSet.next()) {
            Contact contact = new Contact();
            contact.setFirstName(resultSet.getString("first_name"));
            contact.setSecondName(resultSet.getString("second_name"));
            contact.setPhoneNumber(resultSet.getString("phone_number"));

            singlecontact.add(contact);
        }
        return singlecontact;
    }

    }








