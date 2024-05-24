package com.backend_senac.lanches_senac_backend.controllers;

import com.backend_senac.lanches_senac_backend.domain.Usuario;
import com.backend_senac.lanches_senac_backend.domain.dto.UsuarioDto;
import com.backend_senac.lanches_senac_backend.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioDto> salvar(@RequestBody Usuario usuario) {
        return ResponseEntity.ok().body(usuarioService.salvar(usuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDto> atualizar(@PathVariable("id") Long id, @RequestBody Usuario usuario) {
        return ResponseEntity.ok().body(usuarioService.atualizar(id, usuario));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDto> buscarPorId(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(usuarioService.buscarPorId(id));
    }
}
