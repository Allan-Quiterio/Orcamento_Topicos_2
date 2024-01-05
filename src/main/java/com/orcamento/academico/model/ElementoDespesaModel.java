package com.orcamento.academico.model;

import javax.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name="TB_ELEMENTO_DESPESA")
public class ElementoDespesaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo", length = 100, nullable = false, unique = true)
    private Integer codigo;

    @Column(name = "nome", length = 255, nullable = false)
    private String nome;

    @Column(name = "dataCadastro", nullable = false)
    private String dataCadastro;

    @Column(name = "dataAlteracao")
    private String dataAlteracao;
}
