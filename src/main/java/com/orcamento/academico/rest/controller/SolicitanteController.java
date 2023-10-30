package com.orcamento.academico.rest.controller;

import com.orcamento.academico.rest.dto.SolicitanteDto;
import com.orcamento.academico.rest.form.solicitante.SolicitanteForm;
import com.orcamento.academico.rest.form.solicitante.SolicitanteUpdateForm;
import com.orcamento.academico.service.SolicitanteService;
import com.orcamento.academico.service.exceptions.ConstraintException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/solicitante")
public class SolicitanteController {

    @Autowired
    private SolicitanteService solicitanteService;

    @GetMapping
    public ResponseEntity<List<SolicitanteDto>> findAll() {
        List<SolicitanteDto> solicitanteDtoList = solicitanteService.findAll();
        return ResponseEntity.ok().body(solicitanteDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SolicitanteDto> find(@PathVariable("id") long id) {
        SolicitanteDto solicitanteDto = solicitanteService.findById(id);
        return ResponseEntity.ok().body(solicitanteDto);
    }

    @PostMapping
    public ResponseEntity<SolicitanteDto> insert(@Valid @RequestBody SolicitanteForm solicitanteForm,
                                             BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        SolicitanteDto solicitanteDto = solicitanteService.insert(solicitanteForm);
        return ResponseEntity.ok().body(solicitanteDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SolicitanteDto> update(@Valid @RequestBody SolicitanteUpdateForm solicitanteUpdateForm,
                                                   @PathVariable("id") long id, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        SolicitanteDto solicitanteDto = solicitanteService.update(solicitanteUpdateForm, id);
        return ResponseEntity.ok().body(solicitanteDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long id) {
        solicitanteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

