package com.entra21.explore.repositories;
import com.entra21.explore.domain.ProcessoHasItem;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface ProcessoHasItemRepository extends JpaRepository<ProcessoHasItem, Integer>{
    @Query("SELECT max(phi_objeto.id) from ProcessoHasItem phi_objeto")
    Integer getMaxIdPHI();
    

    @Query("SELECT phi_objeto FROM ProcessoHasItem phi_objeto WHERE phi_objeto.item.idItem = :pIdItem ORDER BY phi_objeto.item.nmItem")
    List<ProcessoHasItem> findAllByPHItem(@Param(value = "pIdItem") Integer pIdItem);


    @Query("SELECT phi_objeto FROM ProcessoHasItem phi_objeto WHERE phi_objeto.processo.idProcesso = :pIdProcesso ORDER BY phi_objeto.processo.nmProcesso")
    List<ProcessoHasItem> findAllByPHProcesso(@Param(value = "pIdProcesso") Integer pIdProcesso);

}