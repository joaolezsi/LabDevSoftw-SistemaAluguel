package com.Repository;

import com.Model.Usuario;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepository {

    private List<Usuario> usuarios = new ArrayList<>();

    public void cadastrarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public Usuario consultarUsuarioPorCpf(String cpf) {
        return usuarios.stream().filter(u -> u.getCpf().equals(cpf)).findFirst().orElse(null);
    }

    public void atualizarUsuario(Usuario usuarioAtualizado) {
        Usuario usuario = consultarUsuarioPorCpf(usuarioAtualizado.getCpf());
        if (usuario != null) {
            usuario.setNome(usuarioAtualizado.getNome());
            usuario.setEndereco(usuarioAtualizado.getEndereco());
            usuario.setProfissao(usuarioAtualizado.getProfissao());
        }
    }

    public void removerUsuario(String cpf) {
        usuarios.removeIf(u -> u.getCpf().equals(cpf));
    }

    public List<Usuario> listarUsuarios() {
        return usuarios;
    }
}
