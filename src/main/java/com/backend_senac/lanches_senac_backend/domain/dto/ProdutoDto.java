package com.backend_senac.lanches_senac_backend.domain.dto;

import com.backend_senac.lanches_senac_backend.domain.Produto;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ProdutoDto {

    private Long id;
    private String nome;
    private BigDecimal valor;
    private String linkFoto;

    public ProdutoDto(Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.valor = produto.getValor();
        this.linkFoto = produto.getLinkFoto();
    }
}
