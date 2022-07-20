/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eggcooperation.eggnews.services;

import com.eggcooperation.eggnews.entities.Noticia;
import com.eggcooperation.eggnews.repositories.NoticiaRepositorio;
import com.eggcooperation.eggnews.repositories.UsuarioRepositorio;
import java.util.Date;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Gustavo Torti
 */
@Service
public class NoticiaService {
    
    @Autowired
    private NoticiaRepositorio noticiaRepositorio;
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    
    @Transactional
    public void crearNoticia(String titulo, MultipartFile imagen, String cuerpo, String idAutor){
        Noticia noticia = new Noticia();
        noticia.setTitulo(titulo);
        //noticia.setImagen(imagen);
        noticia.setCuerpo(cuerpo);
        noticia.setAutor(usuarioRepositorio.getOne(idAutor));
        noticia.setFechaAlta(new Date());
        
        noticiaRepositorio.save(noticia);
    }
    
}
