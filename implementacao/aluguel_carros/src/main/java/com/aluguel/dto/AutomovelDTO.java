package com.aluguel.dto;

import lombok.Data;

import java.util.Date;

@Data
public class AutomovelDTO {
    private Date ano;
    private String marca;
    private String modelo;
    private String placa;
}
