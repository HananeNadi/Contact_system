package com.ensah.gui;

import com.ensah.bll.ContactManager;
import com.ensah.bll.GroupeManager;
import com.ensah.bo.Groupe;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class Group_test {
    JPanel groupPanel;
    private JPanel panel2;
    private JPanel buttonpan;
    private JButton creerButton;
    private JButton updateButton1;
    private JButton afficherButton;
    private JButton supprimerButton;
    private JTextField Rechercher;
    private JButton rechercherButton;
    private JScrollPane sp;
    private JTable table_group;
    private JButton backButton;
    private JTextField nomtextField1;
    private JTable table1;
    private JPanel panel_grp;
    private JButton AddContactButton;

    private int id;
    public static DefaultTableModel tableModel_grp,tableModel_contact;

    public Group_test() throws Exception {
        table_grp();
        table_contact();
        GroupeManager.afficher();


        creerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String name= nomtextField1.getText();
                nomtextField1.setText("");
                nomtextField1.requestFocus();

                        try {
                            GroupeManager.createGroup(new Groupe(name));
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }


            }
        });
        afficherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{

                    GroupeManager.afficher();

                }catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        supprimerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


            }
        });
        AddContactButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id_C=JOptionPane.showInputDialog("Please entrer the Contact's ID");
                int id_Contact = Integer.parseInt(id_C);

                String id_g=JOptionPane.showInputDialog("Please entrer the Group's ID");
                int id_groupe = Integer.parseInt(id_g);

                try {
                    GroupeManager.AddContactGroup(id_Contact, id_groupe);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }


            }
        });

        updateButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nomtextField1.getText();
                nomtextField1.setText("");
                nomtextField1.requestFocus();
                try {
                    GroupeManager.updateGroup(new Groupe(name));
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

                            GroupeManager.searchGroupe(name);
                        }catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
            }
        });
        supprimerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (id != -1) {
                    try {
                        GroupeManager.deletegroup(id);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No row selected");
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Contact manager");
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

    private void table_grp() {
        tableModel_grp = new DefaultTableModel(
                new Object[][]{},
                new String[]{"id","nom"});

        JTable table_group = new JTable(tableModel_grp);
        //table_record.setEnabled(false);
        JScrollPane sp = new JScrollPane(table_group);
        panel_grp.setLayout(new BorderLayout());
        panel_grp.add(sp, BorderLayout.CENTER);
        table_group.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table_group.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedRow = table_group.getSelectedRow();
                if (selectedRow != -1) {
                    id =(int)table_group.getValueAt(selectedRow, 0);
                    try {
                        GroupeManager.DisplayContacts(id);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }

                } else {
                    System.out.println("no rows");
                }
            }
        });
    }

    private void table_contact() {
        tableModel_contact = new DefaultTableModel(
                new Object[][]{},
                new String[]{"id","nom", "prenom", "telephone1", "telephone2","adresse ","email_personnel","email_professionnel", " genre"});

        JTable table1 = new JTable(tableModel_contact);
        //table_record.setEnabled(false);
        JScrollPane sp = new JScrollPane(table1);
        panel2.setLayout(new BorderLayout());
        panel2.add(sp, BorderLayout.CENTER);
        table_group.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table_group.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedRow = table_group.getSelectedRow();
                if (selectedRow != -1) {
                    id = (int) table_group.getValueAt(selectedRow, 0);
                } else {
                    System.out.println("no rows");
                }
            }
        });
    }






    public static void main(String[] args) throws Exception {
        JFrame frame = new JFrame("Group_test");
        frame.setContentPane(new Group_test().groupPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
