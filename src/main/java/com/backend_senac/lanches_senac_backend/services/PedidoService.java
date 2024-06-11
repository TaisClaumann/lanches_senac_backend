package com.backend_senac.lanches_senac_backend.services;

import com.backend_senac.lanches_senac_backend.domain.ItemPedido;
import com.backend_senac.lanches_senac_backend.domain.Pedido;
import com.backend_senac.lanches_senac_backend.domain.dto.ItemPedidoDto;
import com.backend_senac.lanches_senac_backend.domain.dto.PedidoDto;
import com.backend_senac.lanches_senac_backend.enums.StatusPedidoEnum;
import com.backend_senac.lanches_senac_backend.repositories.PedidoRepository;
import com.backend_senac.lanches_senac_backend.services.exceptions.RegistroNaoEncontradoException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class PedidoService {

    private final PedidoRepository repository;
    private final UsuarioService usuarioService;
    private final ItemPedidoService itemPedidoService;

    public PedidoService(PedidoRepository repository, UsuarioService usuarioService, ItemPedidoService itemPedidoService) {
        this.repository = repository;
        this.usuarioService = usuarioService;
        this.itemPedidoService = itemPedidoService;
    }

    public Pedido buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new RegistroNaoEncontradoException("Pedido " + id + " não encontrado!"));
    }

    public PedidoDto salvar(Pedido pedido) {
        usuarioService.buscarPorId(pedido.getUsuario().getId());

        if(Objects.isNull(pedido.getId())) {
            pedido.setDataCriacao(LocalDate.now());
            pedido.setStatusPedido(StatusPedidoEnum.ABERTO);
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
        Pedido pedido = repository.findFirstByStatusPedidoAndUsuarioIdOrderByDataCriacaoDesc(StatusPedidoEnum.ABERTO, usuarioId)
                .orElseThrow(() -> new RegistroNaoEncontradoException("Não existem pedidos abertos!"));
        return new PedidoDto(pedido);
    }

    private Pedido prepararPedido(Pedido pedido) {
        Double total = 0.0;
        List<ItemPedido> itensPedido = pedido.getItensPedido();

        for (ItemPedido itemPedido : itensPedido) {
            if (itemPedido.getQuantidade() > 0) {
                itemPedido.setPedido(pedido);
                ItemPedidoDto itemSalvo = itemPedidoService.salvar(itemPedido);
                total += itemSalvo.getValor();
            } else if (Objects.nonNull(itemPedido.getId())) {
                itemPedidoService.excluir(itemPedido.getId());
            }
        }

        pedido.setValor(total);
        return repository.save(pedido);
    }
}
