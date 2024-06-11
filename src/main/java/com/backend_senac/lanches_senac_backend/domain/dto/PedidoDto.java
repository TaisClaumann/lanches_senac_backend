package com.backend_senac.lanches_senac_backend.domain.dto;

import com.backend_senac.lanches_senac_backend.domain.Pedido;
import com.backend_senac.lanches_senac_backend.enums.FormaPagamentoEnum;
import com.backend_senac.lanches_senac_backend.enums.StatusPedidoEnum;
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
    private StatusPedidoEnum statusPedido;
    private FormaPagamentoEnum formaPagamento;
    private UsuarioDto usuario;
    private EnderecoDto endereco;
    private List<ItemPedidoDto> itensPedido;
    private Double valor;

    public PedidoDto(Pedido pedido) {
        this.id = pedido.getId();
        this.dataCriacao = pedido.getDataCriacao();
        this.statusPedido = pedido.getStatusPedido();
        this.formaPagamento = pedido.getFormaPagamento();
        this.usuario = Objects.nonNull(pedido.getUsuario()) ? new UsuarioDto(pedido.getUsuario()) : null;
        this.endereco = Objects.nonNull(pedido.getEndereco()) ? new EnderecoDto(pedido.getEndereco()) : null;
        this.itensPedido = pedido.getItensPedido().stream().map(ItemPedidoDto::new).toList();
        this.valor = pedido.getValor();
    }
}
