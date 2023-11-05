package com.orcamento.academico.rest.controller;

import com.orcamento.academico.rest.dto.LancamentosDto;
import com.orcamento.academico.rest.form.lancamentos.LancamentosForm;
import com.orcamento.academico.rest.form.lancamentos.LancamentosUpdateForm;
import com.orcamento.academico.service.LancamentosService;
import com.orcamento.academico.service.exceptions.ConstraintException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/lancamento")
public class LancamentosController {

    @Autowired
    private LancamentosService lancamentosService;

    @GetMapping
    public ResponseEntity<List<LancamentosDto>> findAll() {
        List<LancamentosDto> lancamentosDtoList = lancamentosService.findAll();
        return ResponseEntity.ok().body(lancamentosDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LancamentosDto> find(@PathVariable("id") Integer id) {
        LancamentosDto lancamentosDto = lancamentosService.findById(id);
        return ResponseEntity.ok().body(lancamentosDto);
    }

    @PostMapping
    public ResponseEntity<LancamentosDto> insert(@Valid @RequestBody LancamentosForm lancamentosForm,
                                                    BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        LancamentosDto lancamentosDto = lancamentosService.insert(lancamentosForm);
        return ResponseEntity.ok().body(lancamentosDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LancamentosDto> update(@Valid @RequestBody LancamentosUpdateForm lancamentosUpdateForm,
                                                    @PathVariable("id") Integer id, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        LancamentosDto lancamentosDto = lancamentosService.update(lancamentosUpdateForm, id);
        return ResponseEntity.ok().body(lancamentosDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        lancamentosService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
