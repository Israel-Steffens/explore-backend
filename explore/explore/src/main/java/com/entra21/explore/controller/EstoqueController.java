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

import com.entra21.explore.domain.Estoque;
import com.entra21.explore.domain.Item;
import com.entra21.explore.domain.Usuario;
import com.entra21.explore.exceptions.ObjectNotFoundException;
import com.entra21.explore.repositories.EstoqueRepository;
import com.entra21.explore.repositories.ItemRepository;
import com.entra21.explore.repositories.UsuarioRepository;


@CrossOrigin("*")
@RestController
@RequestMapping(value="/estoque")
public class EstoqueController {
    
    @Autowired
    private EstoqueRepository estoqRepository;

    @Autowired
    private ItemRepository iRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


    @GetMapping
    public ResponseEntity<List<Estoque>> findAll() {
        List<Estoque> estoques = estoqRepository.findAll();
        return ResponseEntity.ok().body(estoques);
    }

    @GetMapping(value="/nomeUsuario/{eUsuario}")
    public ResponseEntity<List<Estoque>> findAllByname(@PathVariable String eUsuario) {
        List<Estoque> estoques = estoqRepository.findAllByUsuario(eUsuario);
        return ResponseEntity.ok().body(estoques);
    }

    @GetMapping(value="/nomeItem/{eItem}")
    public ResponseEntity<List<Estoque>> findAllByitem(@PathVariable String eItem) {
        List<Estoque> estoques = estoqRepository.findAllByUsuario(eItem);
        return ResponseEntity.ok().body(estoques);
    }

    
    @PostMapping("/{idItem}/usuario/{idUsuario}")
    public ResponseEntity<Estoque> insPHi(@Valid @PathVariable Integer idItem, @Valid @PathVariable Integer idUsuario, @Valid @RequestBody Estoque pEstoque  ){
        Item item = iRepository.findById(idItem).orElseThrow(
            () -> new ObjectNotFoundException("Item nÃo encontrado!")
        );
        Usuario usuario =  usuarioRepository.findById(idUsuario).orElseThrow(
            () -> new ObjectNotFoundException("Processo nÃo encontrado!")
        );
        
        pEstoque.setItem(item);
        pEstoque.setUsuario(usuario);  
        Integer maxId = estoqRepository.getMaxIdEstoque();
        if(maxId == null){
            maxId = 0;
        }
        pEstoque.setIdEstoque(maxId+1);
        estoqRepository.save(pEstoque);
         URI vURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pEstoque.getIdEstoque()).toUri();
        return ResponseEntity.created(vURI).body(pEstoque);
    }


   

    @DeleteMapping("/{id}")
  public ResponseEntity<Void> delEstacao(@PathVariable Integer id){
    estoqRepository.deleteById(id);
    return ResponseEntity.noContent().build();
}   


@PutMapping(value="/{id}")
    public ResponseEntity<Estoque> updDepto(@Valid @PathVariable Integer id,
        @RequestBody Estoque pEstoque) {
            Estoque atualEstoque =
            estoqRepository.findById(id)
                .orElseThrow(
                    () -> new ObjectNotFoundException("Departamento nÃ£o encontrado!")
                );
                atualEstoque.setUsuario(pEstoque.getUsuario());
                atualEstoque.setItem(pEstoque.getItem());
                atualEstoque.setQtdEstoque(pEstoque.getQtdEstoque());
                atualEstoque.setQtdReservada(pEstoque.getQtdReservada());
                estoqRepository.save(atualEstoque);
            return ResponseEntity.ok().body(atualEstoque);
    }

}