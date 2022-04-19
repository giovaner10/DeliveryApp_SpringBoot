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
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private BigDecimal precoUnitario;

    private Integer precoTotal;

    private Integer quantidade;

    private String observacao;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    @NotNull
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    @NotNull
    private Produto produto;


}
