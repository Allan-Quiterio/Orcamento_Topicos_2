package com.orcamento.academico.rest.form.objetivoEstrategico;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ObjetivoEstrategicoUpdateForm {
  @NotEmpty
  @NotBlank(message = "O Nome não pode estar em branco.")
  @Size(min = 10, max = 255, message = "O Nome deve ter entre 10 e 255 caracteres.")
  private String nome;
}