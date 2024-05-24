package com.backend_senac.lanches_senac_backend.domain;

import com.backend_senac.lanches_senac_backend.enums.TipoPagamento;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
public class FormaPagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoPagamento tipoPagamento;

    @JsonIgnore
    @OneToMany(mappedBy = "formaPagamento")
    private List<Pedido> pedidos;
}
