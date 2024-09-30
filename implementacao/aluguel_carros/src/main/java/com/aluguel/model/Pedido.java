package com.aluguel.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pedido_id")
    private Long pedidoId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "automovel_id")
    private Automovel automovel;

    @Column(name = "data_pedido")
    private Date dataPedido;

    @Column(name = "status", nullable = false)
    private String status; // Aberto, Cancelado, Em Análise, Aprovado

    @Column(name = "duracao_contrato_meses")
    private int duracaoContratoMeses; // 12, 24, 36 ou 48 meses

    @Column(name = "compra_veiculo_final")
    private boolean compraVeiculoFinal; // opção de compra ao final do contrato
}
