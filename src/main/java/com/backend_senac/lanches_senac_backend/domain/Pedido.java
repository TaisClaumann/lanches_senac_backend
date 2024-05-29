package com.backend_senac.lanches_senac_backend.domain;

import com.backend_senac.lanches_senac_backend.enums.FormaPagamento;
import com.backend_senac.lanches_senac_backend.enums.StatusPedido;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dataCriacao;

    @Enumerated(EnumType.STRING)
    private StatusPedido statusPedido;
    @Enumerated(EnumType.STRING)
    private FormaPagamento formaPagamento;

    @ManyToOne(cascade = CascadeType.REFRESH)
    private Usuario usuario;
    @ManyToOne(cascade = CascadeType.REFRESH)
    private Endereco endereco;

    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itensPedido;
}
