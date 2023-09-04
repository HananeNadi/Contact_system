package com.ensah.data;
import com.ensah.bo.Contact;
import com.ensah.bo.Groupe;
import com.ensah.gui.Contact_test;


import org.apache.log4j.Logger;


import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ContactDao {
    private static Logger logger = Logger.getLogger(ContactDao.class);

    public static void create(Contact contact) throws Exception {

        int generatedId = -1;
        try {
            //Obtenir la connexion à la base de données
            Connection c = DBConnection.getInstance();

            //Création d'une instruction préparée
            PreparedStatement stm = c.prepareStatement("INSERT INTO Contact(nom, prenom, telephone1, telephone2, adresse, email_personnel, email_professionnel, genre)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            stm.setString(1, contact.getNom());
            stm.setString(2, contact.getPrenom());
            stm.setString(3, contact.getTelephone1());
            stm.setString(4, contact.getTelephone2());
            stm.setString(5, contact.getAdresse());
            stm.setString(6, contact.getEmail_personnel());
            stm.setString(7, contact.getEmail_professionnel());
            stm.setString(8, contact.getGenre());
            JOptionPane.showMessageDialog(null, "contact added");


            stm.executeUpdate();
            ResultSet generatedKeys = stm.getGeneratedKeys();
            if (generatedKeys.next()) {
                generatedId = generatedKeys.getInt(1);
                contact.setId(generatedId);
            }

        } catch (SQLException ex) {
            //tracer l'erreur
            logger.error("Erreur à cause de : ", ex);
            //remonter l'erreur
            throw new DataBaseException(ex);
        }

        // Close the PreparedStatement and Connection
        //stm.close();
        //c.close();
    }


    public static List<Contact> search(String nom) throws Exception {
        List<Contact> list = new ArrayList<>();
        try {
            //Obtenir la connexion à la base de données
            Connection c = DBConnection.getInstance();

            //Création d'une instruction préparée
            PreparedStatement stm = c.prepareStatement("SELECT * FROM Contact Where upper(nom)=? or upper(prenom) = ?or telephone1 = ? or telephone2= ? ");
            stm.setString(1, nom.toUpperCase());
            stm.setString(2, nom.toUpperCase());
            stm.setString(3, nom);
            stm.setString(4, nom);

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                list.add(new Contact((rs.getInt("id_contact")),rs.getString("nom"), rs.getString("prenom"), rs.getString("telephone1"), rs.getString("telephone2"), rs.getString("adresse"), rs.getString("email_personnel"), rs.getString("email_professionnel"), rs.getString("genre")));
            }
            Contact_test.tableModel.setRowCount(0);
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
                Contact_test.tableModel.addRow(rowData);
            }
            Contact_test.tableModel.fireTableDataChanged();

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


    }

    public static List<Contact> afficher() throws Exception {
        List<Contact> list = new ArrayList<>();
        Connection c = DBConnection.getInstance();
        PreparedStatement stm = c.prepareStatement("SELECT * FROM Contact ORDER BY nom");
        ResultSet rs = stm.executeQuery();

        while (rs.next()) {
            list.add(new Contact(
                    rs.getInt("id_contact"),
                    rs.getString("Nom"),
                    rs.getString("prenom"),
                    rs.getString("telephone1"),
                    rs.getString("telephone2"),
                    rs.getString("adresse"),
                    rs.getString("email_personnel"),
                    rs.getString("email_professionnel"),
                    rs.getString("genre")
            ));
            Contact_test.tableModel.setRowCount(0);
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
                Contact_test.tableModel.addRow(rowData);
            }

            // Refresh the table to reflect the updated data
            Contact_test.tableModel.fireTableDataChanged();

        }
        return list;
    }


    public static void update(Contact contact) throws Exception {
        try {
            Connection c = DBConnection.getInstance();
            PreparedStatement stm = c.prepareStatement("UPDATE Contact set nom=?, prenom=?, telephone1=?,telephone2=?,adresse=?,email_personnel=?,email_professionnel=?,genre=? where nom=?");

            stm.setString(1, contact.getNom());
            stm.setString(2, contact.getPrenom());
            stm.setString(3, contact.getTelephone1());
            stm.setString(4, contact.getTelephone2());
            stm.setString(5, contact.getAdresse());
            stm.setString(6, contact.getEmail_personnel());
            stm.setString(7, contact.getEmail_professionnel());
            stm.setString(8, contact.getGenre());
            stm.setString(9, contact.getNom());
            stm.executeUpdate();

            JOptionPane.showMessageDialog(null, "contact updated");

        } catch (SQLException ex) {
            //tracer l'erreur
            logger.error("Erreur à cause de : ", ex);
            //remonter l'erreur
            throw new DataBaseException(ex);
        }

    }

    public static void deleteContact(int id) throws Exception {
        try {
            Connection c = DBConnection.getInstance();
            PreparedStatement stm = c.prepareStatement("DELETE from contact where id_contact=?");

            stm.setInt(1, id);
            stm.executeUpdate();
            JOptionPane.showMessageDialog(null, "Contact deleted");
        } catch (SQLException ex) {
            //tracer l'erreur
            logger.error("Erreur à cause de : ", ex);
            //remonter l'erreur
            throw new DataBaseException(ex);
        }
    }
    // Close the PreparedStatement and Connection
        //stm.close();
        //c.close();

}







