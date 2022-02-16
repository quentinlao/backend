/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daoebenus.ebenus.dao;

import com.daoebenus.ebenus.dao.entities.Adresse;
import java.util.List;

/**
 *
 * @author Quentin
 */
public interface IAdresseDao {
     
    public List<Adresse> findAllAdresses();

    public Adresse findAdresseById(int idAdresse);
    
    public List<Adresse> findAdreseByIdUtilisateur(int idUtilisateur);
    
    public List<Adresse> findAdresseByIdentifiantUtilisateur(String identifiantUtilisateur);

    public List<Adresse> findAdresseByRue(String rueAdresse);

    public Adresse createAdresse(Adresse adresse);

    public Adresse updateAdresse(Adresse adresse);

    public boolean deleteAdresse(Adresse adresse);
}
