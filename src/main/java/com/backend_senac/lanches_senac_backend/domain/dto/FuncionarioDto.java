package com.backend_senac.lanches_senac_backend.domain.dto;

import com.backend_senac.lanches_senac_backend.domain.Funcionario;
import com.backend_senac.lanches_senac_backend.enums.CargoEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class FuncionarioDto {

    private Long id;
    @NotNull
    private String nome;
    @NotNull
    private String cpf;
    @NotNull
    @Enumerated(EnumType.STRING)
    private CargoEnum cargo;

    public FuncionarioDto(Funcionario funcionario) {
        this.id = funcionario.getId();
        this.nome = funcionario.getNome();
        this.cpf = funcionario.getCpf();
        this.cargo = funcionario.getCargo();
    }
}
