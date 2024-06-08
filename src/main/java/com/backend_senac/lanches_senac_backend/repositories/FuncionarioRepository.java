package com.backend_senac.lanches_senac_backend.repositories;

import com.backend_senac.lanches_senac_backend.domain.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    @Query("select (count(f) > 0) from Funcionario f where upper(f.nome) = upper(:nome)")
    boolean existsByNomeIgnoreCase(@Param("nome") String nome);

    boolean existsByCpf(String cpf);
}
