package com.backend_senac.lanches_senac_backend.services;

import com.backend_senac.lanches_senac_backend.domain.Usuario;
import com.backend_senac.lanches_senac_backend.repositories.UsuarioRepository;
import com.backend_senac.lanches_senac_backend.services.exceptions.ObjetoNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public Usuario salvar(Usuario usuario) {
        return repository.save(usuario);
    }

    public Usuario buscarPorId(Long id){
        return repository.findById(id).orElseThrow(() -> new ObjetoNaoEncontradoException("Usuário não encontrado! ID: " + id));
    }

    public Usuario atualizar(Long id, Usuario usuario){
        buscarPorId(id);
        usuario.setId(id);
        return repository.save(usuario);
    }
}
