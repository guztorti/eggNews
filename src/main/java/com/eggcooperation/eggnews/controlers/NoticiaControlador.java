/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eggcooperation.eggnews.controlers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author gustavotorti
 */
@Controller("/noticia")
public class NoticiaControlador {
     @GetMapping("/noticia")
     public String noticia(){
          return "noticia.html";
     }
     
}
