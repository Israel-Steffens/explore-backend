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

import com.entra21.explore.domain.Processo;
import com.entra21.explore.domain.Receita;
import com.entra21.explore.exceptions.ObjectNotFoundException;
import com.entra21.explore.repositories.ProcessoRepository;
import com.entra21.explore.repositories.ReceitaRepository;
import com.entra21.explore.services.ReceitaService;

@CrossOrigin("*")
@RestController
@RequestMapping(value="/receita")
public class ReceitaController {

    @Autowired
    private ReceitaService rService;
   
    @Autowired
    private ProcessoRepository processoRepository;

    @Autowired
    private ReceitaRepository receitaRepository;
    @GetMapping(value="/receita/{id}")
    public ResponseEntity<List<Receita>> findAllByid(@PathVariable Integer id) {
        List<Receita> receitas = receitaRepository.findAllByid(id);
        return ResponseEntity.ok().body(receitas);
    }
    @GetMapping(value="/nome/{nome}")
    public ResponseEntity<List<Receita>> findAllByname(@PathVariable String nome) {
        List<Receita> receitas = receitaRepository.findAllByname(nome);
        return ResponseEntity.ok().body(receitas);
    }
    @GetMapping(value="/descricao/{descricao}")
    public ResponseEntity<List<Receita>> findAllBydesc(@PathVariable String descricao) {
        List<Receita> receitas = receitaRepository.findAllBydesc(descricao);
        return ResponseEntity.ok().body(receitas);
    }
    @GetMapping(value="/ReceitaAtivo/{ReceitaAtivo}")  //?
    public ResponseEntity<List<Receita>> findAllByativo(@PathVariable char ReceitaAtivo) {
        List<Receita> receitas = receitaRepository.findAllByativo(ReceitaAtivo);
        return ResponseEntity.ok().body(receitas);
    }
    @GetMapping(value="/CargaMaquina/{CargaMaquina}")
    public ResponseEntity<List<Receita>> findAllByCarga(@PathVariable Double CargaMaquina) {
        List<Receita> receitas = receitaRepository.findAllBycarga(CargaMaquina);
        return ResponseEntity.ok().body(receitas);
        
    }

    @GetMapping(value="/processo/nome/{nome}")
    public ResponseEntity<List<Receita>> findAllBynameProcesso(@PathVariable String nome) {
        List<Receita> receitas = receitaRepository.findAllByNmProcesso(nome);
        return ResponseEntity.ok().body(receitas);
    }

    @GetMapping(value="/processo/id/{id}")
    public ResponseEntity<List<Receita>> findAllByIdProcesso(@PathVariable Integer id) {
        List<Receita> receitas = receitaRepository.findAllByProcesso(id);
        return ResponseEntity.ok().body(receitas);
    }


    @GetMapping
    public ResponseEntity<List<Receita>> findAll() {
      List<Receita> receitas = receitaRepository.findAll();
        return ResponseEntity.ok().body(receitas);
}
@PostMapping("/{idReceita}/processo/{idProcesso}")
public ResponseEntity<Receita> insPHi(@Valid @PathVariable Integer idReceita, @Valid @PathVariable Integer idProcesso, @Valid @RequestBody Receita pReceita  ){
   
    
    Receita receitas  = receitaRepository.findById(idReceita).orElseThrow(
        () -> new ObjectNotFoundException("Receita nÃo encontrado!"));
    List<Processo> processos = receitas.getReceita_Processo();
    Processo processo =  processoRepository.findById(idProcesso).orElseThrow(
            () -> new ObjectNotFoundException("Processo nÃo encontrado!")
        );
    

        processos.add(processo);
        pReceita.setReceita_Processo(processos);
    
        receitaRepository.save(pReceita);
     URI vURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pReceita.getReceita_Processo()).toUri();
    return ResponseEntity.created(vURI).body(pReceita);


}
@PostMapping 
public ResponseEntity<Receita> insEstacao(@Valid @RequestBody Receita pReceita){
    Integer maxId = receitaRepository.getMaxIdReceita();
    if(maxId == null){
        maxId = 0;
    }
    pReceita.setIdReceita(maxId+1);

    receitaRepository.save(pReceita);
    URI vUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pReceita.getIdReceita()).toUri();
    return ResponseEntity.created(vUri).body(pReceita);
}


  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delEstacao(@PathVariable Integer id){
    rService.delReceita(id);
    return ResponseEntity.noContent().build();
}

@PutMapping(value="/{id}")
    public ResponseEntity<Receita> updDepto(@Valid @PathVariable Integer id,
        @RequestBody Receita pReceita) {
          Receita atualReceita =
            receitaRepository.findById(id)
                .orElseThrow(
                    () -> new ObjectNotFoundException("Departamento nÃ£o encontrado!")
                );
                atualReceita.setNmReceita(pReceita.getNmReceita());
                atualReceita.setDescReceita(pReceita.getDescReceita());
                atualReceita.setReceitaAtiva(pReceita.getReceitaAtiva());
                atualReceita.setCargaMaquina(pReceita.getCargaMaquina());
                receitaRepository.save(atualReceita);
            return ResponseEntity.ok().body(atualReceita);
    }
}