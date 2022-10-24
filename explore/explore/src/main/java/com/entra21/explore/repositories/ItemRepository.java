package com.entra21.explore.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.entra21.explore.domain.Item;
@Repository
public interface ItemRepository extends JpaRepository<Item , Integer>{

    @Query("SELECT max(i_objeto.id) from Item i_objeto")
    Integer getMaxIdItem();

    @Query("SELECT i_objeto FROM Item i_objeto WHERE i_objeto.idItem = :idItem")
    List<Item> findAllByItem(@Param(value = "idItem") Integer idItem);

}