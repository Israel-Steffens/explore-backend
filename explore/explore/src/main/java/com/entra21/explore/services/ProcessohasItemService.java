package com.entra21.explore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entra21.explore.exceptions.DataIntegrityViolationException;
import com.entra21.explore.repositories.ProcessoHasItemRepository;

@Service
public class ProcessohasItemService {
    
    @Autowired
    private ProcessoHasItemRepository processoHasItemRepository;

    public void delEstoque(Integer pId) {
        try {
            processoHasItemRepository.deleteById(pId);
        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Estoque nÃ£o pode ser deletado!");
        }
    }
}
  