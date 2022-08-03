/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eggcooperation.eggnews.repositories;

import com.eggcooperation.eggnews.entities.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Gustavo Torti
 */
@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, String>{
    
     @Query("SELECT u FROM  Usuario u WHERE u.activo = 1") //
     public List<Usuario> listarActivos();
     
     @Query("SELECT u FROM Usuario u WHERE u.id = :id")
     public Usuario encontrarUsuarioPorId(@Param ("id") String id );
}
