package com.RS.ContactsManager.ContactsData;

import javafx.beans.property.SimpleStringProperty;

public class Contact {
    private SimpleStringProperty firstName = new SimpleStringProperty("");
    private SimpleStringProperty lastName=  new SimpleStringProperty("");
    private SimpleStringProperty phoneNumber=  new SimpleStringProperty("");



    public Contact() {
    }


    public Contact(String firstName, String lastName, String phoneNumber) {
        this.firstName.set(firstName);
        this.lastName.set(lastName);
        this.phoneNumber.set(phoneNumber);
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.set(phoneNumber);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public SimpleStringProperty firstNameProperty() {
        return firstName;
    }

    public String getLastName() {
        return lastName.get();
    }

    public SimpleStringProperty lastNameProperty() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public SimpleStringProperty phoneNumberProperty() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "firstName= " + firstName +
                "lastName= " + lastName +
                "phoneNumber= " + phoneNumber + "}";

    }
}
