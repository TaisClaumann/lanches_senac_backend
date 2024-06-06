package com.backend_senac.lanches_senac_backend.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal valor;
    private String linkFoto;

    @JsonIgnore
    @OneToMany(mappedBy = "produto")
    private List<ItemPedido> itensPedido;
}
