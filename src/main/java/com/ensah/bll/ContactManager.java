package com.ensah.bll;
import com.ensah.bo.Contact;
import com.ensah.data.*;
import com.ensah.gui.Contact_test;

import javax.swing.*;
import java.util.List;

public class ContactManager {
    private static ContactDao contactDao=new ContactDao();

    public static void createContact(Contact contact) throws Exception {
        String regex="[a-zA-Z]+";
        String regex1 = "^(?:\\+212|0)[5-7]\\d{8}$";
        String regex2 = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]\\.[a-zA-Z]{2,}$";
        String regex3="^[a-zA-Z]\\.[a-zA-Z]+@etu\\.uae\\.ac\\.ma$";

        if (contact.getNom().equals("") || contact.getPrenom().equals("") || contact.getEmail_professionnel().equals("") || contact.getEmail_personnel().equals("") || contact.getTelephone1().equals("") || contact.getTelephone2().equals("") || contact.getGenre().equals("")) {
            JOptionPane.showMessageDialog(null, "ERROR : Please enter all the fields", "Error", JOptionPane.ERROR_MESSAGE);}

        else if (!contact.getNom().matches("[a-zA-Z]+") || !contact.getPrenom().matches("[a-zA-Z]+")) {
            JOptionPane.showMessageDialog(null, "ERROR : nom ou prenom invalid ", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else if (!contact.getTelephone1().matches(regex1)){
            JOptionPane.showMessageDialog(null,
                    "Telephone1 invalid", "Error", JOptionPane.ERROR_MESSAGE);
        }

        else if (!contact.getTelephone2().matches(regex1)) {
            JOptionPane.showMessageDialog(null,
                    " Telephone2 invalid", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else if (!contact.getEmail_personnel().matches(regex2)) {
            JOptionPane.showMessageDialog(null,
                    " Telephone2 invalid", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else if (!contact.getEmail_professionnel().matches(regex3)) {
            JOptionPane.showMessageDialog(null,
                    " Telephone2 invalid", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            ContactDao.create(contact);
        };
    }

    public static void updateContact(Contact contact)throws Exception{
        String regex="[a-zA-Z]+";
        String regex1 = "^(?:\\+212|0)[5-7]\\d{8}$";
        String regex2 = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]\\.[a-zA-Z]{2,}$";
        String regex3="^[a-zA-Z]\\.[a-zA-Z]+@etu\\.uae\\.ac\\.ma$";

        if (contact.getNom().equals("") || contact.getPrenom().equals("") || contact.getEmail_professionnel().equals("") || contact.getEmail_personnel().equals("") || contact.getTelephone1().equals("") || contact.getTelephone2().equals("") || contact.getGenre().equals("")) {
            JOptionPane.showMessageDialog(null, "ERROR : Please enter all the fields", "Error", JOptionPane.ERROR_MESSAGE);}

        else if (!contact.getNom().matches("[a-zA-Z]+") || !contact.getPrenom().matches("[a-zA-Z]+")) {
            JOptionPane.showMessageDialog(null, "ERROR : nom ou prenom invalid ", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else if (!contact.getTelephone1().matches(regex1)){
            JOptionPane.showMessageDialog(null,
                    "Telephone1 invalid", "Error", JOptionPane.ERROR_MESSAGE);
        }

        else if (!contact.getTelephone2().matches(regex1)) {
            JOptionPane.showMessageDialog(null,
                    " Telephone2 invalid", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else if (!contact.getEmail_personnel().matches(regex2)) {
            JOptionPane.showMessageDialog(null,
                    " Telephone2 invalid", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else if (!contact.getEmail_professionnel().matches(regex3)) {
            JOptionPane.showMessageDialog(null,
                    " Telephone2 invalid", "Error", JOptionPane.ERROR_MESSAGE);
        }else { ContactDao.update(contact);
        };
    }


    public static List<Contact> searchContact(String pNom) throws Exception {
        List<Contact> contact = ContactDao.search(pNom);
        if (ContactDao.search(pNom)==null){
            JOptionPane.showMessageDialog(null,"ERROR contact doesnt exist");}

        else {;
                return contact;}

        return contact;
    }

    public static List<Contact> afficher() throws Exception {
        List<Contact> contact = ContactDao.afficher();
            return contact;}



    public static void deleteContact(int id)throws Exception{
            try {
                ContactDao.deleteContact(id);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }

        }










}
