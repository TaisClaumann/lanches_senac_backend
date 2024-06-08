package com.backend_senac.lanches_senac_backend.controllers;

import com.backend_senac.lanches_senac_backend.domain.dto.FuncionarioDto;
import com.backend_senac.lanches_senac_backend.services.FuncionarioService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/funcionarios")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    @PostMapping
    public void salvar(@RequestBody FuncionarioDto funcionarioDto) {
        funcionarioService.salvar(funcionarioDto);
    }

    @PutMapping("/{id}")
    public void alterar(@PathVariable("id") Long id, @RequestBody FuncionarioDto funcionarioDto) {
        funcionarioService.alterar(id, funcionarioDto);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable("id") @NotNull Long id) {
        funcionarioService.excluir(id);
    }

    @GetMapping
    public List<FuncionarioDto> listarTodos() {
        return funcionarioService.listarTodos();
    }
}
