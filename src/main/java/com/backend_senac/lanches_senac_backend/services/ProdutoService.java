package com.backend_senac.lanches_senac_backend.services;

import com.backend_senac.lanches_senac_backend.domain.Pedido;
import com.backend_senac.lanches_senac_backend.domain.Produto;
import com.backend_senac.lanches_senac_backend.domain.dto.PedidoDto;
import com.backend_senac.lanches_senac_backend.domain.dto.ProdutoDto;
import com.backend_senac.lanches_senac_backend.repositories.ProdutoRepository;
import com.backend_senac.lanches_senac_backend.services.exceptions.ObjetoNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public ProdutoDto salvar(Produto produto) {
        return new ProdutoDto(repository.save(produto));
    }

    public List<ProdutoDto> listarTodos() {
        return repository.findAll().stream().map(ProdutoDto::new).toList();
    }

    public ProdutoDto buscarPorId(Long id) {
        Produto produto = repository.findById(id).orElseThrow(() -> new ObjetoNaoEncontradoException("Produto " + id + " não encontrado!"));
        return new ProdutoDto(produto);
    }
}
