package com.backend_senac.lanches_senac_backend.domain.dto;

import com.backend_senac.lanches_senac_backend.domain.Adicional;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class AdicionalDto {

    private Long id;
    private String nome;

    public AdicionalDto(Adicional adicional) {
        this.id = adicional.getId();
        this.nome = adicional.getNome();
    }
}
