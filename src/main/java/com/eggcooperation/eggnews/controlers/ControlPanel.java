/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eggcooperation.eggnews.controlers;

import com.eggcooperation.eggnews.entities.Noticia;
import com.eggcooperation.eggnews.services.NoticiaServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author gustavotorti
 */


@Controller
@RequestMapping("/")
public class ControlPanel {
     
     
@Autowired
private NoticiaServicio noticiaServicio;
     
     @GetMapping("/inicio")
     public String index(ModelMap modelo){
          List<Noticia> noticias = noticiaServicio.mostrarNocias();
          modelo.addAttribute("noticias", noticias);
          return "inicio.html";
     }
     
     /*@GetMapping("/error")
     public String error(){
          return "error";
     }*/
}
