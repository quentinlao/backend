/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daoebenus.ebenus.dao.entities;

/**
 *
 * @author Quentin
 */
public class Adresse {

    private static final long serialVersionUID = 1L;
    private Integer idAdresse;
    private Utilisateur utilisateur;
    private String rue;
    private String codePostal;
    private String ville;
    private String pays;
    private String status = "P";
    private String typeAdresse = "L";
    private byte principale = 0;
    private Integer version = 1;

    public Adresse() {
        
    }

    public Adresse(Integer idAdresse, Utilisateur utilisateur, String rue, String codePostal, String ville, String pays) {
        this.idAdresse = idAdresse;
        this.utilisateur = utilisateur;
        this.rue = rue;
        this.codePostal = codePostal;
        this.ville = ville;
        this.pays = pays;
    }

    public Adresse(Utilisateur utilisateur, String rue, String codePostal, String ville, String pays) {
        this.utilisateur = utilisateur;
        this.rue = rue;
        this.codePostal = codePostal;
        this.ville = ville;
        this.pays = pays;
    }

    public Integer getIdAdresse() {
        return idAdresse;
    }

    public void setIdAdresse(Integer idAdresse) {
        this.idAdresse = idAdresse;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }    

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTypeAdresse() {
        return typeAdresse;
    }

    public void setTypeAdresse(String typeAdresse) {
        this.typeAdresse = typeAdresse;
    }

    public byte getPrincipale() {
        return principale;
    }

    public void setPrincipale(byte principale) {
        this.principale = principale;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.idAdresse;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Adresse other = (Adresse) obj;
        if (this.idAdresse != other.idAdresse) {
            return false;
        }
        return true;

    }

    @Override
    public String toString() {
        return "Adresse{" + "idAdresse=" + idAdresse + ", utilisateur=" + utilisateur +
                ", rue=" + rue + ", codePostal=" + codePostal + ", ville=" + ville +
                ", pays=" + pays + ", status=" + status + ", typeAdresse=" + typeAdresse +
                ", principale=" + principale + ", version=" + version + '}';
    }

    
}
