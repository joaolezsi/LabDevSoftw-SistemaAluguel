package com.aluguel.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@Entity
@Table(name = "automovel")
public class Automovel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "matricula")
    private Long matricula;

    @Temporal(TemporalType.DATE)
    @Column(name = "ano")
    private Date ano;

    @Column(name = "marca")
    private String marca;

    @Column(name = "modelo")
    private String modelo;

    @Column(name = "placa")
    private String placa;

    @JsonIgnore // Ignora na serialização
    @ManyToOne
    @JoinColumn(name = "contrato_id")
    private Contrato contrato;

    @Column(name = "disponivel")
    private boolean disponivel = true; // Para controlar a disponibilidade do automóvel
}

