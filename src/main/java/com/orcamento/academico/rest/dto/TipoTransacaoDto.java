package com.orcamento.academico.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoTransacaoDto {
  private Long id;
  private String nome;
  private String dataAlteracao;
  private String dataCadastro;
}
