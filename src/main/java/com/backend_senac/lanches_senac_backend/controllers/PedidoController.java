package com.backend_senac.lanches_senac_backend.controllers;

import com.backend_senac.lanches_senac_backend.domain.Pedido;
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
    public Pedido buscarPorId(@PathVariable("id") Long id) {
        return pedidoService.buscarPorId(id);
    }

    @PostMapping
    public Pedido salvar(@RequestBody Pedido pedido) {
        return pedidoService.salvar(pedido);
    }

    @PutMapping("/{id}")
    public Pedido alterar(@RequestBody Pedido pedido, @PathVariable("id") Long id) {
        return pedidoService.alterar(pedido, id);
    }

    @GetMapping("/usuario/{id}")
    public List<Pedido> listarPorUsuario(@PathVariable("id") Long usuarioId) {
        return pedidoService.listarPorUsuario(usuarioId);
    }
}
