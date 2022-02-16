/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daoebenus.ebenus.dao.entities;

import java.util.Date;

/**
 *
 * @author Quentin
 */
public class ArticleCommande {
    
    Integer idArticleCommande;
    
    Commande commande;
    
    Utilisateur utilisateur;
    
    Adresse adresse;
    
    Produit produit;
    
    double totalArticleCommande;
    
    String reference;
    
    Integer quantite;
    
    String status;
    
    Date dateModification;
    
    Integer version = 1;

    public ArticleCommande() {
    }

    public ArticleCommande(Integer idArticleCommande, Commande commande, Utilisateur utilisateur, Adresse adresse, Produit produit, double totalArticleCommande, String reference, Integer quantite, String status, Date dateModification) {
        this.idArticleCommande = idArticleCommande;
        this.commande = commande;
        this.utilisateur = utilisateur;
        this.adresse = adresse;
        this.produit = produit;
        this.totalArticleCommande = totalArticleCommande;
        this.reference = reference;
        this.quantite = quantite;
        this.status = status;
        this.dateModification = dateModification;
    }

    public ArticleCommande(Commande commande, Utilisateur utilisateur, Adresse adresse, Produit produit, double totalArticleCommande, String reference, Integer quantite, String status, Date dateModification) {
        this.commande = commande;
        this.utilisateur = utilisateur;
        this.adresse = adresse;
        this.produit = produit;
        this.totalArticleCommande = totalArticleCommande;
        this.reference = reference;
        this.quantite = quantite;
        this.status = status;
        this.dateModification = dateModification;
    }

    public Integer getIdArticleCommande() {
        return idArticleCommande;
    }

    public void setIdArticleCommande(Integer idArticleCommande) {
        this.idArticleCommande = idArticleCommande;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }    

    public double getTotalArticleCommande() {
        return totalArticleCommande;
    }

    public void setTotalArticleCommande(double totalArticleCommande) {
        this.totalArticleCommande = totalArticleCommande;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDateModification() {
        return dateModification;
    }

    public void setDateModification(Date dateModification) {
        this.dateModification = dateModification;
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
        hash = 13 * hash + this.idArticleCommande;
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
        final ArticleCommande other = (ArticleCommande) obj;
        if (this.idArticleCommande != other.idArticleCommande) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ArticleCommande{" + "idArticleCommande=" + idArticleCommande + 
                ", commande=" + commande + ", utilisateur=" + utilisateur +
                ", adresse=" + adresse + ", produit=" + produit + 
                ", totalArticleCommande=" + totalArticleCommande +
                ", reference=" + reference + ", quantite=" + quantite + 
                ", status=" + status + ", dateModification=" + dateModification +
                ", version=" + version + '}';
    }

   
    
    
}
