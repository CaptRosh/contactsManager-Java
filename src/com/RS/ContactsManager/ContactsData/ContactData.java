package com.RS.ContactsManager.ContactsData;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

public class ContactData {
    public static String filename = "ContactsManager.txt";
    public static ObservableList<Contact> contacts;

    public static ObservableList<Contact> getContacts() {
        return contacts;
    }

    public static void addContact(Contact item){
        contacts.add(item);
    }

    public static void deleteContact(Contact item){
        contacts.remove(item);
    }

    public void loadContacts() throws IOException {
        contacts = FXCollections.observableArrayList();
        Path path = Paths.get(filename);
        BufferedReader br = Files.newBufferedReader(path);

        String input;
        try{
            while((input=br.readLine())!=null){
                String[] split = input.split(",");
                Contact item = new Contact(split[0],split[1],split[2]);
                contacts.add(item);
            }
        } finally {
            br.close();
        }
    }

    public void saveContacts() throws IOException {
        Path path = Paths.get(filename);
        BufferedWriter bw = Files.newBufferedWriter(path);
        try{
            Iterator<Contact> iter = contacts.iterator();
            while (iter.hasNext()){
                Contact item = iter.next();
                bw.write(String.format("%s,%s,%s",item.getFirstName(),item.getLastName(),item.getPhoneNumber()));
                bw.write("\n");
            }
        } finally {
            bw.close();
        }
    }
}
