package com.ensah.gui;


import com.ensah.bll.ContactManager;
import com.ensah.bo.Contact;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Contact_test {
    private JFrame frame;
    public  JTable table_record;
    private JButton creerButton;
    private JButton updateButton1;
    private JButton afficherButton;
    private JButton supprimerButton;
    private JButton rechercherButton;




    private JTextField nomtextfield;
    private JTextField prenomtextfield;
    private JTextField tele1textfield;
    private JTextField tele2textField;
    private JTextField adresstextField;
    private JTextField emailPersotextField;
    private JTextField emailprofetextField;
    private JRadioButton hommeRadioButton;
    private JRadioButton femmeRadioButton;
    JPanel contactPanel;
    private JScrollPane sp;
    private JPanel panel2;
    private JPanel buttonpan;
    private JTextField Rechercher;
    private JButton backButton;
    public static DefaultTableModel tableModel;
    private  int selectedRow,id;


    public Contact_test() throws Exception {
        table();
        ContactManager.afficher();
        JButton[] buttons = {creerButton, updateButton1, afficherButton,supprimerButton,rechercherButton};
        for (JButton button : buttons) {
            button.setFocusPainted(false);
        }



        creerButton.setFocusPainted(false);
        ButtonGroup radiogrp = new ButtonGroup();
        radiogrp.add(hommeRadioButton);
        radiogrp.add(femmeRadioButton);

        creerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name=nomtextfield.getText();
                String prenom=prenomtextfield.getText();
                String adresse=tele1textfield.getText();
                String tele1=tele2textField.getText();
                String tele2=adresstextField.getText();
                String emailperso=emailPersotextField.getText();
                String emailprofe=emailprofetextField.getText();
                String genre=hommeRadioButton.isSelected() ? "Homme" : "Femme";

                nomtextfield.setText("");
                prenomtextfield.setText("");
                tele1textfield.setText("");
                tele2textField.setText("");
                adresstextField.setText("");
                emailPersotextField.setText("");
                emailprofetextField.setText("");
                radiogrp.clearSelection();
                nomtextfield.requestFocus();

                try {
                    ContactManager.createContact(new Contact(name,prenom,adresse,tele1,tele2,emailperso,emailprofe,genre));
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }



            }
        });

        rechercherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String name= Rechercher.getText();
                Rechercher.setText("");
                Rechercher.requestFocus();
                try{
                    ContactManager.searchContact(name);
                }catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        updateButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nomtextfield.getText();
                String prenom = prenomtextfield.getText();
                String adresse = tele1textfield.getText();
                String tele1 = tele2textField.getText();
                String tele2 = adresstextField.getText();
                String emailperso = emailPersotextField.getText();
                String emailprofe = emailprofetextField.getText();
                String genre = hommeRadioButton.isSelected() ? "Homme" : "Femme";

                nomtextfield.setText("");
                prenomtextfield.setText("");
                tele1textfield.setText("");
                tele2textField.setText("");
                adresstextField.setText("");
                emailPersotextField.setText("");
                emailprofetextField.setText("");
                radiogrp.clearSelection();
                nomtextfield.requestFocus();

                try {
                    ContactManager.updateContact(new Contact(name, prenom, adresse, tele1, tele2, emailperso, emailprofe, genre));
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
            });




        supprimerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (id != -1) {
                    try {
                        ContactManager.deleteContact(id);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No row selected");
                }

            }
        });


        afficherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    ContactManager.afficher();
                }catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });



        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame = new JFrame("Contact manager");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add(new Acceuil().panel1);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(backButton);
                currentFrame.dispose();

            }
        });


    }
    public void table() {
        tableModel = new DefaultTableModel(
                new Object[][]{},
                new String[]{"id","nom", "prenom", "telephone1", "telephone2","adresse ","email_personnel","email_professionnel", " genre"});

        JTable table_record = new JTable(tableModel);
        //table_record.setEnabled(false);
        JScrollPane sp = new JScrollPane(table_record);
        panel2.setLayout(new BorderLayout());
        panel2.add(sp, BorderLayout.CENTER);
        table_record.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table_record.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedRow = table_record.getSelectedRow();
                if (selectedRow != -1) {
                    id = (int) table_record.getValueAt(selectedRow, 0);
                } else {
                    System.out.println("no rows");
                }
            }
        });


            }



    public static void main(String[] args) throws Exception {
        JFrame frame = new JFrame("Contact_test");
        frame.setContentPane(new Contact_test().contactPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}



