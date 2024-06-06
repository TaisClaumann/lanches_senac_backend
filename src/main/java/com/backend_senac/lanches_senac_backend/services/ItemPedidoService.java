package com.backend_senac.lanches_senac_backend.services;

import com.backend_senac.lanches_senac_backend.domain.ItemPedido;
import com.backend_senac.lanches_senac_backend.domain.dto.ItemPedidoDto;
import com.backend_senac.lanches_senac_backend.domain.dto.ProdutoDto;
import com.backend_senac.lanches_senac_backend.repositories.ItemPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ItemPedidoService {

    @Autowired
    private ItemPedidoRepository repository;
    @Autowired
    private ProdutoService produtoService;

    public ItemPedidoDto salvar(ItemPedido itemPedido) {
        ProdutoDto produto = produtoService.buscarPorId(itemPedido.getProduto().getId());
        BigDecimal quantidadeItem = BigDecimal.valueOf(Long.parseLong(itemPedido.getQuantidade().toString()));

        BigDecimal totalItemPedido = produto.getValor().multiply(quantidadeItem);
        itemPedido.setValor(totalItemPedido);
        return new ItemPedidoDto(repository.save(itemPedido));
    }

    public List<ItemPedidoDto> listarTodos() {
        return repository.findAll().stream().map(ItemPedidoDto::new).toList();
    }
}
