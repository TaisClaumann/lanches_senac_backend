package com.backend_senac.lanches_senac_backend.controllers;

import com.backend_senac.lanches_senac_backend.domain.Produto;
import com.backend_senac.lanches_senac_backend.domain.dto.ProdutoDto;
import com.backend_senac.lanches_senac_backend.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public ProdutoDto salvar(@RequestBody Produto produto) {
        return produtoService.salvar(produto);
    }

    @GetMapping
    public List<ProdutoDto> listarTodos() {
        return produtoService.listarTodos();
    }
}
