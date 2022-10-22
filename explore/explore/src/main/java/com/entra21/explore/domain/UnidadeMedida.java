package com.entra21.explore.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

@Entity
public class UnidadeMedida implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer  idUnidMed;

    @NotEmpty(message = "Campo idUnidMed Não pode estar vazio  ")
    @Length(min = 1,max = 50, message = "Campo idUnidMed  deve ter entre 1 e 50 caracter")
    private String  nmUnidMed;

    @NotEmpty(message = "Campo nmUsuario Não pode estar vazio  ")
    @Length(min = 1,max = 45, message = "Campo nmUsuario  deve ter entre 1 e 45 caracter")
    private String descUnidMed;

  
    @OneToMany(mappedBy = "uniMedida")
    private List<Item> Item;


    public UnidadeMedida() {
    }

    public UnidadeMedida(Integer idUnidMed,  String nmUnidMed, String descUnidMed) {
        setIdUnidMed(idUnidMed);
        setNmUnidMed(nmUnidMed);
        setDescUnidMed(descUnidMed);
    }

    public Integer getIdUnidMed() {
        return idUnidMed;
    }

    public void setIdUnidMed(Integer idUnidMed) {
        this.idUnidMed = idUnidMed;
    }

    public String getNmUnidMed() {
        return nmUnidMed;
    }

    public void setNmUnidMed(String nmUnidMed) {
        this.nmUnidMed = nmUnidMed;
    }

    public String getDescUnidMed() {
        return descUnidMed;
    }

    public void setDescUnidMed(String descUnidMed) {
        this.descUnidMed = descUnidMed;
    }

    public List<Item> getItem() {
        return Item;
    }

    public void setItem(List<Item> item) {
        Item = item;
    }
}
