package com.entra21.explore.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.entra21.explore.domain.UnidadeMedida;

public interface UnidadeMedidaRepository extends JpaRepository<UnidadeMedida , Integer>  {
    
    @Query("SELECT max(ud_objeto.id) from UnidadeMedida ud_objeto")
    Integer getMaxIdUnidadeMedida();

    /*@Query("SELECT ud_objeto FROM UnidadeMedida ud_objeto WHERE ud_objeto.Item.idItem = :pIdItem ORDER BY nmUnidMed")
    List<UnidadeMedida> findAllByItem(@Param(value = "pIdItem") Integer pIdItem);*/

//
    @Query("SELECT ud_objeto FROM UnidadeMedida ud_objeto WHERE ud_objeto.nmUnidMed like %:pNome% ORDER BY nmUnidMed")
    List<UnidadeMedida> findAllByName(@Param(value = "pNome") String pNome);
    
    @Query("SELECT ud_objeto FROM UnidadeMedida ud_objeto WHERE ud_objeto.idUnidMed = :pId ORDER BY idUnidMed")
    List<UnidadeMedida> findAllByid(@Param(value = "pId") Integer pId);
    
    @Query("SELECT ud_objeto FROM UnidadeMedida ud_objeto WHERE ud_objeto.descUnidMed like %:pDesc% ORDER BY descUnidMed")
    List<UnidadeMedida> findAllByDesc(@Param(value = "pDesc") String pDesc);
}
