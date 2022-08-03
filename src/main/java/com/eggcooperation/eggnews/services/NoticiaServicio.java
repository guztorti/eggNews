/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eggcooperation.eggnews.services;

import com.eggcooperation.eggnews.entities.Imagen;
import com.eggcooperation.eggnews.entities.Noticia;
import com.eggcooperation.eggnews.exceptions.MiException;
import com.eggcooperation.eggnews.repositories.NoticiaRepositorio;
import com.eggcooperation.eggnews.repositories.UsuarioRepositorio;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Gustavo Torti
 */
@Service
public class NoticiaServicio {
    
    @Autowired
    private NoticiaRepositorio noticiaRepositorio;
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    
    @Transactional
    public void crearNoticia(String titulo, Imagen imagen, String cuerpo, String idAutor) throws MiException{
        
        validarDatos(titulo, imagen, cuerpo, idAutor);
        
        Noticia noticia = new Noticia();
        noticia.setTitulo(titulo);
        noticia.setImagen(imagen);
        noticia.setCuerpo(cuerpo);
        noticia.setAutor(usuarioRepositorio.encontrarUsuarioPorId(idAutor));
        noticia.setFechaAlta(new Date());
        noticia.setBorrada(false);
        
        noticiaRepositorio.save(noticia);
    }
    
    
    public Noticia encontrarNoticiaPorId(String idNoticia) throws MiException{
         Optional<Noticia> objeto = noticiaRepositorio.findById(idNoticia);
         
         if (objeto.isPresent()) {
              Noticia noticia = objeto.get();
              return noticia;
         }
         throw new MiException("el id de la noticia no es válido");
         
    }
    
    private boolean validarDatos(String titulo, Imagen imagen, String cuerpo, String idAutor) throws MiException{
         if (titulo.isEmpty() || titulo == null) {
              throw new MiException("El título no puede estar vacio o ser nulo");
         }
         if (imagen == null) {
              throw new MiException("la imagen no puede ser un objeto nulo");
         }
         if (idAutor.isEmpty() || idAutor == null) {
              throw new MiException("El autor no puede estar vacio o ser nulo");
         }
         if (cuerpo.length()>1000 || cuerpo == null) {
              throw new MiException("El cuerpo de la noticia no puede ser nulo ni contener más de 1000 caracteres");
         }
         return true;
    }
    
    public List<Noticia> mostrarNocias(){
         return noticiaRepositorio.listarNoticias();
    }
}
