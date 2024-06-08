package com.backend_senac.lanches_senac_backend.controllers;

import com.backend_senac.lanches_senac_backend.domain.dto.AdicionalDto;
import com.backend_senac.lanches_senac_backend.services.AdicionalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adicionais")
@RequiredArgsConstructor
public class AdicionalController {

    private final AdicionalService adicionalService;

    @PostMapping()
    public AdicionalDto salvar(@RequestBody AdicionalDto adicionalDto) {
        return new AdicionalDto(adicionalService.salvar(adicionalDto));
    }

    @GetMapping()
    public List<AdicionalDto> listarTodos() {
        return adicionalService.listarTodos();
    }

    @GetMapping("/{nome}")
    public AdicionalDto buscarPorNome(@PathVariable("nome") String nome) {
        return new AdicionalDto(adicionalService.buscarPorNome(nome));
    }

    @DeleteMapping("/{nome}")
    public void excluir(@PathVariable("nome") String nome) {
        adicionalService.excluir(nome);
    }

    @PutMapping("/{nome}")
    public AdicionalDto atualizar(@PathVariable("nome") String nome, @RequestBody AdicionalDto adicionalDto) {
        return new AdicionalDto(adicionalService.atualizar(nome, adicionalDto));
    }
}
