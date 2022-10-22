package com.entra21.explore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entra21.explore.exceptions.DataIntegrityViolationException;
import com.entra21.explore.repositories.ProcessoRepository;

@Service
public class ProcessoService {
    @Autowired
    private ProcessoRepository processoRepository;

    public void delProcesso(Integer pId) {
        try {
            processoRepository.deleteById(pId);
        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Estoque nÃ£o pode ser deletado!");
        }
    }
}