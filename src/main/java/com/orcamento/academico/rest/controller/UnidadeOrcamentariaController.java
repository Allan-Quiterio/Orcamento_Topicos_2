package com.orcamento.academico.rest.controller;

import com.orcamento.academico.rest.dto.UnidadeOrcamentaria.UnidadeOrcamentariaDto;
import com.orcamento.academico.rest.form.unidadeOrcamentaria.UnidadeOrcamentariaForm;
import com.orcamento.academico.rest.form.unidadeOrcamentaria.UnidadeOrcamentariaUpdateForm;
import com.orcamento.academico.service.UnidadeOrcamentariaService;
import com.orcamento.academico.service.exceptions.ConstraintException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/unidadeOrcamentaria")
public class UnidadeOrcamentariaController {
    @Autowired
    private UnidadeOrcamentariaService unidadeOrcamentariaService;

    @GetMapping
    public ResponseEntity<List<UnidadeOrcamentariaDto>> findAll() {
        List<UnidadeOrcamentariaDto> unidadeOrcamentariaDtoList = unidadeOrcamentariaService.findAll();
        return ResponseEntity.ok().body(unidadeOrcamentariaDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UnidadeOrcamentariaDto> find(@PathVariable("id") Integer id) {
        UnidadeOrcamentariaDto unidadeOrcamentariaDto = unidadeOrcamentariaService.findById(id);
        return ResponseEntity.ok().body(unidadeOrcamentariaDto);
    }

    @PostMapping
    public ResponseEntity<UnidadeOrcamentariaDto> insert(@Valid @RequestBody UnidadeOrcamentariaForm unidadeOrcamentariaForm,
                                                  BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        UnidadeOrcamentariaDto unidadeOrcamentariaDto = unidadeOrcamentariaService.insert(unidadeOrcamentariaForm);
        return ResponseEntity.ok().body(unidadeOrcamentariaDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UnidadeOrcamentariaDto> update(@Valid @RequestBody UnidadeOrcamentariaUpdateForm unidadeOrcamentariaUpdateForm,
                                                  @PathVariable("id") Integer id, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        UnidadeOrcamentariaDto unidadeOrcamentariaDto = unidadeOrcamentariaService.update(unidadeOrcamentariaUpdateForm, id);
        return ResponseEntity.ok().body(unidadeOrcamentariaDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        unidadeOrcamentariaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
