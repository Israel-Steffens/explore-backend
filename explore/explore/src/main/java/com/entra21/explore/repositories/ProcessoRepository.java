package com.entra21.explore.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.entra21.explore.domain.Processo;

@Repository
public interface ProcessoRepository   extends JpaRepository<Processo , Integer>{
    
    
        @Query("SELECT max(p_objeto.id) from Processo p_objeto")
        Integer getMaxIdProcesso();
       
        @Query("SELECT p_objeto FROM Processo p_objeto JOIN p_objeto.processoReceita processo WHERE processo.idReceita = :pIdReceita ORDER BY p_objeto.nmProcesso")
        List<Processo> findAllByReceita(@Param(value = "pIdReceita") Integer pIdReceita);
       
        
        @Query("SELECT p_objeto FROM Processo p_objeto JOIN p_objeto.processoReceita processo WHERE processo.nmReceita like %:pNmReceita% ORDER BY processo.nmReceita")
        List<Processo> findAllByNmReceita(@Param(value = "pNmReceita") String pNmReceita);

     
        @Query("SELECT ud_objeto FROM Processo ud_objeto WHERE ud_objeto.idProcesso = :pId ORDER BY idProcesso")
       List<Processo> findAllByid(@Param(value = "pId") Integer pId);

       @Query("SELECT ud_objeto FROM Processo ud_objeto WHERE ud_objeto.nmProcesso like %:pNome% ORDER BY nmProcesso")
       List<Processo> findAllByname(@Param(value = "pNome") String pNome);

       @Query("SELECT ud_objeto FROM Processo ud_objeto WHERE ud_objeto.descProcesso like %:pDesc% ORDER BY descProcesso")
       List<Processo> findAllBydesc(@Param(value = "pDesc") String pDesc);
    
       @Query("SELECT ud_objeto FROM Processo ud_objeto WHERE ud_objeto.processoAtivo = :pProcessoAtivo ORDER BY processoAtivo")
       List<Processo> findAllByprocessoAtivo(@Param(value = "pProcessoAtivo") char pProcessoAtivo);


    

}