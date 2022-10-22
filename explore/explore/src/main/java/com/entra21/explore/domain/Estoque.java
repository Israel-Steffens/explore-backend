package com.entra21.explore.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
@Entity
public class  Estoque implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEstoque;

    @NotEmpty(message="Campo qtdEstoque não pode ser vazio!")
    @Length(min = 1, message="Campo qtdEstoque deve ter pelomenos 1 caractere!")
    private Double qtdEstoque;
    
    @NotEmpty(message="Campo qtdReservada não pode ser vazio!")
    private Double qtdReservada;

    @NotEmpty(message="Campo qtdEstqDisponivel não pode ser vazio!")
    @Length(min=1, message="Campo qtdEstqDisponivel deve ter no minímo 1 caractere!")
    private Double qtdEstqDisponivel;

   @ManyToOne
   @JoinColumn (name = "idUsuario")
   private Usuario usuario;

   @ManyToOne 
   @JoinColumn (name = "idItem")
   private Item itemEstoque;
   
    public Estoque(Integer idEstoque, Double qtdEstoque, Double qtdReservada, Double qtdEstqDisponivel,
        Usuario usuario, Item item) {
    setIdEstoque(idEstoque);
    setQtdEstoque(qtdEstoque);
    setQtdReservada(qtdReservada);
    setQtdEstqDisponivel(qtdEstqDisponivel);
    setUsuario(usuario);
    setItem(item);
}

    public Estoque() {

    }
    
    public Integer getIdEstoque() {
        return idEstoque;
    }
    public void setIdEstoque(Integer idEstoque) {
        this.idEstoque = idEstoque;
    }
    public Double getQtdEstoque() {
        return qtdEstoque;
    }
    public void setQtdEstoque(Double qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }
    public Double getQtdReservada() {
        return qtdReservada;
    }
    public void setQtdReservada(Double qtdReservada) {
        this.qtdReservada = qtdReservada;
    }
    public Double getQtdEstqDisponivel() {
        return qtdEstqDisponivel;
    }
    public void setQtdEstqDisponivel(Double qtdEstqDisponivel) {
        this.qtdEstqDisponivel = qtdEstqDisponivel;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public Item getItem() {
        return itemEstoque;
    }
    public void setItem(Item item) {
        this.itemEstoque = item;
    }
}