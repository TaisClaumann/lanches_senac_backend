package com.backend_senac.lanches_senac_backend.repositories;

import com.backend_senac.lanches_senac_backend.domain.Produto;
import com.backend_senac.lanches_senac_backend.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>  {
}
