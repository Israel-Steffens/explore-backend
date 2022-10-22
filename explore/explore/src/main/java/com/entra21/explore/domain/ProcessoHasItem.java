package com.entra21.explore.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

@Entity
public class ProcessoHasItem implements Serializable{
    
    @Id
    private Integer idPHI;

    
    @NotEmpty(message="Campo qtdItem não pode ser vazio!")
       private Double qtdItem;

       @ManyToOne
       @JoinColumn (name= "idProcesso")
       private Processo processo;

      @ManyToOne 
      @JoinColumn (name= "idItem")
      private Item item;

    public ProcessoHasItem(
            @NotEmpty(message = "Campo qtdItem não pode ser vazio!") Double qtdItem) {
        setQtdItem(qtdItem);

    }

    public Integer getIdPHI() {
        return idPHI;
    }

    public void setIdPHI(Integer idPHI) {
        this.idPHI = idPHI;
    }

    public Double getQtdItem() {
        return qtdItem;
    }

    public void setQtdItem(Double qtdItem) {
        this.qtdItem = qtdItem;
    }

    public Processo getProcesso() {
        return processo;
    }

    public void setProcesso(Processo processo) {
        this.processo = processo;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}