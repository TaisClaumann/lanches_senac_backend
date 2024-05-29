package com.backend_senac.lanches_senac_backend.services;

import com.backend_senac.lanches_senac_backend.domain.ItemPedido;
import com.backend_senac.lanches_senac_backend.domain.Pedido;
import com.backend_senac.lanches_senac_backend.domain.dto.ItemPedidoDto;
import com.backend_senac.lanches_senac_backend.repositories.ItemPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ItemPedidoService {

    @Autowired
    private ItemPedidoRepository repository;

    public ItemPedidoDto salvar(ItemPedido itemPedido) {
        return new ItemPedidoDto(repository.save(itemPedido));
    }

    public List<ItemPedidoDto> listarTodos() {
        return repository.findAll().stream().map(ItemPedidoDto::new).toList();
    }
}
