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
public class Usuario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer  idUsuario;

    @NotEmpty(message = "Campo nmUsuario Não pode estar vazio  ")
    @Length(min = 1,max = 255, message = "Campo nmUsuario  deve ter entre 1 e 255 caracter")
    private String nmUsuario;
    
    @NotEmpty(message = "Campo CPF Não pode estar vazio  ")
    @Length(min = 1,max = 14, message = "Campo CPF  deve ter entre 1 e 14 caracter")
    private String cpf;

    @NotEmpty(message = "Campo email Não pode estar vazio  ")
    @Length(min = 1,max = 100, message = "Campo email  deve ter entre 1 e 100 caracter")
    private String email;
    
    @NotEmpty(message = "Campo telefone Não pode estar vazio  ")
    @Length(min = 1,max = 45, message = "Campo telefone  deve ter entre 1 e 45 caracter")
    private String telefone;

    @NotEmpty(message = "Campo senha Não pode estar vazio  ")
    @Length(min = 1,max = 45, message = "Campo senha  deve ter entre 1 e 45 caracter")
    private String senha;

    @NotEmpty(message = "Campo genero Não pode estar vazio  ")
    @Length(max=1, message="Campo genero deve ter apenas 1 caractere!")
    private String genero;
    @OneToMany(mappedBy = "usuario")
    private List<Estoque> estoque;

    
    public Usuario() {
    }

    public Usuario(Integer idUsuario, String nmUsuario, String cpf, String email, String telefone, String senha, String genero) {
        setIdUsuario(idUsuario);
        setNmUsuario(nmUsuario);
        setCpf(cpf);
        setEmail(email);
        setTelefone(telefone);
        setSenha(senha);
        setGenero(genero);
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNmUsuario() {
        return nmUsuario;
    }

    public void setNmUsuario(String nmUsuario) {
        this.nmUsuario = nmUsuario;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }





}