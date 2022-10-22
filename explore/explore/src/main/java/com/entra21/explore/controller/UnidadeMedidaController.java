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
import com.entra21.explore.services.UnidadeMedidaService;

@CrossOrigin("*")
@RestController
@RequestMapping(value="/unidademedida")
public class UnidadeMedidaController {

    @Autowired
    private UnidadeMedidaService unidMedService;

    @Autowired
    private UnidadeMedidaRepository unidmedRepository;
    @Autowired
    private ItemRepository iRepository;

    @GetMapping
    public ResponseEntity<List<UnidadeMedida>> findAll() {
        List<UnidadeMedida> unidmeds = unidmedRepository.findAll();
        return ResponseEntity.ok().body(unidmeds);
    }
//
    @GetMapping(value="/nome/{nome}")
    public ResponseEntity<List<UnidadeMedida>> findAllByName(@PathVariable String nome) {
        List<UnidadeMedida> unidmeds = unidmedRepository.findAllByName(nome);
        return ResponseEntity.ok().body(unidmeds);
    }

    @GetMapping(value="/id/{id}")
    public ResponseEntity<List<UnidadeMedida>> findAllById(@PathVariable Integer id) {
        List<UnidadeMedida> unidmeds = unidmedRepository.findAllByid(id);
        return ResponseEntity.ok().body(unidmeds);
    }

    @GetMapping(value="/desc/{desc}")
    public ResponseEntity<List<UnidadeMedida>> findAllByDesc(@PathVariable String desc) {
        List<UnidadeMedida> unidmeds = unidmedRepository.findAllByDesc(desc);
        return ResponseEntity.ok().body(unidmeds);
    }

    //
    @GetMapping(value="/{id}/item")
    public ResponseEntity<List<Item>> findAllByItem(@PathVariable Integer id) {
        return ResponseEntity.ok().body(iRepository.findAllByItem(id));
    }


    
@PostMapping 
public ResponseEntity<UnidadeMedida> insEstacao(@Valid @RequestBody UnidadeMedida pUnidMed){
    Integer maxId = unidmedRepository.getMaxIdUnidadeMedida();
    if(maxId == null){
        maxId = 0;
    }
    pUnidMed.setIdUnidMed(maxId+1); 
    unidmedRepository.save(pUnidMed);
    URI vUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pUnidMed.getIdUnidMed()).toUri();
    return ResponseEntity.created(vUri).body(pUnidMed);
}

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delEstacao(@PathVariable Integer id){
        unidMedService.delUnidadeMedida(id);
        return ResponseEntity.noContent().build();
    }
    
@PutMapping(value="/{id}")
public ResponseEntity<UnidadeMedida> updDepto(@Valid @PathVariable Integer id,
    @RequestBody UnidadeMedida pUnidMed) {
        UnidadeMedida atualUnidMed =
      unidmedRepository.findById(id)
            .orElseThrow(
                () -> new ObjectNotFoundException("Departamento nÃ£o encontrado!")
            );
            atualUnidMed.setNmUnidMed(pUnidMed.getNmUnidMed());
            atualUnidMed.setDescUnidMed(pUnidMed.getDescUnidMed());
            unidmedRepository.save(atualUnidMed);
        return ResponseEntity.ok().body(atualUnidMed);
}

}