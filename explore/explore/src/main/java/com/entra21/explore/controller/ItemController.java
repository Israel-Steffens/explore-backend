package com.entra21.explore.controller;

import com.entra21.explore.domain.Item;
import com.entra21.explore.exceptions.ObjectNotFoundException;
import com.entra21.explore.repositories.ItemRepository;
import com.entra21.explore.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/item")
public class ItemController {

    @Autowired
    private ItemService iServices;

    @Autowired
    private ItemRepository iRepository;

    @GetMapping
    public ResponseEntity<List<Item>> findAll() {
        List<Item> items = iRepository.findAll();
        return ResponseEntity.ok().body(items);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Item> findById(@PathVariable Integer id) {
        Item item = iRepository.findById(id)
                .orElseThrow(
                        () -> new ObjectNotFoundException("Item " + id + " não encontrado!")
                );
        return ResponseEntity.ok().body(item);
    }

    @GetMapping(value = "/unidMedida/{id}")
    public ResponseEntity<List<Item>> findAllByItem(@PathVariable Integer id) {
        return ResponseEntity.ok().body(iRepository.findAllByItem(id));
    }


    @PostMapping()
    public ResponseEntity<Item> newItem(@Valid @RequestBody Item pItem) {
        Integer maxId = iRepository.getMaxIdItem();
        if (maxId == null) {
            maxId = 0;
        }
        pItem.setIdItem(maxId + 1);
        iRepository.save(pItem);

        URI vURI = ServletUriComponentsBuilder.fromCurrentRequest().path("").buildAndExpand(pItem.getIdItem()).toUri();
        return ResponseEntity.created(vURI).body(pItem);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delEstacao(@PathVariable Integer id) {
        iServices.delItem(id);
        return ResponseEntity.noContent().build();
    }


    @PutMapping(value = "/{id}")
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