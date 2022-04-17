package com.app.deliveryapp.domain.repository;

import com.app.deliveryapp.domain.model.Cozinha;
import com.app.deliveryapp.domain.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {
}
