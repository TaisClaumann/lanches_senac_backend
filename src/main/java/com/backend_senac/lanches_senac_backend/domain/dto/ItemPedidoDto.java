package com.backend_senac.lanches_senac_backend.domain.dto;

import com.backend_senac.lanches_senac_backend.domain.ItemPedido;
import lombok.*;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ItemPedidoDto {

    private Long id;
    private Integer quantidade;
    private ProdutoDto produto;
    private Double valor;

    public ItemPedidoDto(ItemPedido itemPedido) {
        this.id = itemPedido.getId();
        this.quantidade = itemPedido.getQuantidade();
        this.produto = Objects.nonNull(itemPedido.getProduto()) ? new ProdutoDto(itemPedido.getProduto()) : null;
        this.valor = itemPedido.getValor();
    }
}
