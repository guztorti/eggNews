/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eggcooperation.eggnews.repositories;

import com.eggcooperation.eggnews.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Gustavo Torti
 */
@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, String>{
    
}
