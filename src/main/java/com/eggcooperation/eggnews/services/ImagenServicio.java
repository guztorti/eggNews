/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eggcooperation.eggnews.services;

import com.eggcooperation.eggnews.entities.Imagen;
import com.eggcooperation.eggnews.exceptions.MiException;
import com.eggcooperation.eggnews.repositories.ImagenRepositorio;
import java.io.IOException;
import java.util.Date;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author gustavotorti
 */
@Service
public class ImagenServicio {
     @Autowired
     private ImagenRepositorio imagenRepositorio;
     
     @Transactional
     public Imagen guardar(MultipartFile archivo) throws MiException{
        if(archivo != null){
           
             try {
                  Imagen foto = new Imagen();
                  foto.setMime(archivo.getContentType());
                  foto.setNombre(archivo.getName());
                  foto.setContenido(archivo.getBytes());
                  foto.setFCreacion(new Date());
                  foto.setFModificacion(foto.getFCreacion());
                  
                  return imagenRepositorio.save(foto);
             } catch (IOException ex) {
                  Logger.getLogger(ImagenServicio.class.getName()).log(Level.SEVERE, null, ex);
             }
                
            
        }else{
             throw new MiException("no se puede guardar una imagen si no está especificada");
             
        }
       return null;
    }
     
     public Imagen buscarImagen(String idImagen) throws MiException{
          
          Imagen imagen = null;
          Optional<Imagen> respuestaImagen = imagenRepositorio.findById(idImagen);
          
          if (respuestaImagen.isPresent()) {
               imagen = respuestaImagen.get();
          }else{
               throw new MiException("La Id no corresponde a ninguna imagen");
          }
          return imagen;
     }
}
