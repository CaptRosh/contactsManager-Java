package com.RS.ContactsManager;

import com.RS.ContactsManager.ContactsData.Contact;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class NewContactController {

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField phoneNumberField;

    public Contact process(){
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String phoneNumber = phoneNumberField.getText();

        Contact item = new Contact(firstName,lastName,phoneNumber);
        return item;
    }

    public void editContact(Contact item){
        firstNameField.setText(item.getFirstName());
        lastNameField.setText(item.getLastName());
        phoneNumberField.setText(item.getPhoneNumber());
    }

    public void updateContact(Contact item){
        item.setFirstName(firstNameField.getText());
        item.setLastName(lastNameField.getText());
        item.setPhoneNumber(phoneNumberField.getText());
    }
}
