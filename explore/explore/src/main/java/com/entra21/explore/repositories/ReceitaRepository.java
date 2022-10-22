package com.entra21.explore.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.entra21.explore.domain.Receita;

@Repository
public interface ReceitaRepository  extends JpaRepository<Receita , Integer>{

    
    @Query("SELECT max(r_objeto.id) from Receita r_objeto")
    Integer getMaxIdReceita();

    @Query("SELECT r_objeto FROM Receita r_objeto JOIN r_objeto.receitaProcesso receita WHERE receita.idProcesso = :pIdProcesso ORDER BY r_objeto.nmReceita")
    List<Receita> findAllByProcesso(@Param(value = "pIdProcesso") Integer pIdProcesso);

    @Query("SELECT r_objeto FROM Receita r_objeto JOIN r_objeto.receitaProcesso receita WHERE receita.nmProcesso like %:pNmProcesso% ORDER BY receita.nmProcesso")
        List<Receita> findAllByNmProcesso(@Param(value = "pNmProcesso") String pNmProcesso);
     
    @Query("SELECT ud_objeto FROM Receita ud_objeto WHERE ud_objeto.idReceita = :pId ORDER BY idReceita")
    List<Receita> findAllByid(@Param(value = "pId") Integer pId);

    @Query("SELECT ud_objeto FROM Receita ud_objeto WHERE ud_objeto.nmReceita like %:pNome% ORDER BY nmReceita")
    List<Receita> findAllByname(@Param(value = "pNome") String pNome);

    @Query("SELECT ud_objeto FROM Receita ud_objeto WHERE ud_objeto.descReceita like %:pDesc% ORDER BY descReceita")
    List<Receita> findAllBydesc(@Param(value = "pDesc") String pDesc);

    @Query("SELECT ud_objeto FROM Receita ud_objeto WHERE ud_objeto.receitaAtiva = :pAtivo ORDER BY receitaAtiva")
    List<Receita> findAllByativo(@Param(value = "pAtivo") char pAtivo);

    @Query("SELECT ud_objeto FROM Receita ud_objeto WHERE ud_objeto.cargaMaquina = :pCarga ORDER BY cargaMaquina")
    List<Receita> findAllBycarga(@Param(value = "pCarga") Double pCarga);

}
