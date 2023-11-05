package com.orcamento.academico.rest.controller;

import com.orcamento.academico.rest.dto.ModalidadeAplicacaoDto;
import com.orcamento.academico.rest.form.modalidadeAplicacao.ModalidadeAplicacaoForm;
import com.orcamento.academico.rest.form.modalidadeAplicacao.ModalidadeAplicacaoUpdateForm;
import com.orcamento.academico.service.ModalidadeAplicacaoService;
import com.orcamento.academico.service.exceptions.ConstraintException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/modalidadeAplicacoes")
public class ModalidadeAplicacaoController {

  @Autowired
  private ModalidadeAplicacaoService modalidadeAplicacaoService;

  @GetMapping
  public ResponseEntity<List<ModalidadeAplicacaoDto>> findAll() {
    List<ModalidadeAplicacaoDto> modalidadeAplicacaoDtoList = modalidadeAplicacaoService.findAll();
    return ResponseEntity.ok().body(modalidadeAplicacaoDtoList);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ModalidadeAplicacaoDto> find(@PathVariable("id") long id) {
    ModalidadeAplicacaoDto modalidadeAplicacaoDto = modalidadeAplicacaoService.findById(id);
    return ResponseEntity.ok().body(modalidadeAplicacaoDto);
  }

  @PostMapping
  public ResponseEntity<ModalidadeAplicacaoDto> insert(
      @Valid @RequestBody ModalidadeAplicacaoForm modalidadeAplicacaoForm,
      BindingResult br) {
    if (br.hasErrors())
      throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

    ModalidadeAplicacaoDto modalidadeAplicacaoDto = modalidadeAplicacaoService.insert(modalidadeAplicacaoForm);
    return ResponseEntity.ok().body(modalidadeAplicacaoDto);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ModalidadeAplicacaoDto> update(
      @Valid @RequestBody ModalidadeAplicacaoUpdateForm modalidadeAplicacaoUpdateForm,
      @PathVariable("id") long id, BindingResult br) {
    if (br.hasErrors())
      throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

    ModalidadeAplicacaoDto modalidadeAplicacaoDto = modalidadeAplicacaoService.update(modalidadeAplicacaoUpdateForm,
        id);
    return ResponseEntity.ok().body(modalidadeAplicacaoDto);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") long id) {
    modalidadeAplicacaoService.delete(id);
    return ResponseEntity.noContent().build();
  }
}