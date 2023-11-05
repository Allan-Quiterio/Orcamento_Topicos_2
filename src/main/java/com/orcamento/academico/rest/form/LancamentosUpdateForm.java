package com.orcamento.academico.rest.form;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
public class LancamentosUpdateForm {

    private Byte lancamentoInvalido;
    private Integer numeroLancamento;
    private Integer idTipoLancamento;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataLancamento;
    private Integer idLancamentoPai;
    private Integer idUnidade;
    private String descricao;
    private Integer idUnidadeOrcamentaria;
    private Integer idPrograma;
    private Integer idAcao;
    private Integer idFonteRecurso;
    private Integer idGrupoDespesa;
    private Integer idModalidadeAplicacao;
    private Integer idElementoDespesa;
    private Integer idSolicitante;
    private Character ged;
    private String contratado;
    private Integer idObjetivoEstrategico;
    private Float valor;
    private Integer idTipoTransacao;

}
