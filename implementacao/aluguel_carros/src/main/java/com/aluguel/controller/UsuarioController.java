package com.aluguel.controller;

import com.aluguel.model.Automovel;
import com.aluguel.model.Pedido;
import com.aluguel.model.Usuario;
import com.aluguel.repository.AutomovelRepository;
import com.aluguel.repository.ContratoRepository;
import com.aluguel.repository.PedidoRepository;
import com.aluguel.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/me")
@CrossOrigin(origins = "*")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;
    private final AutomovelRepository automovelRepository;
    private final ContratoRepository contratoRepository;
    private final PedidoRepository pedidoRepository;

    private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    public UsuarioController(UsuarioRepository usuarioRepository, AutomovelRepository automovelRepository,
            ContratoRepository contratoRepository, PedidoRepository pedidoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.automovelRepository = automovelRepository;
        this.contratoRepository = contratoRepository;
        this.pedidoRepository = pedidoRepository;
    }

    @GetMapping("/info")
    public ResponseEntity<Usuario> getMe(Authentication authentication) {
        String username = authentication.getName();
        logger.info("Usuário autenticado com CPF: {}", username); // L // Modifique isso dependendo de como você obtém o
                                                                  // CPF
        Usuario usuario = usuarioRepository.findByUsername(username).orElse(null);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Usuario> updateMe(@RequestBody Usuario usuarioRequest, Authentication authentication) {
        String username = authentication.getName(); // Obtendo o CPF do usuário autenticado
        logger.info("Usuário autenticado com CPF: {}", username); // Loga o CPF

        // Busca o usuário pelo CPF
        Usuario usuario = usuarioRepository.findByUsername(username).orElse(null);

        if (usuario != null) {
            // Atualiza os dados do usuário
            usuario.setNome(usuarioRequest.getNome());
            usuario.setEndereco(usuarioRequest.getEndereco());
            usuario.setProfissao(usuarioRequest.getProfissao());
            Usuario updatedUsuario = usuarioRepository.save(usuario); // Salva as alterações no banco de dados
            return ResponseEntity.ok(updatedUsuario); // Retorna o usuário atualizado
        } else {
            return ResponseEntity.notFound().build(); // Retorna 404 se não encontrado
        }
    }

    @GetMapping("/automoveis")
    public List<Automovel> getAvailableAutomoveis() {
        return automovelRepository.findByDisponivelTrue(); // Retorna automóveis disponíveis
    }

    @PostMapping("/contratos")
    public ResponseEntity<Pedido> emitirContrato(@RequestParam String placa, Authentication authentication) {
        String cpf = authentication.getName();
        Usuario usuario = usuarioRepository.findByUsername(cpf).orElse(null);

        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }

        Automovel automovel = automovelRepository.findByPlaca(placa);
        if (automovel == null || !automovel.isDisponivel()) {
            return ResponseEntity.badRequest().build(); // Carro não encontrado ou não disponível
        }

        Pedido pedido = new Pedido();
        pedido.setUsuario(usuario);
        pedido.setAutomovel(automovel);
        pedido.setStatus("Pendente"); // Define o status como pendente
        pedido.setDataPedido(new Date());

        // Salva o pedido
        pedidoRepository.save(pedido);

        // Marcar o automóvel como indisponível
        automovel.setDisponivel(false);
        automovelRepository.save(automovel);

        return ResponseEntity.ok(pedido);
    }

}
