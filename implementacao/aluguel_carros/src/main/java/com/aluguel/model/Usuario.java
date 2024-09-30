package com.aluguel.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(nullable = false, unique = true, length = 20)
    private String username;

    @Column(nullable = false)
    private String password; // Senha armazenada de forma segura (usando hash)

    @Column(nullable = false, unique = true, length = 20) 
    private String rg;

    @Column(nullable = false, unique = true, length = 11)
    private String cpf;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 150)
    private String endereco;

    @Column(nullable = false, length = 100)
    private String profissao;

    @Column(nullable = false)
    private String role; 
}
