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
import com.entra21.explore.services.ProcessoService;

@CrossOrigin("*")
@RestController
@RequestMapping(value="/processo")
public class ProcessoController {

    @Autowired
    private ProcessoService pService;

    @Autowired
    private ProcessoRepository processoRepository;
    @Autowired
    private ReceitaRepository receitRepository;

    @GetMapping(value="/id/{id}")
    public ResponseEntity<List<Processo>> findAllByid(@PathVariable Integer id) {
        List<Processo> funcionarios = processoRepository.findAllByid(id);
        return ResponseEntity.ok().body(funcionarios);
    }
    @GetMapping(value="/nome/{nome}")
    public ResponseEntity<List<Processo>> findAllByname(@PathVariable String nome) {
        List<Processo> funcionarios = processoRepository.findAllByname(nome);
        return ResponseEntity.ok().body(funcionarios);
    }

    @GetMapping(value="/receita/nome/{nome}")
    public ResponseEntity<List<Processo>> findAllBynameReceita(@PathVariable String nome) {
        List<Processo> processos = processoRepository.findAllByNmReceita(nome);
        return ResponseEntity.ok().body(processos);
    }

    @GetMapping(value="/receita/id/{id}")
    public ResponseEntity<List<Processo>> findAllByIdReceita(@PathVariable Integer id) {
        List<Processo> processos = processoRepository.findAllByReceita(id);
        return ResponseEntity.ok().body(processos);
    }

    @GetMapping(value="/descricao/{descricao}")
    public ResponseEntity<List<Processo>> findAllBydesc(@PathVariable String descricao) {
        List<Processo> funcionarios = processoRepository.findAllBydesc(descricao);
        return ResponseEntity.ok().body(funcionarios);
    }
    @GetMapping(value="/processoAtivo/{processoAtivo}")
    public ResponseEntity<List<Processo>> findAllByprocessoAtivo(@PathVariable char processoAtivo) {
        List<Processo> funcionarios = processoRepository.findAllByprocessoAtivo(processoAtivo);
        return ResponseEntity.ok().body(funcionarios);
    }


    @GetMapping
    public ResponseEntity<List<Processo>> findAll() {
        List<Processo> processos = processoRepository.findAll();
        return ResponseEntity.ok().body(processos);
    }

    @PostMapping("/{idProcesso}/receita/{idReceita}")
    public ResponseEntity<Processo> insPHi(@Valid @PathVariable Integer idProcesso, @Valid @PathVariable Integer idReceita, @Valid @RequestBody Processo proc  ){
        
        
        Processo processo =  processoRepository.findById(idProcesso).orElseThrow(
            () -> new ObjectNotFoundException("Processo nÃo encontrado!")
        );

        List<Receita> receitas = processo.getProcessoReceita();
        Receita receita = receitRepository.findById(idReceita).orElseThrow(
            () -> new ObjectNotFoundException("Receita nÃo encontrado!"));


        receitas.add(receita);
        proc.setProcessoReceita(receitas);
        
        processoRepository.save(proc);
         URI vURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(proc.getProcessoReceita()).toUri();
        return ResponseEntity.created(vURI).body(proc);
    
    
    }


    @PostMapping 
    public ResponseEntity<Processo> insEstacao(@Valid @RequestBody Processo pProcesso){
        Integer maxId = processoRepository.getMaxIdProcesso();
        if(maxId == null){
            maxId = 0;
        }
        pProcesso.setIdProcesso(maxId+1); 
        processoRepository.save(pProcesso);
        URI vUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pProcesso.getIdProcesso()).toUri();
        return ResponseEntity.created(vUri).body(pProcesso);
    }
  
    


    @DeleteMapping(value = "{id_receita}/Processo/{id_processo}")
    public ResponseEntity<Void> delProjetoPessoa(@Valid @PathVariable Integer id_receita, @Valid @PathVariable Integer id_processo) {
        Receita receita = receitRepository
            .findById(id_receita)
            .orElseThrow(
                () -> new ObjectNotFoundException("Projeto "+id_receita+" não encontrado!")
            );
        List<Processo> processos = receita.getReceita_Processo();
        Processo processo = processoRepository
            .findById(id_processo)
            .orElseThrow(
                () -> new ObjectNotFoundException("Pessoa "+id_processo+" não encontrada!")
            );
            processos.remove(processo);
            receita.setReceita_Processo(processos);
        receitRepository.save(receita);
        return ResponseEntity.noContent().build();
    }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delEstacao(@PathVariable Integer id){
    pService.delProcesso(id);
    return ResponseEntity.noContent().build();
}

@PutMapping(value="/{id}")
    public ResponseEntity<Processo> updDepto(@Valid @PathVariable Integer id,
        @RequestBody Processo pProcesso) {
            Processo atualProcesso =
            processoRepository.findById(id)
                .orElseThrow(
                    () -> new ObjectNotFoundException("Departamento nÃ£o encontrado!")
                );
                atualProcesso.setNmProcesso(pProcesso.getNmProcesso());
                atualProcesso.setDescProcesso(pProcesso.getDescProcesso());
                atualProcesso.setProcessoAtivo(pProcesso.getProcessoAtivo());
                processoRepository.save(atualProcesso);
            return ResponseEntity.ok().body(atualProcesso);
    }

}