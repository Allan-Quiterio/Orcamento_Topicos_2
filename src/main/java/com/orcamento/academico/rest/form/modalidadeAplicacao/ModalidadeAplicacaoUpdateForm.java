package com.orcamento.academico.rest.form.modalidadeAplicacao;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ModalidadeAplicacaoUpdateForm {
  @NotEmpty
  @NotBlank(message = "O Codigo não pode estar em branco.")
  private Float codigo;

  @NotEmpty
  @NotBlank(message = "O Nome não pode estar em branco.")
  @Size(min = 10, max = 255, message = "O Nome deve ter entre 10 e 255 caracteres.")
  private String nome;

  @NotNull(message = "Data de cadastro não pode ser nula.")
  @Past(message = "A data de cadastro informada deve ser anterior ao dia atual.")
  @JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDate dataCadastro;

  @NotNull(message = "Data de alteração não pode ser nula.")
  @Past(message = "A data de alteração informada deve ser anterior ao dia atual.")
  @JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDate dataAlteracao;
}