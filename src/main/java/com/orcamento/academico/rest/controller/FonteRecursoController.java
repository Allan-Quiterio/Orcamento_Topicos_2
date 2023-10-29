package com.orcamento.academico.rest.controller;

import com.orcamento.academico.rest.dto.FonteRecursoDto;
import com.orcamento.academico.rest.form.FonteRecursoForm;
import com.orcamento.academico.rest.form.FonteRecursoUpdateForm;
import com.orcamento.academico.service.FonteRecursoService;
import com.orcamento.academico.service.exceptions.ConstraintException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fonteRecursos")
public class FonteRecursoController {

    @Autowired
    private FonteRecursoService fonteRecursoService;

    @GetMapping
    public ResponseEntity<List<FonteRecursoDto>> findAll() {
        List<FonteRecursoDto> fonteRecursoDtoList = fonteRecursoService.findAll();
        return ResponseEntity.ok().body(fonteRecursoDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FonteRecursoDto> find(@PathVariable("id") long id) {
        FonteRecursoDto fonteRecursoDto = fonteRecursoService.findById(id);
        return ResponseEntity.ok().body(fonteRecursoDto);
    }

    @PostMapping
    public ResponseEntity<FonteRecursoDto> insert(@Valid @RequestBody FonteRecursoForm fonteRecursoForm,
                                                  BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        FonteRecursoDto fonteRecursoDto = fonteRecursoService.insert(fonteRecursoForm);
        return ResponseEntity.ok().body(fonteRecursoDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FonteRecursoDto> update(@Valid @RequestBody FonteRecursoUpdateForm fonteRecursoUpdateForm,
                                                  @PathVariable("id") long id, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        FonteRecursoDto fonteRecursoDto = fonteRecursoService.update(fonteRecursoUpdateForm, id);
        return ResponseEntity.ok().body(fonteRecursoDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long id) {
        fonteRecursoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}