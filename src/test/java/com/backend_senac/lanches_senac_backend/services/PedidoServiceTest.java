package com.backend_senac.lanches_senac_backend.services;

import com.backend_senac.lanches_senac_backend.domain.ItemPedido;
import com.backend_senac.lanches_senac_backend.domain.Pedido;
import com.backend_senac.lanches_senac_backend.domain.Usuario;
import com.backend_senac.lanches_senac_backend.domain.dto.PedidoDto;
import com.backend_senac.lanches_senac_backend.domain.dto.UsuarioDto;
import com.backend_senac.lanches_senac_backend.enums.FormaPagamentoEnum;
import com.backend_senac.lanches_senac_backend.mocks.MockFactory;
import com.backend_senac.lanches_senac_backend.services.exceptions.RegistroNaoEncontradoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class PedidoServiceTest {

    @Autowired
    private PedidoService pedidoService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private MockFactory mockFactory;
    private Pedido pedido;
    private PedidoDto pedidoRetorno;

    @Nested
    class Dado_um_pedido {

        @BeforeEach
        void setUp() {
            UsuarioDto usuarioDto = usuarioService.salvar(mockFactory.criarUsuario("teste", "145.079.470-06"));
            ItemPedido itemPedido = mockFactory.criarItemPedido(10.0, 1);

            pedido = Pedido.builder()
                    .formaPagamento(FormaPagamentoEnum.PIX)
                    .usuario(Usuario.builder().id(usuarioDto.getId()).build())
                    .itensPedido(Collections.singletonList(itemPedido))
                    .build();
        }

        @Nested
        class Quando_salvar {

            @Nested
            class Quando_usuario_inexistente {

                @BeforeEach
                void setUp() {
                    pedido.setUsuario(mockFactory.criarUsuario("teste", "981.970.940-71"));
                }

                @Test
                void Entao_deve_gerar_erro() {
                    assertThrows(RegistroNaoEncontradoException.class, () -> pedidoService.salvar(pedido));
                }
            }
        }
    }
}
