package com.orcamento.academico.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ElementoDespesaDto {
    private Long id;
    private Integer codigo;
    private String nome;
    private String dataCadastro;
    private String dataAlteracao;
}
