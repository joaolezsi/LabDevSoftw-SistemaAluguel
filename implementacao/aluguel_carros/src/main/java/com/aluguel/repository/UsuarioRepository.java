package com.aluguel.repository;

import com.aluguel.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Adicionando o método para buscar por CPF
    Optional<Usuario> findByCpf(String cpf);

    // Também já existe o findByUsername para o UserDetailsService
    Optional<Usuario> findByUsername(String username);
}
