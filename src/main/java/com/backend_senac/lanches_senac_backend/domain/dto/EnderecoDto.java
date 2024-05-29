package com.backend_senac.lanches_senac_backend.domain.dto;

import com.backend_senac.lanches_senac_backend.domain.Endereco;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class EnderecoDto {

    private Long id;
    private String rua;
    private String numero;
    private String bairro;
    private String cep;
    private String cidade;

    public EnderecoDto(Endereco endereco) {
        this.id = endereco.getId();
        this.rua = endereco.getRua();
        this.numero = endereco.getNumero();
        this.bairro = endereco.getBairro();
        this.cep = endereco.getCep();
        this.cidade = endereco.getCidade();
    }
}
