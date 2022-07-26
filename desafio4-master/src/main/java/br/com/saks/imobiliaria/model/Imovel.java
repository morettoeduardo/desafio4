/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.saks.imobiliaria.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;

/**
 *
 * @author 7839677
 */
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
@Data
public class Imovel implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "id_tipoImovel")
    private TipoImovel tipoImovel;
    
    @ManyToMany
    @JoinTable(name = "interesse",
    joinColumns = @JoinColumn(name = "id_imovel"),
    inverseJoinColumns = @JoinColumn(name = "id_cliente"))
    private List<Cliente> clientes;
    
    @Column(nullable = false, length=100)
    private String titulo;
    
    @Column(length=500)
    private String descricao;
    
    @Temporal(TemporalType.DATE)
    @Column(nullable = false, name = "data_criacao")
    private Date dataCriacao;
    
    @Column(columnDefinition = "DECIMAL(8,2)")
    private float valor;
    
    @Column(nullable = false, length=1)
    private int status;
    
}