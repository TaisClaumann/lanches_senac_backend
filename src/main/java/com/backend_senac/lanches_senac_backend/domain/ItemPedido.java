package com.backend_senac.lanches_senac_backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantidade;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH)
    private Pedido pedido;
    @ManyToOne(cascade = CascadeType.REFRESH)
    private Produto produto;
}
