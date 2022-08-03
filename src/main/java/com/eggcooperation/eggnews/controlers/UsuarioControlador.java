/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eggcooperation.eggnews.controlers;

import com.eggcooperation.eggnews.exceptions.MiException;
import com.eggcooperation.eggnews.services.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author gustavotorti
 */
@Controller
@RequestMapping("/usuario")
public class UsuarioControlador {
     
     @Autowired
     private UsuarioServicio usuarioServicio;
     
     @GetMapping("/registrar")
     public String registrar(){
          return "/usuario_form.html";
     }
     
     @PostMapping("/registro")
     public String registro(@RequestParam String nombre, @RequestParam String email, @RequestParam String clave, ModelMap modelo){
          
          try {
               usuarioServicio.cargarUsuario(nombre, email, clave);
               return "redirect:/inicio";
          } catch (MiException ex) {
               modelo.put("error", ex.getMessage());
          }
          return "redirect:/usuario_form.html";
     }
}
