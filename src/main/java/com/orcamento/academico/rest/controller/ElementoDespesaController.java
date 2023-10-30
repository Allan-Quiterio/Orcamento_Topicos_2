package com.orcamento.academico.rest.controller;

import com.orcamento.academico.rest.dto.ElementoDespesaDto;
import com.orcamento.academico.rest.form.elementoDespesa.ElementoDespesaForm;
import com.orcamento.academico.service.ElementoDespesaService;
import com.orcamento.academico.service.exceptions.ConstraintException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/elementoDespesa")
public class ElementoDespesaController {
    @Autowired
    private ElementoDespesaService elementoDespesaService;

    @GetMapping
    public ResponseEntity<List<ElementoDespesaDto>> findAll() {
        List<ElementoDespesaDto> elementoDespesaDtoList = elementoDespesaService.findAll();
        return ResponseEntity.ok().body(elementoDespesaDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ElementoDespesaDto> find(@PathVariable("id") Integer id) {
        ElementoDespesaDto elementoDespesaDto = elementoDespesaService.findById(id);
        return ResponseEntity.ok().body(elementoDespesaDto);
    }

    @PostMapping
    public ResponseEntity<ElementoDespesaDto> insert(@Valid @RequestBody ElementoDespesaForm elementoDespesaForm,
                                                  BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        ElementoDespesaDto elementoDespesaDto = elementoDespesaService.insert(elementoDespesaForm);
        return ResponseEntity.ok().body(elementoDespesaDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ElementoDespesaDto> update(@Valid @RequestBody ElementoDespesaForm elementoDespesaUpdateForm,
                                                  @PathVariable("id") Integer id, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        ElementoDespesaDto elementoDespesaDto = elementoDespesaService.update(elementoDespesaUpdateForm, id);
        return ResponseEntity.ok().body(elementoDespesaDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        elementoDespesaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
