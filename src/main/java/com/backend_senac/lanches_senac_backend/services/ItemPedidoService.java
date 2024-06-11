package com.backend_senac.lanches_senac_backend.services;

import com.backend_senac.lanches_senac_backend.domain.ItemPedido;
import com.backend_senac.lanches_senac_backend.domain.dto.ItemPedidoDto;
import com.backend_senac.lanches_senac_backend.domain.dto.ProdutoDto;
import com.backend_senac.lanches_senac_backend.repositories.ItemPedidoRepository;
import com.backend_senac.lanches_senac_backend.services.exceptions.RegistroNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemPedidoService {

    @Autowired
    private ItemPedidoRepository repository;
    @Autowired
    private ProdutoService produtoService;

    public ItemPedidoDto salvar(ItemPedido itemPedido) {
        ProdutoDto produto = produtoService.buscarPorId(itemPedido.getProduto().getId());
        itemPedido.setValor(itemPedido.getQuantidade() * produto.getValor());
        return new ItemPedidoDto(repository.save(itemPedido));
    }

    public List<ItemPedidoDto> listarTodos() {
        return repository.findAll().stream().map(ItemPedidoDto::new).toList();
    }

    public ItemPedidoDto buscarPorId(Long id) {
        ItemPedido itemPedido = repository.findById(id).orElseThrow(() -> new RegistroNaoEncontradoException("Item pedido " + id + " não encontrado!"));
        return new ItemPedidoDto(itemPedido);
    }

    public void excluir(Long id) {
        buscarPorId(id);
        repository.deleteById(id);
    }
}
