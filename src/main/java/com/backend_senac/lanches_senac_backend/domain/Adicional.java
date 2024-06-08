package com.backend_senac.lanches_senac_backend.domain;

import com.backend_senac.lanches_senac_backend.domain.dto.AdicionalDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
public class Adicional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    public Adicional(AdicionalDto adicionalDto) {
        this.nome = adicionalDto.getNome();
    }
}
