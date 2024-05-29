package com.backend_senac.lanches_senac_backend.controllers;

import com.backend_senac.lanches_senac_backend.domain.Pedido;
import com.backend_senac.lanches_senac_backend.domain.dto.PedidoDto;
import com.backend_senac.lanches_senac_backend.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping("/{id}")
    public PedidoDto buscarPorId(@PathVariable("id") Long id) {
        return pedidoService.buscarPorId(id);
    }

    @PostMapping
    public PedidoDto salvar(@RequestBody Pedido pedido) {
        return pedidoService.salvar(pedido);
    }

    @PutMapping("/{id}")
    public PedidoDto alterar(@RequestBody Pedido pedido, @PathVariable("id") Long id) {
        return pedidoService.alterar(pedido, id);
    }

    @GetMapping("/usuario/{id}")
    public List<PedidoDto> listarPorUsuario(@PathVariable("id") Long usuarioId) {
        return pedidoService.listarPorUsuario(usuarioId);
    }

    @GetMapping("/aberto")
    public PedidoDto buscarUltimoPedidoAberto() {
        return pedidoService.buscarUltimoPedidoAberto();
    }
}
