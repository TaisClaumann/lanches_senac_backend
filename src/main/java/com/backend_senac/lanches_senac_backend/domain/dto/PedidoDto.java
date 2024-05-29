package com.backend_senac.lanches_senac_backend.domain.dto;

import com.backend_senac.lanches_senac_backend.domain.*;
import com.backend_senac.lanches_senac_backend.enums.FormaPagamento;
import com.backend_senac.lanches_senac_backend.enums.StatusPedido;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class PedidoDto {

    private Long id;
    private LocalDate dataCriacao;
    private StatusPedido statusPedido;
    private FormaPagamento formaPagamento;
    private UsuarioDto usuario;
    private EnderecoDto endereco;
    private List<ItemPedidoDto> itensPedido;

    public PedidoDto(Pedido pedido) {
        this.id = pedido.getId();
        this.dataCriacao = pedido.getDataCriacao();
        this.statusPedido = pedido.getStatusPedido();
        this.formaPagamento = pedido.getFormaPagamento();
        this.usuario = Objects.nonNull(pedido.getUsuario()) ? new UsuarioDto(pedido.getUsuario()) : null;
        this.endereco = Objects.nonNull(pedido.getEndereco()) ? new EnderecoDto(pedido.getEndereco()) : null;
        this.itensPedido = pedido.getItensPedido().stream().map(ItemPedidoDto::new).toList();
    }
}
