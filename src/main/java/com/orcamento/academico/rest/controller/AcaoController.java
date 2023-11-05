package com.orcamento.academico.rest.controller;

import com.orcamento.academico.rest.dto.AcaoDto;
import com.orcamento.academico.rest.form.acao.AcaoForm;
import com.orcamento.academico.rest.form.acao.AcaoUpdateForm;
import com.orcamento.academico.service.AcaoService;
import com.orcamento.academico.service.exceptions.ConstraintException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/acao")
public class AcaoController {
    @Autowired
    private AcaoService acaoService;

    @GetMapping
    public ResponseEntity<List<AcaoDto>> findAll() {
        List<AcaoDto> acaoDtoList = acaoService.findAll();
        return ResponseEntity.ok().body(acaoDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AcaoDto> find(@PathVariable("id") Long id) {
        AcaoDto acaoDto = acaoService.findById(id);
        return ResponseEntity.ok().body(acaoDto);
    }

    @PostMapping
    public ResponseEntity<AcaoDto> insert(@Valid @RequestBody AcaoForm acaoForm,
                                                  BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        AcaoDto acaoDto = acaoService.insert(acaoForm);
        return ResponseEntity.ok().body(acaoDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AcaoDto> update(@Valid @RequestBody AcaoUpdateForm acaoUpdateForm,
                                                  @PathVariable("id") Long id, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        AcaoDto acaoDto = acaoService.update(acaoUpdateForm, id);
        return ResponseEntity.ok().body(acaoDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        acaoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
