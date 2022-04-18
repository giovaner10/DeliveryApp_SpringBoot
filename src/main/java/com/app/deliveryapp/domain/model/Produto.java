package com.app.deliveryapp.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull
    private String nome;

    @NotNull
    private String descricao;

    @NotNull
    private BigDecimal preco;

    @NotNull
    private boolean ativo;

    @ManyToOne
    @JoinColumn(name = "id_restaurante")
    private Restaurante restaurante;
}
