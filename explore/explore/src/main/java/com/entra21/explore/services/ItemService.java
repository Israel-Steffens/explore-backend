package com.entra21.explore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entra21.explore.exceptions.DataIntegrityViolationException;
import com.entra21.explore.repositories.ItemRepository;
@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;
   
    public void delItem(Integer pId) {
        try {
            itemRepository.deleteById(pId);
        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Item nÃ£o pode ser deletado!");
        }
    }
    
}