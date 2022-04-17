package com.app.deliveryapp.infrastructure.repository;


import com.app.deliveryapp.domain.model.Restaurante;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.List;

@Repository
public class RestauranteRepositoryImpl {

    @PersistenceContext
    private EntityManager manager;

    public List<Restaurante> find(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal){
        return manager.createNativeQuery(
               "select *" +
                       "from restaurante" +
                       "where nome like :nome" +
                       "and taxaFrete between :taxaInicial and :taxaFinal"


                ,Restaurante.class
        ).getResultList();
    }
}
