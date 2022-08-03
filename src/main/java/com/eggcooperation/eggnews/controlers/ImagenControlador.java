/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eggcooperation.eggnews.controlers;

import com.eggcooperation.eggnews.entities.Noticia;
import com.eggcooperation.eggnews.exceptions.MiException;
import com.eggcooperation.eggnews.services.ImagenServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.eggcooperation.eggnews.services.NoticiaServicio;
import com.eggcooperation.eggnews.services.UsuarioServicio;

/**
 *
 * @author gustavotorti
 */

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
@RequestMapping("/imagen")
public class ImagenControlador {
     
     @Autowired
     private NoticiaServicio noticiaServicio;
     
     @GetMapping("/noticia")
     public ResponseEntity<byte[]> fotoImagen(@RequestParam String idNoticia) throws Exception{
          Noticia noticia = noticiaServicio.encontrarNoticiaPorId(idNoticia);
          try{
          if(noticia.getImagen()==null){
               throw new MiException("la imagen no puede ser nula");
          }
          byte[] imagen = noticia.getImagen().getContenido();
          HttpHeaders headers = new HttpHeaders();
          headers.setContentType(MediaType.IMAGE_JPEG);
          return new ResponseEntity<>(imagen, headers, HttpStatus.OK);
          }catch(Exception ex){
               System.out.println("La noticia no tiene foto");
               return new ResponseEntity<>(HttpStatus.NOT_FOUND);
          }
     }

}
