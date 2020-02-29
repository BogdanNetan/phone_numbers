package org.fasttrackit.transfer;

public class GetContactRequest {
    private String firstName;



    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    @Override
    public String toString() {
        return "FilterContactRequest{" +
                "firstName='" + firstName + '\'' +
                '}';
    }
}
