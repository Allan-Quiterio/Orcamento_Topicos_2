package com.orcamento.academico.rest.controller;

import com.orcamento.academico.rest.dto.ObjetivoEstrategicoDto;
import com.orcamento.academico.rest.form.objetivoEstrategico.ObjetivoEstrategicoForm;
import com.orcamento.academico.rest.form.objetivoEstrategico.ObjetivoEstrategicoUpdateForm;
import com.orcamento.academico.service.ObjetivoEstrategicoService;
import com.orcamento.academico.service.exceptions.ConstraintException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/objetivoEstrategico")
public class ObjetivoEstrategicoController {

    @Autowired
    private ObjetivoEstrategicoService objetivoEstrategicoService;

    @GetMapping
    public ResponseEntity<List<ObjetivoEstrategicoDto>> findAll() {
        List<ObjetivoEstrategicoDto> objetivoEstrategicoDtoList = objetivoEstrategicoService.findAll();
        return ResponseEntity.ok().body(objetivoEstrategicoDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ObjetivoEstrategicoDto> find(@PathVariable("id") long id) {
        ObjetivoEstrategicoDto objetivoEstrategicoDto = objetivoEstrategicoService.findById(id);
        return ResponseEntity.ok().body(objetivoEstrategicoDto);
    }

    @PostMapping
    public ResponseEntity<ObjetivoEstrategicoDto> insert(@Valid @RequestBody ObjetivoEstrategicoForm objetivoEstrategicoForm,
                                             BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        ObjetivoEstrategicoDto objetivoEstrategicoDto = objetivoEstrategicoService.insert(objetivoEstrategicoForm);
        return ResponseEntity.ok().body(objetivoEstrategicoDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ObjetivoEstrategicoDto> update(@Valid @RequestBody ObjetivoEstrategicoUpdateForm objetivoEstrategicoUpdateForm,
                                                   @PathVariable("id") long id, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        ObjetivoEstrategicoDto objetivoEstrategicoDto = objetivoEstrategicoService.update(objetivoEstrategicoUpdateForm, id);
        return ResponseEntity.ok().body(objetivoEstrategicoDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long id) {
        objetivoEstrategicoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

