package com.entra21.explore.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

@Entity
public class Item implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idItem;

    @NotEmpty(message="Campo nmItem não pode ser vazio!")
    @Length(min=1, max = 45 ,message="Campo nmItem deve ter entre 1 e 45 caracteres!")
    private String nmItem;
    
    @NotEmpty(message="Campo descMaterial não pode ser vazio!")
    @Length(min=1, max = 45, message="Campo descMaterial deve ter entre 1 e 45 caracteres!")
    private String descMaterial;

    @NotEmpty(message="Campo qtdEstqDisponivel não pode ser vazio!")
    private Double vlrUnitario;

    @OneToMany (mappedBy = "itemEstoque")
    private List<Estoque>estoque;

   private UnidadeMedida uniMedida;

   @OneToMany(mappedBy = "item")
   private List<ProcessoHasItem> processoHI;


    public Item(Integer idItem, String nmItem, String descMaterial, Double vlrUnitario) {
        setIdItem(idItem);
        setNmItem(nmItem);
        setDescMaterial(descMaterial);
        setVlrUnitario(vlrUnitario);
    }

    public Item() {
    }

    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }

    public String getNmItem() {
        return nmItem;
    }

    public void setNmItem(String nmItem) {
        this.nmItem = nmItem;
    }

    public String getDescMaterial() {
        return descMaterial;
    }

    public void setDescMaterial(String descMaterial) {
        this.descMaterial = descMaterial;
    }

    public Double getVlrUnitario() {
        return vlrUnitario;
    }

    public void setVlrUnitario(Double vlrUnitario) {
        this.vlrUnitario = vlrUnitario;
    }

    public List<Estoque> getEstoque() {
        return estoque;
    }

    public void setEstoque(List<Estoque> estoque) {
        this.estoque = estoque;
    }

    public UnidadeMedida getUniMedida() {
        return uniMedida;
    }

    public void setUniMedida(UnidadeMedida uniMedida) {
        this.uniMedida = uniMedida;
    }

    public List<ProcessoHasItem> getProcessoHI() {
        return processoHI;
    }

    public void setProcessoHI(List<ProcessoHasItem> processoHI) {
        this.processoHI = processoHI;
    }
}
