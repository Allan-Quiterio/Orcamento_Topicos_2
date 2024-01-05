package com.orcamento.academico.model;

import javax.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "TB_TIPO_LANCAMENTO")
public class TipoLancamentoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", length = 255, nullable = false)
    private String nome;

    @Column(name = "dataCadastro", nullable = false)
    private String dataCadastro;

    @Column(name = "dataAlteracao", nullable = false)
    private String dataAlteracao;
}

