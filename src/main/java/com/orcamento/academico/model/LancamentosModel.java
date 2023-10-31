package com.orcamento.academico.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name="lancamentos")
public class LancamentosModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "lancamentoInvalido", nullable = false)
    private Byte lancamentoInvalido;

    @Column(name = "numeroLancamento", nullable = false)
    private Integer numeroLancamento;

    @Column(name = "idTipoLancamento", length = 100, nullable = false)
    private Integer idTipoLancamento;

    @Column(name = "dataLancamento", nullable = false)
    private LocalDate dataLancamento;

    @Column(name = "idLancamentoPai", length = 100, nullable = false)
    private Integer idLancamentoPai;

    @Column(name = "idUnidade", length = 100, nullable = false)
    private Integer idUnidade;

    @Column(name = "descricao", length = 255, nullable = false)
    private String descricao;

    @Column(name = "idUnidadeOrcamentaria", length = 100, nullable = false)
    private Integer idUnidadeOrcamentaria;

    @Column(name = "idPrograma", length = 100, nullable = false)
    private Integer idPrograma;

    @Column(name = "idAcao", length = 100, nullable = false)
    private Integer idAcao;

    @Column(name = "idFonteRecurso", length = 100, nullable = false)
    private Integer idFonteRecurso;

    @Column(name = "idGrupoDespesa", length = 100, nullable = false)
    private Integer idGrupoDespesa;

    @Column(name = "idModalidadeAplicacao", length = 100, nullable = false)
    private Integer idModalidadeAplicacao;

    @Column(name = "idElementoDespesa", length = 100, nullable = false)
    private Integer idElementoDespesa;

    @Column(name = "idSolicitante", length = 100, nullable = false)
    private Integer idSolicitante;

    @Column(name = "ged", length = 100, nullable = false)
    private Character ged;

    @Column(name = "contratado", length = 255, nullable = false)
    private String contratado;

    @Column(name = "idObjetivoEstrategico", length = 100, nullable = false)
    private Integer idObjetivoEstrategico;

    @Column(name = "valor", nullable = false)
    private Float valor;

    @Column(name = "idTipoTransacao", length = 100, nullable = false)
    private Integer idTipoTransacao;

    @Column(name = "dataCadastro", nullable = false)
    private LocalDate dataCadastro;

    @Column(name = "dataAlteracao", nullable = false)
    private LocalDate dataAlteracao;

    @Column(name = "anoOrcamento", nullable = false)
    private LocalDate anoOrcamento;
}
