package com.backend_senac.lanches_senac_backend.services;

import com.backend_senac.lanches_senac_backend.domain.ItemPedido;
import com.backend_senac.lanches_senac_backend.domain.Pedido;
import com.backend_senac.lanches_senac_backend.domain.dto.ItemPedidoDto;
import com.backend_senac.lanches_senac_backend.domain.dto.PedidoDto;
import com.backend_senac.lanches_senac_backend.enums.StatusPedido;
import com.backend_senac.lanches_senac_backend.repositories.PedidoRepository;
import com.backend_senac.lanches_senac_backend.services.exceptions.ObjetoNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repository;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private ItemPedidoService itemPedidoService;

    public Pedido buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new ObjetoNaoEncontradoException("Pedido " + id + " não encontrado!"));
    }

    public PedidoDto salvar(Pedido pedido) {
        usuarioService.buscarPorId(pedido.getUsuario().getId());

        if(Objects.isNull(pedido.getId())) {
            pedido.setDataCriacao(LocalDate.now());
            pedido.setStatusPedido(StatusPedido.ABERTO);
        }

        Pedido pedidoSalvo = repository.save(pedido);
        return new PedidoDto(prepararPedido(pedidoSalvo));
    }

    public PedidoDto alterar(Pedido pedido, Long id) {
        buscarPorId(id);
        pedido.setId(id);
        return new PedidoDto(prepararPedido(pedido));
    }

    public List<PedidoDto> listarPorUsuario(Long usuarioId) {
        usuarioService.buscarPorId(usuarioId);
        return repository.findByUsuarioId(usuarioId).stream().map(PedidoDto::new).toList();
    }

    public PedidoDto buscarUltimoPedidoAbertoUsuario(Long usuarioId) {
        Pedido pedido = repository.findFirstByStatusPedidoAndUsuarioIdOrderByDataCriacaoDesc(StatusPedido.ABERTO, usuarioId)
                .orElseThrow(() -> new ObjetoNaoEncontradoException("Não existem pedidos abertos!"));
        return new PedidoDto(pedido);
    }

    private Pedido prepararPedido(Pedido pedido) {
        BigDecimal total = BigDecimal.ZERO;
        List<ItemPedido> itensPedido = pedido.getItensPedido();

        for (ItemPedido itemPedido : itensPedido) {
            if (itemPedido.getQuantidade() > 0) {
                itemPedido.setPedido(pedido);
                ItemPedidoDto itemSalvo = itemPedidoService.salvar(itemPedido);
                total = total.add(itemSalvo.getValor());
            } else if (Objects.nonNull(itemPedido.getId())) {
                itemPedidoService.excluir(itemPedido.getId());
            }
        }

        pedido.setValor(total);
        return repository.save(pedido);
    }
}
