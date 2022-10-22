package com.entra21.explore.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.entra21.explore.domain.Estoque;

@Repository
public interface EstoqueRepository extends JpaRepository<Estoque , Integer> {
    @Query("SELECT max(e_objeto.id) from Estoque e_objeto")
    Integer getMaxIdEstoque();

    @Query("SELECT e_objeto FROM Estoque e_objeto WHERE e_objeto.usuario.nmUsuario like %:pNmUsuario% ORDER BY e_objeto.usuario.nmUsuario")
    List<Estoque> findAllByUsuario(@Param(value = "pNmUsuario") String pNmUsuario);

    @Query("SELECT e_objeto FROM Estoque e_objeto WHERE e_objeto.itemEstoque.nmItem like %:pNmItem% ORDER BY e_objeto.itemEstoque.nmItem")
    List<Estoque> findAllByItem(@Param(value = "pNmItem") Integer pNmItem);
}
