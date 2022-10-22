package com.entra21.explore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entra21.explore.domain.Usuario;
import com.entra21.explore.exceptions.DataIntegrityViolationException;
import com.entra21.explore.repositories.UsuarioRepository;


@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

//Dentro de um @service
public boolean validaUsuario(String pUsuario, String pSenha) {
    //Verifica se pSenha Ã© a correta de pUsuario
    Usuario usua = usuarioRepository.findByName(pUsuario);
    if(pUsuario == usua.getNmUsuario() && pSenha ==  usua.getSenha()){
        return true;
    }else{
    return false;
    }
}
public void delUsuario(Integer pId) {
    try {
        usuarioRepository.deleteById(pId);
    } catch (org.springframework.dao.DataIntegrityViolationException e) {
        throw new DataIntegrityViolationException("Usuario não pode ser deletado!");
    }
}
}