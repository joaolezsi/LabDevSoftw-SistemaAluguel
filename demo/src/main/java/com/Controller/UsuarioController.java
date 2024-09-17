package com.Controller;

import com.Model.Usuario;
import com.Service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    private UsuarioService usuarioService = new UsuarioService();

    @GetMapping
    public String listarUsuarios(Model model) {
        List<Usuario> usuarios = usuarioService.listarUsuarios();
        model.addAttribute("usuarios", usuarios);
        return "usuarios/lista";  // Retorna a view com a lista de usuários
    }

    @GetMapping("/cadastrar")
    public String exibirFormularioCadastro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "usuarios/cadastro";
    }

    @PostMapping("/cadastrar")
    public String cadastrarUsuario(@ModelAttribute Usuario usuario) {
        usuarioService.cadastrarUsuario(usuario);
        return "redirect:/usuarios";
    }

    @GetMapping("/editar/{cpf}")
    public String exibirFormularioEdicao(@PathVariable String cpf, Model model) {
        Usuario usuario = usuarioService.consultarUsuarioPorCpf(cpf);
        model.addAttribute("usuario", usuario);
        return "usuarios/editar";
    }

    @PostMapping("/editar")
    public String atualizarUsuario(@ModelAttribute Usuario usuario) {
        usuarioService.atualizarUsuario(usuario);
        return "redirect:/usuarios";
    }

    @GetMapping("/remover/{cpf}")
    public String removerUsuario(@PathVariable String cpf) {
        usuarioService.removerUsuario(cpf);
        return "redirect:/usuarios";
    }
}