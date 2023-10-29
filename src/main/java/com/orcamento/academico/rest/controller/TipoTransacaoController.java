package com.orcamento.academico.rest.controller;

import com.orcamento.academico.rest.dto.TipoTransacaoDto;
import com.orcamento.academico.rest.form.TipoTransacaoForm;
import com.orcamento.academico.rest.form.TipoTransacaoUpdateForm;
import com.orcamento.academico.service.TipoTransacaoService;
import com.orcamento.academico.service.exceptions.ConstraintException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipoTransacoes")
public class TipoTransacaoController {

  @Autowired
  private TipoTransacaoService tipoTransacaoService;

  @GetMapping
  public ResponseEntity<List<TipoTransacaoDto>> findAll() {
    List<TipoTransacaoDto> tipoTransacaoDtoList = tipoTransacaoService.findAll();
    return ResponseEntity.ok().body(tipoTransacaoDtoList);
  }

  @GetMapping("/{id}")
  public ResponseEntity<TipoTransacaoDto> find(@PathVariable("id") long id) {
    TipoTransacaoDto tipoTransacaoDto = tipoTransacaoService.findById(id);
    return ResponseEntity.ok().body(tipoTransacaoDto);
  }

  @PostMapping
  public ResponseEntity<TipoTransacaoDto> insert(@Valid @RequestBody TipoTransacaoForm tipoTransacaoForm,
      BindingResult br) {
    if (br.hasErrors())
      throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

    TipoTransacaoDto tipoTransacaoDto = tipoTransacaoService.insert(tipoTransacaoForm);
    return ResponseEntity.ok().body(tipoTransacaoDto);
  }

  @PutMapping("/{id}")
  public ResponseEntity<TipoTransacaoDto> update(@Valid @RequestBody TipoTransacaoUpdateForm tipoTransacaoUpdateForm,
      @PathVariable("id") long id, BindingResult br) {
    if (br.hasErrors())
      throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

    TipoTransacaoDto tipoTransacaoDto = tipoTransacaoService.update(tipoTransacaoUpdateForm, id);
    return ResponseEntity.ok().body(tipoTransacaoDto);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") long id) {
    tipoTransacaoService.delete(id);
    return ResponseEntity.noContent().build();
  }
}