/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eggcooperation.eggnews.entities;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;
import com.eggcooperation.eggnews.entities.Imagen;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.Data;

/**
 *
 * @author Gustavo Torti
 */
@Entity
@Data
public class Noticia {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    private String titulo;
    
    @OneToOne
    private Imagen imagen;
    
    @Column(columnDefinition="varchar(500)")
    private String cuerpo;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAlta;
    
    @ManyToOne
    private Usuario autor;
    
   
    private boolean borrada;

    
}
