package com.ensah.bo;


public class Groupe {
    private int id;

    private String nom;

    public Groupe(String nom) {
        this.nom = nom;
    }

    public Groupe(int id, String nom) {
        this.nom = nom;
        this.id = id;
    }

    public String toString() {
        return "Groupe (id=" + id + ", nom=" + nom + ")";
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
}
