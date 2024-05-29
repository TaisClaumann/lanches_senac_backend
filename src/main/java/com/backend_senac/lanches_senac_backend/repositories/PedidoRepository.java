package com.backend_senac.lanches_senac_backend.repositories;

import com.backend_senac.lanches_senac_backend.domain.Pedido;
import com.backend_senac.lanches_senac_backend.enums.StatusPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>  {

    List<Pedido> findByUsuarioId(Long usuarioId);
    Optional<Pedido> findFirstByStatusPedidoAndUsuarioIdOrderByDataCriacaoDesc(StatusPedido statusPedido, Long usuarioId);
}
