package com.entra21.explore.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.entra21.explore.domain.Usuario;

@Repository
public interface UsuarioRepository  extends JpaRepository<Usuario , Integer>{

    @Query("SELECT max(us_objeto.id) from Usuario us_objeto")
    Integer getMaxIdUsuario();

    @Query("SELECT us_objeto FROM Usuario us_objeto WHERE us_objeto.nmUsuario = :pNmUsuario ORDER BY nmUsuario")
    Usuario findByName(@Param(value = "pNmUsuario") String pNmUsuario);


    @Query("SELECT ud_objeto FROM Usuario ud_objeto WHERE ud_objeto.idUsuario =  :pId ORDER BY idUsuario")
       List<Usuario> findAllByid(@Param(value = "pId") Integer pId);

       @Query("SELECT ud_objeto FROM Usuario ud_objeto WHERE ud_objeto.nmUsuario like %:pNome% ORDER BY nmUsuario")
    List<Usuario> findAllByname(@Param(value = "pNome") String pNome);

    @Query("SELECT ud_objeto FROM Usuario ud_objeto WHERE ud_objeto.cpf = :pCpf ORDER BY cpf")
    List<Usuario> findAllBycpf(@Param(value = "pCpf") String pCpf);

  

}