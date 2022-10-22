package com.entra21.explore.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
@Entity
public class Receita implements Serializable{
    @Id
    private  Integer idReceita;
    
    @NotEmpty(message="Campo descReceita não pode ser vazio!")
    @Length(max=100, message="Campo DescReceita deve ter no maximo 100 caracteres!")
    private String descReceita ;

    @NotEmpty(message="Campo receitaAtiva não pode ser vazio!")
    @Length( max=1, message="Campo receitaAtiva  deve ter pelomenos 1 caracteres!")
    private String  receitaAtiva;
   
    private Double cargaMaquina;

    @NotEmpty(message="Campo nmReceita não pode ser vazio!")
    @Length( min = 1 ,max=100, message="Campo nmReceita  deve ter entre 1 e 100 caracteres!")
    private String  nmReceita;

    @ManyToMany
    @JoinTable(
        name="processo_receita",
        joinColumns = @JoinColumn(name="id_receita"),
        inverseJoinColumns = @JoinColumn(name="id_processo"),
        uniqueConstraints  = @UniqueConstraint(name = "processo_receita_pk",columnNames ={"id_receita","id_processo"} ) 
    )
    private List<Processo> receitaProcesso = new ArrayList<>();


    public Receita() {
    }


    public Receita(Integer idReceita, String descReceita, String receitaAtiva, Double cargaMaquina, String nmReceita) {
      setIdReceita(idReceita);
        setDescReceita(descReceita);
        setReceitaAtiva(receitaAtiva);
        setCargaMaquina(cargaMaquina);
        setNmReceita(nmReceita);
    }


    public Integer getIdReceita() {
        return idReceita;
    }


    public void setIdReceita(Integer idReceita) {
        this.idReceita = idReceita;
    }


    public String getDescReceita() {
        return descReceita;
    }


    public void setDescReceita(String descReceita) {
        this.descReceita = descReceita;
    }


    public String getReceitaAtiva() {
        return receitaAtiva;
    }


    public void setReceitaAtiva(String receitaAtiva) {
        this.receitaAtiva = receitaAtiva;
    }


    public Double getCargaMaquina() {
        return cargaMaquina;
    }


    public void setCargaMaquina(Double cargaMaquina) {
        this.cargaMaquina = cargaMaquina;
    }


    public String getNmReceita() {
        return nmReceita;
    }


    public void setNmReceita(String nmReceita) {
        this.nmReceita = nmReceita;
    }


    public List<Processo> getReceita_Processo() {
        return receitaProcesso;
    }


    public void setReceita_Processo(List<Processo> receita_Processo) {
        receitaProcesso = receita_Processo;
    }


    public List<Processo> getReceitaProcesso() {
        return receitaProcesso;
    }


    public void setReceitaProcesso(List<Processo> receitaProcesso) {
        this.receitaProcesso = receitaProcesso;
    }

    
}