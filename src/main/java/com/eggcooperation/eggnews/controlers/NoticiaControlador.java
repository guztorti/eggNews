/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eggcooperation.eggnews.controlers;

import com.eggcooperation.eggnews.entities.Imagen;
import com.eggcooperation.eggnews.entities.Noticia;
import com.eggcooperation.eggnews.entities.Usuario;
import com.eggcooperation.eggnews.exceptions.MiException;
import com.eggcooperation.eggnews.services.ImagenServicio;
import com.eggcooperation.eggnews.services.NoticiaServicio;
import com.eggcooperation.eggnews.services.UsuarioServicio;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author gustavotorti
 */
@Controller
@RequestMapping("/noticia")
public class NoticiaControlador {
     
     @Autowired
     private NoticiaServicio noticiaServicio;
     @Autowired
     private ImagenServicio imagenServicio;
     @Autowired
     private UsuarioServicio usuarioServicio;
     

     @GetMapping("/registro")
     public String registro(ModelMap modelo){
          List<Usuario> usuarios = usuarioServicio.listar();
          modelo.addAttribute("autores", usuarios);
          return "noticia_cargar.html";
     }
     
     @PostMapping("/registrar")
     public String cargarNoticia(@RequestParam String titulo, @RequestParam String cuerpo,@RequestParam MultipartFile archivo,@RequestParam String idAutor, ModelMap modelo){
           try {
               Imagen imagen = imagenServicio.guardar(archivo);
               noticiaServicio.crearNoticia(titulo, imagen, cuerpo, idAutor);
               return "redirect:/inicio";
          } catch (MiException ex) {
               modelo.put("error", ex.getMessage());
               return "redirect:noticia_cargar.html";
          }
          
     }
     
     @GetMapping("/")
     public String listar(ModelMap modelo){
          List<Noticia> noticias = noticiaServicio.mostrarNocias();
          modelo.addAttribute("noticias", noticias);
          
          return "inicio.html";
     }
     
    
    @GetMapping("/mostrar/{id}")
    public String verNoticia(@PathVariable String id, ModelMap modelo) {
          try {
               modelo.addAttribute("noticia", noticiaServicio.encontrarNoticiaPorId(id));
               return "noticia_ver";
          } catch (MiException ex) {
               modelo.put("error", ex);
          }
          return null;
    }
}
