package com.aluguel.controller;

import com.aluguel.model.Pedido;
import com.aluguel.model.Usuario;
import com.aluguel.repository.PedidoRepository;
import com.aluguel.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "*") 
public class PedidoController {

    private final PedidoRepository pedidoRepository;
    private final UsuarioRepository usuarioRepository;

    public PedidoController(PedidoRepository pedidoRepository, UsuarioRepository usuarioRepository) {
        this.pedidoRepository = pedidoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/me")
    public ResponseEntity<List<Pedido>> getMePedidos(Authentication authentication) {
        String cpf = authentication.getName(); // Obtém o CPF do usuário autenticado
        Usuario usuario = usuarioRepository.findByUsername(cpf).orElse(null);
        if (usuario != null) {
            List<Pedido> pedidos = pedidoRepository.findByUsuario_UserId(usuario.getUserId());
            return ResponseEntity.ok(pedidos);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/me/{pedidoId}")
    public ResponseEntity<Pedido> updateMePedido(@PathVariable Long pedidoId, @RequestBody Pedido pedidoRequest, Authentication authentication) {
        String cpf = authentication.getName();
        Usuario usuario = usuarioRepository.findByUsername(cpf).orElse(null);
        if (usuario != null) {
            Pedido pedido = pedidoRepository.findById(pedidoId).orElse(null);
            if (pedido != null && pedido.getUsuario().getUserId().equals(usuario.getUserId())) {
                pedido.setStatus(pedidoRequest.getStatus());
                pedido.setDuracaoContratoMeses(pedidoRequest.getDuracaoContratoMeses());
                pedido.setCompraVeiculoFinal(pedidoRequest.isCompraVeiculoFinal());
                Pedido updatedPedido = pedidoRepository.save(pedido);
                return ResponseEntity.ok(updatedPedido);
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Usuário não tem permissão para modificar este pedido
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
