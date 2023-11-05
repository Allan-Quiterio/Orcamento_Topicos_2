package com.orcamento.academico.rest.form.lancamentos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
public class LancamentosUpdateForm {
    @NotNull(message = "O campo 'lancamentoInvalido' não pode estar nulo.")
    private Byte lancamentoInvalido;

    private Integer numeroLancamento;

    @NotNull(message = "A data de Lançamento não pode estar nula.")
    @FutureOrPresent(message = "Data de Lançamento deve ser data atual ou futura.")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataLancamento;

    @NotEmpty
    @NotBlank(message = "A descrição não pode estar em branco.")
    @Size(max = 255)
    private String descricao;

    private Integer idTipoLancamento;
    private Integer idLancamentoPai;
    private Integer idUnidade;
    private Integer idUnidadeOrcamentaria;
    private Integer idPrograma;
    private Integer idAcao;
    private Integer idFonteRecurso;
    private Integer idGrupoDespesa;
    private Integer idModalidadeAplicacao;
    private Integer idElementoDespesa;
    private Integer idSolicitante;
    private Integer idObjetivoEstrategico;
    private Integer idTipoTransacao;

    private Character ged;

    @Size(max = 255)
    private String contratado;

    @NotNull(message = "Valor não pode ser nulo.")
    @Positive(message = "Valor do Lançamento não pode ser menor ou igual a zero")
    private Float valor;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataAlteracao;

    @Digits(integer = 4, fraction = 0, message = "O anoOrcamento deve ter exatamente 4 dígitos")
    private Short anoOrcamento;

}
