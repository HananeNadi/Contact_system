package com.ensah.bo;

public class Contact {

    private int id;

    private String nom;

    private String prenom;
    private String adresse;

    private String email_personnel;
    private String email_professionnel;

    private String telephone1;
    private String telephone2;
    private String genre;


    public Contact(String nom, String prenom,String telephone1, String telephone2,String adresse, String email_personnel, String email_professionnel, String genre) {
        this.nom = nom;
        this.prenom = prenom;
        this.telephone1 = telephone1;
        this.telephone2 = telephone2;
        this.adresse=adresse;
        this.email_personnel = email_personnel;
        this.email_professionnel = email_professionnel;
        this.genre = genre;
    }

    public Contact(int id, String nom, String prenom,String telephone1, String telephone2,String adresse, String email_personnel, String email_professionnel, String genre) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.telephone1 = telephone1;
        this.telephone2 = telephone2;
        this.adresse=adresse;
        this.email_personnel = email_personnel;
        this.email_professionnel = email_professionnel;
        this.genre = genre;
    }


    @Override
    public String toString() {
        return "Contact (id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", telephone1=" + telephone1 + ", telephone2=" + telephone2+ ", email personnel=" + email_personnel + ", email professionnel=" + email_professionnel + ", Genre=" + genre + ")";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEmail_personnel() {
        return email_personnel;
    }

    public void setEmail_personnel(String email_personnel) {
        this.email_personnel = email_personnel;
    }

    public String getEmail_professionnel() {
        return email_professionnel;
    }

    public void setEmail_professionnel(String email_professionnel) {
        this.email_professionnel = email_professionnel;
    }

    public String getTelephone1() {
        return telephone1;
    }

    public void setTelephone1(String telephone1) {
        this.telephone1 = telephone1;
    }

    public String getTelephone2() {
        return telephone2;
    }

    public void setTelephone2(String telephone2) {
        this.telephone2 = telephone2;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}