package org.fasttrackit.web;

import org.fasttrackit.config.ObjectMapperConfiguration;
import org.fasttrackit.domain.Contact;
import org.fasttrackit.service.ContactService;
import org.fasttrackit.transfer.CreateContactRequest;
import org.fasttrackit.transfer.GetContactRequest;
import org.fasttrackit.transfer.UpdateContactRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/contacts")
public class ContactServlet extends HttpServlet {


    private ContactService contactService = new ContactService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setAccesControlHeaders(resp);
        String id = req.getParameter("id");

        CreateContactRequest request = ObjectMapperConfiguration.objectMapper.
                readValue( req.getReader(), CreateContactRequest.class);

        try {
            contactService.createContact(request);
        } catch (SQLException | ClassNotFoundException e) {
            resp.sendError(500, "Internal server error: " + e.getMessage());
        }

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setAccesControlHeaders(resp);

        String id = req.getParameter("id");

        try {
            contactService.deleteContact(Long.parseLong(id));
        } catch (SQLException | ClassNotFoundException e) {
            resp.sendError(500, "Internal server error: " + e.getMessage());

        }

    }
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setAccesControlHeaders(resp);

        String id = req.getParameter("id");

        UpdateContactRequest request = ObjectMapperConfiguration.objectMapper.
                readValue(req.getReader(), UpdateContactRequest.class);

        try {
            contactService.updateContact(Long.parseLong(id), request);
        } catch (SQLException | ClassNotFoundException e) {
            resp.sendError(500, "Internal server error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setAccesControlHeaders(resp);

        String first_name = req.getParameter("first_name");


        if (first_name != null) {
            try {
                CreateContactRequest request = new CreateContactRequest();
                request.setFirstName(first_name);
                ObjectMapperConfiguration.objectMapper.writeValue(resp.getWriter(),
                        contactService.getContactByFirstName(request));
            } catch (SQLException | ClassNotFoundException e) {
                resp.sendError(500, "Internal server error: " + e.getMessage());
            }
        } else {
            try {
            List<Contact> contacts = contactService.getContacts();

            String response = ObjectMapperConfiguration.objectMapper.writeValueAsString(contacts);

            resp.getWriter().print(response);

        } catch (SQLException | ClassNotFoundException e) {
            resp.sendError(500, "Internal server error: " + e.getMessage());

        }
    }

    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setAccesControlHeaders(resp);
    }
    private void setAccesControlHeaders(HttpServletResponse resp) {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        resp.addHeader("Access-Control-Allow-Headers", "content-type");

    }
}






