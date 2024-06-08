package com.backend_senac.lanches_senac_backend.domain;

import com.backend_senac.lanches_senac_backend.domain.dto.FuncionarioDto;
import com.backend_senac.lanches_senac_backend.enums.CargoEnum;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;

    @Enumerated(EnumType.STRING)
    private CargoEnum cargo;

    public Funcionario(FuncionarioDto funcionarioDto) {
        this.id = funcionarioDto.getId();
        this.nome = funcionarioDto.getNome();
        this.cpf = funcionarioDto.getCpf();
        this.cargo = funcionarioDto.getCargo();
    }
}
