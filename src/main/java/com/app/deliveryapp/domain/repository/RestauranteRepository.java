package com.app.deliveryapp.domain.repository;

import com.app.deliveryapp.domain.model.Cozinha;
import com.app.deliveryapp.domain.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {

    //verifica res com uma taxa dentro desse intervalo
    List<Restaurante> findByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);

    //nesse metodo criado por nos mesmo nele dispomos de estrategias para cria minha propia consulta
    @Query("from Restaurante  where nome like  %:buscado%")
    List<Restaurante> buscarPorNome(@Param("buscado") String nome);

}
