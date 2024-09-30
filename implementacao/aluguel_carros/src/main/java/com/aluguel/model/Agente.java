package com.aluguel.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "agente")
public class Agente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "agente_id")
    private Long agenteId;

    @Column(name = "nome")
    private String nome;

    @Column(name = "tipo") // Empresa ou Banco
    private String tipo;
}
