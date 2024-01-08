package com.orcamento.academico.rest.controller;

import com.orcamento.academico.rest.dto.ProgramaDto;
import com.orcamento.academico.rest.form.programa.ProgramaForm;
import com.orcamento.academico.rest.form.programa.ProgramaUpdateForm;
import com.orcamento.academico.service.ProgramaService;
import com.orcamento.academico.service.exceptions.ConstraintException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/programa")
public class ProgramaController {
    @Autowired
    private ProgramaService programaService;

    @GetMapping
    public ResponseEntity<List<ProgramaDto>> findAll() {
        List<ProgramaDto> programaDtoList = programaService.findAll();
        return ResponseEntity.ok().body(programaDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProgramaDto> find(@PathVariable("id") Long id) {
        ProgramaDto programaDto = programaService.findById(id);
        return ResponseEntity.ok().body(programaDto);
    }

    @PostMapping
    public ResponseEntity<ProgramaDto> insert(@Valid @RequestBody ProgramaForm programaForm,
                                              BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        ProgramaDto programaDto = programaService.insert(programaForm);
        return ResponseEntity.ok().body(programaDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProgramaDto> update(@Valid @RequestBody ProgramaUpdateForm programaUpdateForm,
                                                  @PathVariable("id") Long id, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        ProgramaDto programaDto = programaService.update(programaUpdateForm, id);
        return ResponseEntity.ok().body(programaDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        programaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
