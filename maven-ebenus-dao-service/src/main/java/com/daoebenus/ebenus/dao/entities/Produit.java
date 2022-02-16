/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daoebenus.ebenus.dao.entities;

import java.util.Objects;

/**
 *
 * @author Quentin
 */
public class Produit {
    private static final long serialVersionUID = 1L;
    private Integer idProduit;
    private String reference;
    private Double prix;
    private String nom;
    private String description;
    private Integer stock;
    private byte active;
    private byte marquerEffacer = 0;
    private Integer version = 1;
    
    public Produit(Integer idProduit, String reference, Double prix, String nom, String description, Integer stock, byte active) {
        this.idProduit = idProduit;
        this.reference = reference;
        this.prix = prix;
        this.nom = nom;
        this.description = description;
        this.stock = stock;
        this.active = active;
    }

    public Produit(String reference, Double prix, String nom, String description, Integer stock, byte active) {
        this.reference = reference;
        this.prix = prix;
        this.nom = nom;
        this.description = description;
        this.stock = stock;
        this.active = active;
    }

    public Produit() {
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.idProduit);
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
        final Produit other = (Produit) obj;
        if (!Objects.equals(this.idProduit, other.idProduit)) {
            return false;
        }
        return true;
    }
    
    public Integer getIdProduit() {
        return idProduit;
    }

    @Override
    public String toString() {
        return "Produit{" + "idProduit=" + idProduit + ", reference=" + reference + ", prix=" + prix + ", nom=" + nom + ", description=" + description + ", stock=" + stock + ", active=" + active + ", marquerEffacer=" + marquerEffacer + ", version=" + version + '}';
    }

    public void setIdProduit(Integer idProduit) {
        this.idProduit = idProduit;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public byte getActive() {
        return active;
    }

    public void setActive(byte active) {
        this.active = active;
    }

    public byte getMarquerEffacer() {
        return marquerEffacer;
    }

    public void setMarquerEffacer(byte marquerEffacer) {
        this.marquerEffacer = marquerEffacer;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
