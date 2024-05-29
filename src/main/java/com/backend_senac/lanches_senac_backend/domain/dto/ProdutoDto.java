package com.backend_senac.lanches_senac_backend.domain.dto;

import com.backend_senac.lanches_senac_backend.domain.Produto;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ProdutoDto {

    private Long id;
    private String nome;
    private String valor;

    public ProdutoDto(Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.valor = produto.getValor();
    }
}
