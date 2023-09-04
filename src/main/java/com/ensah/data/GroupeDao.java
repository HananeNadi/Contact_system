package com.ensah.data;
import com.ensah.bo.Contact;
import com.ensah.bo.Groupe;
import com.ensah.gui.Contact_test;
import com.ensah.gui.Group_test;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GroupeDao {
    private static Logger logger = Logger.getLogger(GroupeDao.class);

    public void create(Groupe groupe) throws Exception {

        int generatedId = -1;
        try {
            // Obtenir la connexion � la base de donn�es
            Connection c = DBConnection.getInstance();
            // Cr�ation d'une instruction pr�par�e
            PreparedStatement stm = c.prepareStatement("INSERT INTO groupe(nom)VALUES (?)", Statement.RETURN_GENERATED_KEYS);

            stm.setString(1, groupe.getNom());
            stm.executeUpdate();
            JOptionPane.showMessageDialog(null, "Group created");
            ResultSet generatedKeys = stm.getGeneratedKeys();
            if (generatedKeys.next()) {
                generatedId = generatedKeys.getInt(1);
                groupe.setId(generatedId);
            }
        } catch (SQLException ex) {
            //tracer l'erreur
            logger.error("Error due to : ", ex);
            //remonter l'erreur
            throw new DataBaseException(ex);

        }


    }


    public static List<Groupe> afficher() throws Exception {
        List<Groupe> list = new ArrayList<>();
        Connection c = DBConnection.getInstance();
        PreparedStatement stm = c.prepareStatement("SELECT * FROM groupe ORDER BY nom");
        ResultSet rs = stm.executeQuery();

        while (rs.next()) {
            list.add(new Groupe(
                    rs.getInt("id"),
                    rs.getString("nom")
            ));
        }

        Group_test.tableModel_grp.setRowCount(0);

        for (Groupe groupe : list) {
            Object[] rowData = {
                    groupe.getId(),
                    groupe.getNom()
            };
            Group_test.tableModel_grp.addRow(rowData);
        }

        // Refresh the table to reflect the updated data
        Group_test.tableModel_grp.fireTableDataChanged();

        return list;
    }


    public void AddContact(int id_c, int id_g) throws Exception {

        try {
            // Obtenir la connexion � la base de donn�es
            Connection c = DBConnection.getInstance();
            // Cr�ation d'une instruction pr�par�e
            PreparedStatement stm = c.prepareStatement("INSERT INTO grp_contact(id_contact,id_groupe)VALUES (?,?)");

            stm.setInt(1, id_c);
            stm.setInt(2, id_g);

            stm.executeUpdate();

        } catch (SQLException ex) {
            //tracer l'erreur
            logger.error("Erreur à cause de : ", ex);
            //remonter l'erreur
            throw new DataBaseException(ex);
        }

    }


    public static List<Contact> displayContact(int id) throws DataBaseException {
        List<Contact> list = new ArrayList<>();
        try {
            Connection c = DBConnection.getInstance();
            PreparedStatement stm = c.prepareStatement("SELECT id_contact FROM grp_contact WHERE id_groupe = ?");
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                String contactId = rs.getString("id_contact");
                PreparedStatement stm1 = c.prepareStatement("SELECT * FROM contact WHERE id_contact = ?");
                stm1.setString(1, contactId);
                ResultSet rs1 = stm1.executeQuery();

                while (rs1.next()) {
                    list.add(new Contact(
                            rs1.getInt("id_contact"),
                            rs1.getString("Nom"),
                            rs1.getString("prenom"),
                            rs1.getString("telephone1"),
                            rs1.getString("telephone2"),
                            rs1.getString("adresse"),
                            rs1.getString("email_personnel"),
                            rs1.getString("email_professionnel"),
                            rs1.getString("genre")
                    ));
                }
            }

            Group_test.tableModel_contact.setRowCount(0);
            for (Contact contact : list) {
                Object[] rowData = {
                        contact.getId(),
                        contact.getNom(),
                        contact.getPrenom(),
                        contact.getTelephone1(),
                        contact.getTelephone2(),
                        contact.getAdresse(),
                        contact.getEmail_professionnel(),
                        contact.getEmail_personnel(),
                        contact.getGenre()
                };
                Group_test.tableModel_contact.addRow(rowData);
            }

            Group_test.tableModel_contact.fireTableDataChanged();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public static void update(Groupe groupe) throws Exception {
        try {
            Connection c = DBConnection.getInstance();
            PreparedStatement stm = c.prepareStatement("UPDATE groupe set nom=? where nom=?");

            stm.setString(1, groupe.getNom());
            stm.executeUpdate();

            JOptionPane.showMessageDialog(null, "Group updated");

        } catch (SQLException ex) {
            //tracer l'erreur
            logger.error("Error due to : ", ex);
            //remonter l'erreur
            throw new DataBaseException(ex);
        }


    }

    public static List<Groupe> search(String nom) throws Exception {
        List<Groupe> list = new ArrayList<>();
        try {
            //Obtenir la connexion à la base de données
            Connection c = DBConnection.getInstance();
            //Création d'une instruction préparée
            PreparedStatement stm = c.prepareStatement("SELECT * FROM groupe Where upper(nom)=? ");
            stm.setString(1, nom.toUpperCase());


            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                list.add(new Groupe(rs.getInt("ID"),rs.getString("Nom")));
            }
            Group_test.tableModel_grp.setRowCount(0);
            for (Groupe contact : list) {
                Object[] rowData = {
                        contact.getId(),
                        contact.getNom(),
                };
                Group_test.tableModel_grp.addRow(rowData);
            }
            Group_test.tableModel_grp.fireTableDataChanged();

            rs.close();
        } catch (SQLException ex) {
            //tracer l'erreur
            logger.error("Erreur à cause de : ", ex);
            //remonter l'erreur
            throw new DataBaseException(ex);
        }
        if (list.isEmpty()) {
            return null;
        }

        return list;


    }public static void deletegroupe(int id) throws Exception {
        try {
            Connection c = DBConnection.getInstance();
            PreparedStatement stm = c.prepareStatement("DELETE from groupe where ID=?");

            stm.setInt(1, id);
            stm.executeUpdate();
            JOptionPane.showMessageDialog(null, "Groupe deleted");
        } catch (SQLException ex) {
            //tracer l'erreur
            logger.error("Erreur à cause de : ", ex);
            //remonter l'erreur
            throw new DataBaseException(ex);
        }
    }}

