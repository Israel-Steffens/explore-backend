package com.entra21.explore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entra21.explore.exceptions.DataIntegrityViolationException;
import com.entra21.explore.repositories.UnidadeMedidaRepository;

@Service
public class UnidadeMedidaService {
    @Autowired
    private  UnidadeMedidaRepository unidadeMedidaRepository;

    public void delUnidadeMedida(Integer pId) {
        try {
            unidadeMedidaRepository.deleteById(pId);
        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Estoque nÃ£o pode ser deletado!");
        }
    }
}
  