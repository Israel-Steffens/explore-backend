package com.entra21.explore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entra21.explore.exceptions.DataIntegrityViolationException;
import com.entra21.explore.repositories.ReceitaRepository;

@Service
public class ReceitaService {
    
    @Autowired
    private ReceitaRepository receitaRepository;

    public void delReceita(Integer pId) {
        try {
            receitaRepository.deleteById(pId);
        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Estoque nÃ£o pode ser deletado!");
        }
    }
}
  