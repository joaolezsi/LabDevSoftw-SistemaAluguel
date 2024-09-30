package com.aluguel.repository;

import com.aluguel.model.Automovel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutomovelRepository extends JpaRepository<Automovel, Long> {
    List<Automovel> findByDisponivelTrue(); // Para listar automóveis disponíveis
    Automovel findByPlaca(String placa); // Para buscar um automóvel pela placa
}
