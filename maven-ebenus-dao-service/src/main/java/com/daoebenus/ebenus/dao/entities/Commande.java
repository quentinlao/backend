/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daoebenus.ebenus.dao.entities;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Quentin
 */
public class Commande {
    private static final long serialVersionUID = 1L;
    private Integer idCommande;
    private double totalCommande;
    private Utilisateur utilisateur;
    private Adresse adresse;
    private String statut;
    private Date dateCommande;
    private Date dateModification;
    private Integer version = 0;

    
    public Commande(){
    }

    public Commande(Integer idCommande, double totalCommande, Utilisateur utilisateur, Adresse adresse, String statut, Date dateCommande, Date dateModification, Integer version) {
        this.idCommande = idCommande;
        this.totalCommande = totalCommande;
        this.utilisateur = utilisateur;
        this.adresse = adresse;
        this.statut = statut;
        this.dateCommande = dateCommande;
        this.dateModification = dateModification;
        this.version = version;
    }

    public Commande(double totalCommande, Utilisateur utilisateur, Adresse adresse, String statut, Date dateCommande, Date dateModification, Integer version) {
        this.totalCommande = totalCommande;
        this.utilisateur = utilisateur;
        this.adresse = adresse;
        this.statut = statut;
        this.dateCommande = dateCommande;
        this.dateModification = dateModification;
        this.version = version;
    }

    public Commande(double totalCommande, Utilisateur utilisateur, Adresse adresse, String statut, Date dateCommande) {
        this.totalCommande = totalCommande;
        this.utilisateur = utilisateur;
        this.adresse = adresse;
        this.statut = statut;
        this.dateCommande = dateCommande;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getIdCommande() {
        return idCommande;
    }

    public double getTotalCommande() {
        return totalCommande;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public String getStatut() {
        return statut;
    }

    public Date getDateCommande() {
        return dateCommande;
    }

    public Date getDateModification() {
        return dateModification;
    }

    public Integer getVersion() {
        return version;
    }

    public void setIdCommande(Integer idCommande) {
        this.idCommande = idCommande;
    }

    public void setTotalCommande(double totalCommande) {
        this.totalCommande = totalCommande;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public void setDateCommande(Date dateCommande) {
        this.dateCommande = dateCommande;
    }

    public void setDateModification(Date dateModification) {
        this.dateModification = dateModification;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.idCommande);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Commande other = (Commande) obj;
        if (!Objects.equals(this.idCommande, other.idCommande)) {
            return false;
        }
        return true;
    }


    @Override
    public String toString() {
        return "Commande{" +
                "idCommande=" + idCommande +
                ", totalCommande=" + totalCommande +
                ", utilisateur=" + utilisateur +
                ", adresse=" + adresse +
                ", statut='" + statut + '\'' +
                ", dateCommande=" + dateCommande +
                ", dateModification=" + dateModification +
                ", version=" + version +
                '}';
    }
}
