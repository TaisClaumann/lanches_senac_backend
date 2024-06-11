package com.backend_senac.lanches_senac_backend.mocks;

import com.backend_senac.lanches_senac_backend.domain.ItemPedido;
import com.backend_senac.lanches_senac_backend.domain.Produto;
import com.backend_senac.lanches_senac_backend.domain.Usuario;
import com.backend_senac.lanches_senac_backend.domain.dto.ProdutoDto;
import com.backend_senac.lanches_senac_backend.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MockFactory {

    @Autowired
    private ProdutoService produtoService;

    public Usuario criarUsuario(String login, String cpf) {
        return Usuario.builder()
                .cpf(cpf)
                .login(login)
                .senha("1234")
                .build();
    }

    public ItemPedido criarItemPedido(Double valorProduto, Integer quantidade) {
        return ItemPedido.builder()
                .produto(Produto.builder().id(criarProduto(valorProduto).getId()).build())
                .quantidade(quantidade)
                .build();
    }

    public ProdutoDto criarProduto(Double valor) {
        return produtoService.salvar(Produto.builder()
                .valor(valor)
                .nome("Pizza")
                .build());
    }
}
