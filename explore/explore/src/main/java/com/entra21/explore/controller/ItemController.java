package com.entra21.explore.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.entra21.explore.domain.Item;
import com.entra21.explore.domain.UnidadeMedida;
import com.entra21.explore.exceptions.ObjectNotFoundException;
import com.entra21.explore.repositories.ItemRepository;
import com.entra21.explore.repositories.UnidadeMedidaRepository;
import com.entra21.explore.services.ItemService;

@CrossOrigin("*")
@RestController
@RequestMapping(value="/item")
public class ItemController {
    
    @Autowired
    private ItemService iServices;

    @Autowired
    private ItemRepository iRepository;

    @Autowired
    private UnidadeMedidaRepository unidadeMedidaRepository;


    @GetMapping
    public ResponseEntity<List<Item>> findAll() {
        List<Item> items = iRepository.findAll();
        return ResponseEntity.ok().body(items);
    }
    @GetMapping(value="/{id}")
    public ResponseEntity<Item> findById(@PathVariable Integer id) {
        Item item = iRepository.findById(id)
            .orElseThrow(
                () -> new ObjectNotFoundException("Item "+id+" não encontrado!")
            );
        return ResponseEntity.ok().body(item);
    }

    @GetMapping(value="/unidMedida/{id}")
    public ResponseEntity<List<Item>> findAllByItem(@PathVariable Integer id) {
        return ResponseEntity.ok().body(iRepository.findAllByItem(id));
    }
    
   
    @PostMapping("/{idUnidMed}")
    public ResponseEntity<Item> insPHi(@Valid @PathVariable Integer idUnidMed,  @Valid @RequestBody Item pItem  ){
        UnidadeMedida unidMed = unidadeMedidaRepository.findById(idUnidMed).orElseThrow(
            () -> new ObjectNotFoundException("Item nÃo encontrado!")
        );
       
        
        pItem.setUniMedida(unidMed);
        Integer maxId = iRepository.getMaxIdItem();
        if(maxId == null){
            maxId = 0;
        }
        pItem.setIdItem(maxId+1); 
        iRepository.save(pItem);
        
         URI vURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pItem.getIdItem()).toUri();
        return ResponseEntity.created(vURI).body(pItem);
    }


    @DeleteMapping("/{id}")
  public ResponseEntity<Void> delEstacao(@PathVariable Integer id){
    iServices.delItem(id);
    return ResponseEntity.noContent().build();
}   


@PutMapping(value="/{id}")
    public ResponseEntity<Item> updDepto(@Valid @PathVariable Integer id,
        @RequestBody Item pItem) {
            Item atualItem =
            iRepository.findById(id)
                .orElseThrow(
                    () -> new ObjectNotFoundException("Departamento nÃ£o encontrado!")
                );
                atualItem.setNmItem(pItem.getNmItem());
                atualItem.setDescMaterial(pItem.getDescMaterial());
                atualItem.setUniMedida(pItem.getUniMedida());
                atualItem.setVlrUnitario(pItem.getVlrUnitario());
                iRepository.save(atualItem);
            return ResponseEntity.ok().body(atualItem);
    }
  
}