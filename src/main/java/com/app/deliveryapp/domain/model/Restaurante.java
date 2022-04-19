package com.app.deliveryapp.domain.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @CreationTimestamp
    @Column(nullable = false)
    private OffsetDateTime dataCadastro;

    @UpdateTimestamp
    @Column(nullable = false)
    @JsonIgnore
    private OffsetDateTime dataAtualizacao;

    @NotNull
    @Column(name = "taxa_frete")
    private BigDecimal taxaFrete;

    @ManyToOne
    @JoinColumn(name = "id_cozinha")
    @NotNull
    private Cozinha cozinha;

    @Embedded
    @JsonIgnore
    private Endereco endereco;

    @JsonIgnore
    @OneToMany(mappedBy = "restaurante")
    private List<Produto> produtos = new ArrayList<>();

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "restaurnte_forma_Pagamento",
    joinColumns = @JoinColumn(name = "restaurante_id ")
    , inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id"))
    private List<FormaPagamento> formaPagamento = new ArrayList<>();
}
