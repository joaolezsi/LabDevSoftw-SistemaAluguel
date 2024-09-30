package com.aluguel.controller;

import com.aluguel.dto.AutomovelDTO;
import com.aluguel.model.Automovel;
import com.aluguel.model.Pedido;
import com.aluguel.model.Usuario;
import com.aluguel.repository.AutomovelRepository;
import com.aluguel.repository.ContratoRepository;
import com.aluguel.repository.PedidoRepository;
import com.aluguel.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    private final ContratoRepository contratoRepository;
    private final PedidoRepository pedidoRepository;
    private final AutomovelRepository automovelRepository;
    private final UsuarioRepository usuarioRepository;

    public AdminController(ContratoRepository contratoRepository, PedidoRepository pedidoRepository,
                           AutomovelRepository automovelRepository, UsuarioRepository usuarioRepository) {
        this.contratoRepository = contratoRepository;
        this.pedidoRepository = pedidoRepository;
        this.automovelRepository = automovelRepository; // Adicione este campo
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/contratos")
public ResponseEntity<List<Pedido>> getAllContratos(Authentication authentication) {
    String nome = authentication.getName(); // Obtenha o nome do usuário autenticado
    Usuario usuario = usuarioRepository.findByUsername(nome).orElse(null);

    if (usuario == null || !usuario.getRole().equals("ADMIN")) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
    
    List<Pedido> contratos = pedidoRepository.findAll(); // Lista todos os contratos (pedidos)
    return ResponseEntity.ok(contratos); // Retorna a lista encapsulada em ResponseEntity
}


    @PostMapping("/contratos/{id}/aprovar")
    public ResponseEntity<Pedido> aprovarContrato(@PathVariable Long id, Authentication authentication) {
        String nome = authentication.getName();// Obtenha o nome do usuário autenticado
        Usuario usuario = usuarioRepository.findByUsername(nome).orElse(null);

        if (usuario == null || !usuario.getRole().equals("ADMIN")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Contrato não encontrado"));
        pedido.setStatus("Aprovado"); // Altera o status para aprovado
        pedidoRepository.save(pedido);

        // Marcar o automóvel como indisponível
        Automovel automovel = pedido.getAutomovel();
        automovel.setDisponivel(false);
        automovelRepository.save(automovel);

        return ResponseEntity.ok(pedido);
    }

    
    @PostMapping("/contratos/{id}/recusar")
    public ResponseEntity<Pedido> recusarContrato(@PathVariable Long id, Authentication authentication) {
        String nome = authentication.getName();// Obtenha o nome do usuário autenticado
        Usuario usuario = usuarioRepository.findByUsername(nome).orElse(null);

        if (usuario == null || !usuario.getRole().equals("ADMIN")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Contrato não encontrado"));
        pedido.setStatus("Recusado"); // Altera o status para recusado
        pedidoRepository.save(pedido);
        return ResponseEntity.ok(pedido);
    }

    
    @PostMapping("/automoveis")
    public ResponseEntity<Automovel> addAutomovel(@RequestBody AutomovelDTO automovelDTO, Authentication authentication) {
        String nome = authentication.getName();// Obtenha o nome do usuário autenticado
        Usuario usuario = usuarioRepository.findByUsername(nome).orElse(null);

        if (usuario == null || !usuario.getRole().equals("ADMIN")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    Automovel automovel = new Automovel();
    automovel.setAno(automovelDTO.getAno());
    automovel.setMarca(automovelDTO.getMarca());
    automovel.setModelo(automovelDTO.getModelo());
    automovel.setPlaca(automovelDTO.getPlaca());
    automovel.setDisponivel(true); // Ou o que for apropriado

    Automovel savedAutomovel = automovelRepository.save(automovel);
    return ResponseEntity.ok(savedAutomovel); // Retorna o automóvel adicionado
}

@GetMapping("/automoveis")
public ResponseEntity<List<Automovel>> getAllAutomoveis(Authentication authentication) {
    String nome = authentication.getName(); // Obtenha o nome do usuário autenticado
    Usuario usuario = usuarioRepository.findByUsername(nome).orElse(null);

    if (usuario == null || !usuario.getRole().equals("ADMIN")) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
    
    List<Automovel> automoveis = automovelRepository.findAll(); // Lista todos os automóveis
    return ResponseEntity.ok(automoveis); // Retorna a lista encapsulada em ResponseEntity
}

}
