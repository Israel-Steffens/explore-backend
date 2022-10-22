package com.entra21.explore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entra21.explore.exceptions.DataIntegrityViolationException;
import com.entra21.explore.repositories.EstoqueRepository;
@Service
public class EstoqueService {
    @Autowired
    private EstoqueRepository estoqueRepository;

    public void delEstoque(Integer pId) {
        try {
            estoqueRepository.deleteById(pId);
        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Estoque nÃ£o pode ser deletado!");
        }
    }
}
    