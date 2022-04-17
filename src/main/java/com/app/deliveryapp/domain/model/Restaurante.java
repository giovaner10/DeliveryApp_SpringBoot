package com.app.deliveryapp.domain.model;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Restaurante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull
    private String nome;

    @NotNull
    @Column(name = "taxa_frete")
    private BigDecimal taxaFrete;

    @ManyToOne
    @JoinColumn(name = "id_cozinha")
    @NotNull
    private Cozinha cozinha;
}
