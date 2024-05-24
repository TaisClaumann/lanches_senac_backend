package com.backend_senac.lanches_senac_backend.services;

import com.backend_senac.lanches_senac_backend.domain.Usuario;
import com.backend_senac.lanches_senac_backend.domain.dto.UsuarioDto;
import com.backend_senac.lanches_senac_backend.repositories.UsuarioRepository;
import com.backend_senac.lanches_senac_backend.services.exceptions.ObjetoNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public UsuarioDto salvar(Usuario usuario) {
        return new UsuarioDto(repository.save(usuario));
    }

    public UsuarioDto buscarPorId(Long id){
        return new UsuarioDto(repository.findById(id).orElseThrow(() -> new ObjetoNaoEncontradoException("Usuário não encontrado! ID: " + id)));
    }

    public UsuarioDto atualizar(Long id, Usuario usuario){
        buscarPorId(id);
        usuario.setId(id);
        return new UsuarioDto(repository.save(usuario));
    }
}
