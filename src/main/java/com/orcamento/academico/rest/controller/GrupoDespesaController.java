package com.orcamento.academico.rest.controller;

import com.orcamento.academico.rest.dto.GrupoDespesaDto;
import com.orcamento.academico.rest.form.GrupoDespesaForm;
import com.orcamento.academico.rest.form.GrupoDespesaUpdateForm;
import com.orcamento.academico.service.GrupoDespesaService;
import com.orcamento.academico.service.exceptions.ConstraintException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grupoDespesas")
public class GrupoDespesaController {

  @Autowired
  private GrupoDespesaService grupoDespesaService;

  @GetMapping
  public ResponseEntity<List<GrupoDespesaDto>> findAll() {
    List<GrupoDespesaDto> grupoDespesaDtoList = grupoDespesaService.findAll();
    return ResponseEntity.ok().body(grupoDespesaDtoList);
  }

  @GetMapping("/{id}")
  public ResponseEntity<GrupoDespesaDto> find(@PathVariable("id") long id) {
    GrupoDespesaDto grupoDespesaDto = grupoDespesaService.findById(id);
    return ResponseEntity.ok().body(grupoDespesaDto);
  }

  @PostMapping
  public ResponseEntity<GrupoDespesaDto> insert(@Valid @RequestBody GrupoDespesaForm grupoDespesaForm,
      BindingResult br) {
    if (br.hasErrors())
      throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

    GrupoDespesaDto grupoDespesaDto = grupoDespesaService.insert(grupoDespesaForm);
    return ResponseEntity.ok().body(grupoDespesaDto);
  }

  @PutMapping("/{id}")
  public ResponseEntity<GrupoDespesaDto> update(@Valid @RequestBody GrupoDespesaUpdateForm grupoDespesaUpdateForm,
      @PathVariable("id") long id, BindingResult br) {
    if (br.hasErrors())
      throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

    GrupoDespesaDto grupoDespesaDto = grupoDespesaService.update(grupoDespesaUpdateForm, id);
    return ResponseEntity.ok().body(grupoDespesaDto);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") long id) {
    grupoDespesaService.delete(id);
    return ResponseEntity.noContent().build();
  }
}