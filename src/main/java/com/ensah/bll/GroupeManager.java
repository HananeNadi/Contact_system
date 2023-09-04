package com.ensah.bll;

import com.ensah.bo.Contact;
import com.ensah.bo.Groupe;
import com.ensah.data.ContactDao;
import com.ensah.data.GroupeDao;

import javax.swing.*;
import java.util.List;

public class GroupeManager {
    private static GroupeDao groupeDao=new GroupeDao();

    public static void createGroup(Groupe groupe) throws Exception {
        if (groupe.getNom().equals("")){
                JOptionPane.showMessageDialog(null, "ERROR : Please enter the name's field", "Error", JOptionPane.ERROR_MESSAGE);}
        else { groupeDao.create(groupe);
        };
    }


    public static List<Groupe> afficher() throws Exception {
        List<Groupe> group = GroupeDao.afficher();
        if (GroupeDao.afficher()==null){
            JOptionPane.showMessageDialog(null,"ERROR Group doesnt exist");}
        else {
            return group;}

        return group;
    }



    public static void AddContactGroup(int id_c,int id_g) throws Exception {
        groupeDao.AddContact(id_c,id_g);
    }

    public static List<Contact> DisplayContacts(int id)throws Exception{
        List<Contact> group= GroupeDao.displayContact(id);
        return group;
    }

    public static void updateGroup(Groupe group)throws Exception{
        if (group.getNom().equals("")){
            JOptionPane.showMessageDialog(null, "ERROR EMPTY FIELD");
        }
        else { GroupeDao.update(group);
        };
    }

    public static List<Groupe> searchGroupe(String pNom) throws Exception {
        List<Groupe> group = GroupeDao.search(pNom);
        if (GroupeDao.search(pNom)==null){
            JOptionPane.showMessageDialog(null,"ERROR Group doesnt exist");}

        else {;
            return group;}

        return group;
    }

    public static void deletegroup(int id)throws Exception{
        try {
            GroupeDao.deletegroupe(id);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }
}
