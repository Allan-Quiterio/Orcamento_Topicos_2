package com.orcamento.academico.rest.controller;

import com.orcamento.academico.rest.dto.TipoLancamentoDto;
import com.orcamento.academico.rest.form.tipoLancamento.TipoLancamentoForm;
import com.orcamento.academico.rest.form.tipoLancamento.TipoLancamentoUpdateForm;
import com.orcamento.academico.service.TipoLancamentoService;
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
    private TipoLancamentoService tipoLancamentoService;

    @GetMapping
    public ResponseEntity<List<TipoLancamentoDto>> findAll() {
        List<TipoLancamentoDto> tipoLancamentoDtoList = tipoLancamentoService.findAll();
        return ResponseEntity.ok().body(tipoLancamentoDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoLancamentoDto> find(@PathVariable("id") long id) {
        TipoLancamentoDto tipoLancamentoDto = tipoLancamentoService.findById(id);
        return ResponseEntity.ok().body(tipoLancamentoDto);
    }

    @PostMapping
    public ResponseEntity<TipoLancamentoDto> insert(@Valid @RequestBody TipoLancamentoForm tipoLancamentoForm,
                                                    BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        TipoLancamentoDto tipoLancamentoDto = tipoLancamentoService.insert(tipoLancamentoForm);
        return ResponseEntity.ok().body(tipoLancamentoDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoLancamentoDto> update(@Valid @RequestBody TipoLancamentoUpdateForm tipoLancamentoUpdateForm,
                                                    @PathVariable("id") long id, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        TipoLancamentoDto tipoLancamentoDto = tipoLancamentoService.update(tipoLancamentoUpdateForm, id);
        return ResponseEntity.ok().body(tipoLancamentoDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long id) {
        tipoLancamentoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
