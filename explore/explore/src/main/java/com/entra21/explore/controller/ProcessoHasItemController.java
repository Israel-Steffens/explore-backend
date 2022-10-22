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
import com.entra21.explore.domain.Processo;
import com.entra21.explore.domain.ProcessoHasItem;
import com.entra21.explore.exceptions.ObjectNotFoundException;
import com.entra21.explore.repositories.ItemRepository;
import com.entra21.explore.repositories.ProcessoHasItemRepository;
import com.entra21.explore.repositories.ProcessoRepository;

@CrossOrigin("*")
@RestController
@RequestMapping(value="/processohasitem")
public class ProcessoHasItemController {

  


    @Autowired
    private ProcessoHasItemRepository phiRepository;

    
    @Autowired
    private ProcessoRepository procRepository;

    
    @Autowired
    private ItemRepository iRepository;

    @GetMapping
    public ResponseEntity<List<ProcessoHasItem>> findAll() {
        List<ProcessoHasItem> processos = phiRepository.findAll();
        return ResponseEntity.ok().body(processos);
    }

    @PostMapping("/{idProcesso}/item/{idItem}")
    public ResponseEntity<ProcessoHasItem> insPHi(@Valid @PathVariable Integer idProcesso, @Valid @PathVariable Integer idItem, @Valid @RequestBody ProcessoHasItem phi  ){
        Processo proc =  procRepository.findById(idProcesso).orElseThrow(
            () -> new ObjectNotFoundException("Processo nÃo encontrado!")
        );
        Item item = iRepository.findById(idItem).orElseThrow(
            () -> new ObjectNotFoundException("Item nÃo encontrado!")
        );

        phi.setProcesso(proc);
        phi.setItem(item);

        Integer maxId = phiRepository.getMaxIdPHI();
        if(maxId == null){
            maxId = 0;
        }
        phi.setIdPHI(maxId+1);
         phiRepository.save(phi);
         URI vURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(phi.getIdPHI()).toUri();
        return ResponseEntity.created(vURI).body(phi);
    
    
    }

    
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delEstacao(@PathVariable Integer id){
    phiRepository.deleteById(id);
    return ResponseEntity.noContent().build();
}

@PutMapping(value="/{id}")
    public ResponseEntity<ProcessoHasItem> updDepto(@Valid @PathVariable Integer id,
        @RequestBody ProcessoHasItem pPHI) {
            ProcessoHasItem atualPHI =
            phiRepository.findById(id)
                .orElseThrow(
                    () -> new ObjectNotFoundException("Departamento nÃ£o encontrado!")
                );
                atualPHI.setProcesso(pPHI.getProcesso());
                atualPHI.setItem(pPHI.getItem());
                atualPHI.setQtdItem(pPHI.getQtdItem());
                phiRepository.save(atualPHI);
            return ResponseEntity.ok().body(atualPHI);
    }

}