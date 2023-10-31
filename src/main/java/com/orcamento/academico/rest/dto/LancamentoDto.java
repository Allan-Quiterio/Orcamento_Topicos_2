package com.orcamento.academico.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.orcamento.academico.model.*;

import java.time.LocalDate;

public class LancamentoDto {
    private Integer id;
    private Byte lancamentoInvalido;
    private Integer numeroLancamento;
    private TipoLancamentoModel tipoLancamento;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataLancamento;
    private Integer idLancamentoPai;
    private UnidadeModel idUnidade;
    private String descricao;
    private UnidadeOrcamentariaModel idUnidadeOrcamentaria;
    private ProgramaModel idPrograma;
    private AcaoModel idAcao;
    private FonteRecursoModel idFonteRecurso;
    private GrupoDespesaModel idGrupoDespesa;
    private ModalidadeAplicacaoModel idModalidadeAplicacao;
    private ElementoDespesaModel idElementoDespesa;
    private SolicitanteModel idSolicitante;
    private Character ged;
    private String contratado;
    private ObjetivoEstrategicoModel idObjetivoEstrategico;
    private Float valor;
    private TipoTransacaoModel idTipoTransacaoModel;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataAlteracao;
    @JsonFormat(pattern = "yyyy")
    private LocalDate anoOrcamento;
}
