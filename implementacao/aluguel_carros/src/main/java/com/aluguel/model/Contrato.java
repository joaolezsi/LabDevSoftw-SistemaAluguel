package com.aluguel.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "contrato")
public class Contrato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contrato_id")
    private Long contratoId;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "banco_id")
    private Agente banco;

    @Column(name = "data_inicio")
    private Date dataInicio;

    @Column(name = "data_fim")
    private Date dataFim;

    @Column(name = "valor_mensal")
    private Double valorMensal;
}
