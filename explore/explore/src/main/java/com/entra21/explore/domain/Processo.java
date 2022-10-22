package com.entra21.explore.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

@Entity
public class Processo implements Serializable {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer idProcesso;

@NotEmpty(message="Campo descProcesso não pode ser vazio!")
@Length( max=255, message="Campo descProcesso deve ter entre 5 e 255 caracteres!")
private String descProcesso;
    
@NotEmpty(message="Campo processoAtivo não pode ser vazio!")
@Length(max=1, message="Campo processoAtivo deve ter apenas 1 caractere!")
private String processoAtivo;

@NotEmpty(message="Campo nmProcesso não pode ser vazio!")
@Length(min = 1,max=100, message="Campo nmProcesso deve ter entre 1 e 100caracteres!")
private String nmProcesso;

@OneToMany (mappedBy =  "processo")
private List<ProcessoHasItem> processoHI;

@ManyToMany(mappedBy = "receitaProcesso")
private List<Receita> processoReceita  = new ArrayList<>();

public Processo(Integer idProcesso,
        @NotEmpty(message = "Campo descProcesso não pode ser vazio!") @Length(max = 255, message = "Campo descProcesso deve ter entre 5 e 255 caracteres!") String descProcesso,
        @NotEmpty(message = "Campo processoAtivo não pode ser vazio!") @Length(max = 1, message = "Campo processoAtivo deve ter apenas 1 caractere!") String processoAtivo,
        @NotEmpty(message = "Campo nmProcesso não pode ser vazio!") @Length(min = 1, max = 100, message = "Campo nmProcesso deve ter entre 1 e 100caracteres!") String nmProcesso,
        List<ProcessoHasItem> processoHI, List<Receita> processoReceita) {
    this.idProcesso = idProcesso;
    this.descProcesso = descProcesso;
    this.processoAtivo = processoAtivo;
    this.nmProcesso = nmProcesso;
    this.processoHI = processoHI;
    this.processoReceita = processoReceita;
}

public Processo() {
}

public Integer getIdProcesso() {
    return idProcesso;
}

public void setIdProcesso(Integer idProcesso) {
    this.idProcesso = idProcesso;
}

public String getDescProcesso() {
    return descProcesso;
}

public void setDescProcesso(String descProcesso) {
    this.descProcesso = descProcesso;
}

public String getProcessoAtivo() {
    return processoAtivo;
}

public void setProcessoAtivo(String processoAtivo) {
    this.processoAtivo = processoAtivo;
}

public String getNmProcesso() {
    return nmProcesso;
}

public List<ProcessoHasItem> getProcessoHI() {
    return processoHI;
}

public void setProcessoHI(List<ProcessoHasItem> processoHI) {
    this.processoHI = processoHI;
}

public List<Receita> getProcessoReceita() {
    return processoReceita;
}

public void setProcessoReceita(List<Receita> processoReceita) {
    this.processoReceita = processoReceita;
}

public void setNmProcesso(String nmProcesso) {
    this.nmProcesso = nmProcesso;
}



}