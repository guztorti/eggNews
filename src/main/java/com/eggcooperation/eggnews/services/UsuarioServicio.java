/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eggcooperation.eggnews.services;

import com.eggcooperation.eggnews.entities.Usuario;
import com.eggcooperation.eggnews.exceptions.MiException;
import com.eggcooperation.eggnews.repositories.UsuarioRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author gustavotorti
 */
@Service
public class UsuarioServicio {

     @Autowired
     private UsuarioRepositorio usuarioRepositorio;

     public Usuario buscarUsuarioPorId(String idUsuario) throws MiException {
          Optional<Usuario> objeto = usuarioRepositorio.findById(idUsuario);

          if (objeto.isPresent()) {
               return objeto.get();

          }
          throw new MiException("el usuario no está registrado en la base de datos");
     }

     @Transactional
     public boolean cargarUsuario(String nombre, String email, String clave) throws MiException {
          try {
               if (nombre.isEmpty() || nombre == null) {
                    throw new MiException("El nombre de usuario no puede ser nulo ni estar vacio");
               }
               if (email.isEmpty() || email == null) {
                    throw new MiException("El email de usuario no puede ser nulo ni estar vacio\n"
                            + "y debe contener @ y .");
               }
               if (clave.length() < 6 || clave == null) {
                    throw new MiException("La clave de usuario no puede ser nula ni contener menos de 6 caracteres");
               }
          } catch (MiException ex) {
               return false;
          }

          Usuario usuario = new Usuario();
          usuario.setActivo(true);
          usuario.setNombre(nombre);
          usuario.setEmail(email);
          usuario.setClave(clave);

          usuarioRepositorio.save(usuario);

          return true;
     }

     public List<Usuario> listar() {
          return usuarioRepositorio.listarActivos();
     }
}
