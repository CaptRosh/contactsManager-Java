package com.RS.ContactsManager;

import com.RS.ContactsManager.ContactsData.Contact;
import com.RS.ContactsManager.ContactsData.ContactData;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.Optional;

public class MainWindowController {

    private ContactData data;

    @FXML
    private TableView<Contact> contactsTableView;

    @FXML
    private BorderPane mainWindow;

    @FXML
    private TableColumn<Contact,String> firstNameColumn;

    @FXML
    private TableColumn<Contact,String> lastNameColumn;

    @FXML
    private TableColumn<Contact,String> phoneNumberColumn;


    public void initialize() {

        data = new ContactData();
        try{
            data.loadContacts();
        } catch (IOException e){
            System.out.println(e);
        }
        contactsTableView.setItems(data.getContacts());
        firstNameColumn.setCellValueFactory(data -> data.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(data -> data.getValue().lastNameProperty());
        phoneNumberColumn.setCellValueFactory(data -> data.getValue().phoneNumberProperty());

        }

    public void newContact(){
        Dialog<ButtonType> dialogue = new Dialog();
        dialogue.initOwner(mainWindow.getScene().getWindow());
        dialogue.setTitle("Add new Contact");
        dialogue.setHeaderText("Create a new Contact");

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("NewContact.fxml"));
        try{
            dialogue.getDialogPane().setContent(fxmlLoader.load());

        } catch (IOException e){
            System.out.print("Couldn't load the dialog");
            e.printStackTrace();
            return;
        }

        dialogue.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialogue.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> pressed = dialogue.showAndWait();
        if(pressed.isPresent() && pressed.get() == ButtonType.OK){
            NewContactController ok = fxmlLoader.getController();
            Contact item = ok.process();
            data.addContact(item);
            contactsTableView.getSelectionModel().select(item);
            try{
                data.saveContacts();
            } catch (IOException e){
                System.out.println(e.getMessage());
            }
            contactsTableView.setItems(data.getContacts());
        }
    }

    public void exit(){
        Platform.exit();
    }

    public void editContact() {
        Contact selectedItem = contactsTableView.getSelectionModel().getSelectedItem();

        if(selectedItem == null){
            notSelected();
        }
        Dialog editContact = new Dialog();
        editContact.initOwner(mainWindow.getScene().getWindow());
        editContact.setTitle("Edit Contact");
        editContact.setHeaderText("Edit this Contact");

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("NewContact.fxml"));
        try{
            editContact.getDialogPane().setContent(fxmlLoader.load());

        } catch (IOException e){
            System.out.print("Couldn't load the dialog");
            e.printStackTrace();
            return;
        }

        editContact.getDialogPane().getButtonTypes().add(ButtonType.OK);
        editContact.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        NewContactController controller = fxmlLoader.getController();
        controller.editContact(selectedItem);

        Optional<ButtonType> result = editContact.showAndWait();
        if(result.isPresent() && result.get()==ButtonType.OK){
            controller.updateContact(selectedItem);
            try{
                data.saveContacts();
            } catch (IOException e){
                System.out.println(e.getMessage());
            }
            contactsTableView.setItems(data.getContacts());
        }
    }
    public void notSelected(){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No Contact Selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select a contact.");
            alert.showAndWait();
    }

    public void deleteContact(){
        Contact selectedItem = contactsTableView.getSelectionModel().getSelectedItem();
        if(selectedItem == null){
            notSelected();
        }

        data.deleteContact(selectedItem);
    }
}
