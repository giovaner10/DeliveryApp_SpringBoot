package com.app.deliveryapp.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Grupo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull
    private String nome;

    @ManyToMany
    @JoinTable(name = "Grupo_Permissoes",
            joinColumns = @JoinColumn(name = "grupo_id")
            , inverseJoinColumns = @JoinColumn(name = "permissao_id"))
    private List<Permissao> permissoes = new ArrayList<>();

}
