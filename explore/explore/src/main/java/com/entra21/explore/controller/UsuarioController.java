package com.entra21.explore.controller;

import java.net.URI;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.entra21.explore.domain.Usuario;
import com.entra21.explore.exceptions.ObjectNotFoundException;
import com.entra21.explore.repositories.UsuarioRepository;
import com.entra21.explore.services.UsuarioService;


@CrossOrigin("*")
@RestController
@RequestMapping(value="/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;


    @Autowired
    private UsuarioService uService;

    @GetMapping(value="/id/{id}")
    public ResponseEntity<List<Usuario>> findAllByid(@PathVariable Integer id) {
        List<Usuario> funcionarios = usuarioRepository.findAllByid(id);
        return ResponseEntity.ok().body(funcionarios);
    }

    @GetMapping(value="/nome/{nome}")
    public ResponseEntity<List<Usuario>> findAllByname(@PathVariable String nome) {
        List<Usuario> funcionarios = usuarioRepository.findAllByname(nome);
        return ResponseEntity.ok().body(funcionarios);
    }
   
    @GetMapping
    public ResponseEntity<List<Usuario>> findAll() {
        List<Usuario> unidmeds = usuarioRepository.findAll();
        return ResponseEntity.ok().body(unidmeds);
    } 

    


    @PostMapping 
    public ResponseEntity<Usuario> insEstacao(@Valid @RequestBody Usuario pUsuario){
        Integer maxId = usuarioRepository.getMaxIdUsuario();
        if(maxId == null){
            maxId = 0;
        }
        pUsuario.setIdUsuario(maxId+1); 
        usuarioRepository.save(pUsuario);
        URI vUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pUsuario.getIdUsuario()).toUri();
        return ResponseEntity.created(vUri).body(pUsuario);
    }

    //Dentro de um @RestController
//Estrutura do JSON enviado:
// { "login" : "tiago", "senha" : "batata" }

@PostMapping(value = "/geraJSON", produces = "application/json")
@ResponseBody
public String retornaJSON(@RequestBody Map<String, Object> inputJSON) {
    String retorno  = "";
    if (uService.validaUsuario(inputJSON.get("login").toString(), inputJSON.get("senha").toString())) {
        retorno = "true";
    } else {
        retorno = "false";
    }
    return "{ \"mensagem\": "+retorno+" }";
}
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delEstacao(@PathVariable Integer id){
        uService.delUsuario(id);
        return ResponseEntity.noContent().build();
    }

      
@PutMapping(value="/{id}")
public ResponseEntity<Usuario> updDepto(@Valid @PathVariable Integer id,
    @RequestBody Usuario pUsuario) {
        Usuario atualUsuario =
        usuarioRepository.findById(id)
            .orElseThrow(
                () -> new ObjectNotFoundException("Departamento nÃ£o encontrado!")
            );
            atualUsuario.setNmUsuario(pUsuario.getNmUsuario());
            atualUsuario.setCpf(pUsuario.getCpf());
            atualUsuario.setEmail(pUsuario.getEmail());
            atualUsuario.setTelefone(pUsuario.getTelefone());
            atualUsuario.setSenha(pUsuario.getSenha());
            atualUsuario.setGenero(pUsuario.getGenero());
            usuarioRepository.save(atualUsuario);
        return ResponseEntity.ok().body(atualUsuario);
}

}