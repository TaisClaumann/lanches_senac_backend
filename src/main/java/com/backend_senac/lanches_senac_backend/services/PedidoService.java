package com.backend_senac.lanches_senac_backend.services;

import com.backend_senac.lanches_senac_backend.domain.Pedido;
import com.backend_senac.lanches_senac_backend.repositories.PedidoRepository;
import com.backend_senac.lanches_senac_backend.services.exceptions.ObjetoNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private ItemPedidoService itemPedidoService;

    public Pedido buscarPorId(Long id) {
        return pedidoRepository.findById(id).orElseThrow(() -> new ObjetoNaoEncontradoException("Pedido " + id + " não encontrado!"));
    }

    public Pedido salvar(Pedido pedido) {
        Pedido pedidoSalvo = pedidoRepository.save(pedido);
        pedido.getItensPedido().stream()
                .filter(itemPedido -> Objects.isNull(itemPedido.getId()))
                .forEach(itemPedido -> {
                    itemPedido.setPedido(pedidoSalvo);
                    itemPedidoService.salvar(itemPedido);
                });
        return pedidoSalvo;
    }

    public Pedido alterar(Pedido pedido, Long id) {
        buscarPorId(id);
        pedido.setId(id);
        return salvar(pedido);
    }

    public List<Pedido> listarPorUsuario(Long usuarioId) {
        usuarioService.buscarPorId(usuarioId);
        return pedidoRepository.findByUsuarioId(usuarioId);
    }
}
