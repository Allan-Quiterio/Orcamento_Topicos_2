package com.orcamento.academico.rest.form.lancamentos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
public class LancamentosUpdateForm {
    @NotNull(message = "O campo 'lancamentoInvalido' não pode estar nulo.")
    private Byte lancamentoInvalido;

    @NotNull(message = "O campo 'numeroLancamento' não pode estar nulo.")
    private Integer numeroLancamento;

    @NotNull(message = "A data de Lançamento não pode estar nula.")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataLancamento;

    @NotEmpty
    @NotBlank(message = "A descrição não pode estar em branco.")
    @Size(max = 255)
    private String descricao;

    @NotNull(message = "O campo 'idTipoLancamento' não pode estar nulo.")
    private Integer idTipoLancamento;

    private Integer idLancamentoPai;

    @NotNull(message = "O campo 'idUnidade' não pode estar nulo.")
    private Integer idUnidade;

    @NotNull(message = "O campo 'idUnidadeOrcamentaria' não pode estar nulo.")
    private Integer idUnidadeOrcamentaria;

    @NotNull(message = "O campo 'idPrograma' não pode estar nulo.")
    private Integer idPrograma;

    @NotNull(message = "O campo 'idAcao' não pode estar nulo.")
    private Integer idAcao;

    @NotNull(message = "O campo 'idFonteRecurso' não pode estar nulo.")
    private Integer idFonteRecurso;

    @NotNull(message = "O campo 'idGrupoDespesa' não pode estar nulo.")
    private Integer idGrupoDespesa;

    @NotNull(message = "O campo 'idModalidadeAplicacao' não pode estar nulo.")
    private Integer idModalidadeAplicacao;

    @NotNull(message = "O campo 'idElementoDespesa' não pode estar nulo.")
    private Integer idElementoDespesa;

    @NotNull(message = "O campo 'idSolicitante' não pode estar nulo.")
    private Integer idSolicitante;

    @NotNull(message = "O campo 'idObjetivoEstrategico' não pode estar nulo.")
    private Integer idObjetivoEstrategico;

    @NotNull(message = "O campo 'idTipoTransacao' não pode estar nulo.")
    private Integer idTipoTransacao;

    @NotNull(message = "O campo 'ged' não pode estar nulo.")
    private String ged;

    @NotNull(message = "O campo 'contratado' não pode estar nulo.")
    @Size(max = 255)
    private String contratado;

    @NotNull(message = "Valor não pode ser nulo.")
    @Positive(message = "Valor do Lançamento não pode ser menor ou igual a zero")
    private Float valor;

    @NotNull(message = "Valor não pode ser nulo.")
    @JsonFormat(pattern = "yyyy")
    private Integer anoOrcamento;
}
