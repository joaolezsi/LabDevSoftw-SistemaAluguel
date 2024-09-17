package com.Service;

import com.Model.Usuario;
import com.Repository.UsuarioRepository;
import java.util.List;

public class UsuarioService {

    private UsuarioRepository usuarioRepository = new UsuarioRepository();

    public void cadastrarUsuario(Usuario usuario) {
        usuarioRepository.cadastrarUsuario(usuario);
    }

    public Usuario consultarUsuarioPorCpf(String cpf) {
        return usuarioRepository.consultarUsuarioPorCpf(cpf);
    }

    public void atualizarUsuario(Usuario usuario) {
        usuarioRepository.atualizarUsuario(usuario);
    }

    public void removerUsuario(String cpf) {
        usuarioRepository.removerUsuario(cpf);
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.listarUsuarios();
    }
}
