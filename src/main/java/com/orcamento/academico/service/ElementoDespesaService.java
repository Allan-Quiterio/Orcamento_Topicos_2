package com.orcamento.academico.service;

import com.orcamento.academico.model.ElementoDespesaModel;
import com.orcamento.academico.repository.ElementoDespesaRepository;
import com.orcamento.academico.rest.dto.ElementoDespesaDto;
import com.orcamento.academico.rest.form.elementoDespesa.ElementoDespesaForm;
import com.orcamento.academico.rest.form.elementoDespesa.ElementoDespesaUpdateForm;
import com.orcamento.academico.service.exceptions.DataIntegrityException;
import com.orcamento.academico.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ElementoDespesaService {

    @Autowired
    ElementoDespesaRepository elementoDespesaRepository;

    public ElementoDespesaDto findById(Long id) {
        try {
            Optional<ElementoDespesaModel> elementoDespesaModel = elementoDespesaRepository.findById(id);
            if (elementoDespesaModel.isPresent()) {
                return convertElementoDespesaModelToElementoDespesaDto(elementoDespesaModel.get());
            }
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + ElementoDespesaModel.class.getName());
        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + ElementoDespesaModel.class.getName());
        }
    }

    public List<ElementoDespesaDto> findAll() {
        List<ElementoDespesaModel> elementoDespesaList = elementoDespesaRepository.findAll();
        return convertListToDto(elementoDespesaList);
    }

    public ElementoDespesaDto insert(ElementoDespesaForm elementoDespesaForm) {
        try {
            ElementoDespesaModel elementoDespesaNovo = convertElementoDespesaFormToElementoDespesaModel(elementoDespesaForm);
            Optional<ElementoDespesaModel> byCodigo = elementoDespesaRepository.findByCodigo(elementoDespesaNovo.getCodigo());
            if (byCodigo.isPresent()) {
                throw new IllegalStateException("Codigo já registrado.");
            }
            elementoDespesaNovo = elementoDespesaRepository.save(elementoDespesaNovo);
            return convertElementoDespesaModelToElementoDespesaDto(elementoDespesaNovo);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do Elemento de Despesa não foi(foram) preenchido(s).");
        }
    }

    public ElementoDespesaDto update(ElementoDespesaUpdateForm elementoDespesaUpdateForm, Long id) {
        try {
            Optional<ElementoDespesaModel> elementoDespesaExistente = elementoDespesaRepository.findById(id);
            if (elementoDespesaExistente.isPresent()) {
                ElementoDespesaModel elementoDespesaAtualizado = elementoDespesaExistente.get();
                elementoDespesaAtualizado.setNome(elementoDespesaUpdateForm.getNome());
                elementoDespesaAtualizado.setCodigo(elementoDespesaUpdateForm.getCodigo());
                elementoDespesaAtualizado.setDataCadastro(elementoDespesaUpdateForm.getDataCadastro());
                elementoDespesaAtualizado.setDataAlteracao(elementoDespesaUpdateForm.getDataAlteracao());
                elementoDespesaAtualizado = elementoDespesaRepository.save(elementoDespesaAtualizado);
                return convertElementoDespesaModelToElementoDespesaDto(elementoDespesaAtualizado);
            } else {
                throw new DataIntegrityException("O id do Elemento de Despesa não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do Elemento de Despesa não foi(foram) preenchido(s).");
        }
    }

    public void delete(Long id) {
        try {
            if (elementoDespesaRepository.existsById(id)) {
                elementoDespesaRepository.deleteById(id);
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir um Elemento Despesa!");
        }
    }

    private ElementoDespesaModel convertElementoDespesaFormToElementoDespesaModel(ElementoDespesaForm elementoDespesaForm) {
        ElementoDespesaModel elementoDespesaModel = new ElementoDespesaModel();
        elementoDespesaModel.setNome(elementoDespesaForm.getNome());
        elementoDespesaModel.setCodigo(elementoDespesaForm.getCodigo());
        elementoDespesaModel.setDataCadastro(elementoDespesaForm.getDataCadastro());
        elementoDespesaModel.setDataAlteracao(elementoDespesaForm.getDataAlteracao());
        return elementoDespesaModel;
    }

    private ElementoDespesaDto convertElementoDespesaModelToElementoDespesaDto(ElementoDespesaModel elementoDespesaModel) {
        ElementoDespesaDto elementoDespesaDto = new ElementoDespesaDto();
        elementoDespesaDto.setCodigo(elementoDespesaModel.getCodigo());
        elementoDespesaDto.setId(elementoDespesaModel.getId());
        elementoDespesaDto.setNome(elementoDespesaModel.getNome());
        elementoDespesaDto.setDataAlteracao(elementoDespesaModel.getDataAlteracao());
        elementoDespesaDto.setDataCadastro(elementoDespesaModel.getDataCadastro());
        return elementoDespesaDto;
    }

    private List<ElementoDespesaDto> convertListToDto(List<ElementoDespesaModel> list) {
        List<ElementoDespesaDto> elementoDespesaDtoList = new ArrayList<>();
        for (ElementoDespesaModel elementoDespesaModel : list) {
            ElementoDespesaDto elementoDespesaDto = this.convertElementoDespesaModelToElementoDespesaDto(elementoDespesaModel);
            elementoDespesaDtoList.add(elementoDespesaDto);
        }
        return elementoDespesaDtoList;
    }
}
