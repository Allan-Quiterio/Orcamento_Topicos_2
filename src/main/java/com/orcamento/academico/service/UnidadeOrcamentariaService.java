package com.orcamento.academico.service;

import com.orcamento.academico.model.UnidadeOrcamentariaModel;
import com.orcamento.academico.repository.UnidadeOrcamentariaRepository;
import com.orcamento.academico.rest.dto.UnidadeOrcamentaria.UnidadeOrcamentariaDto;
import com.orcamento.academico.rest.form.unidadeOrcamentaria.UnidadeOrcamentariaForm;
import com.orcamento.academico.rest.form.unidadeOrcamentaria.UnidadeOrcamentariaUpdateForm;
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
public class UnidadeOrcamentariaService {

    @Autowired
    UnidadeOrcamentariaRepository unidadeOrcamentariaRepository;

    public UnidadeOrcamentariaDto findById(Integer id) {
        try {
            Optional<UnidadeOrcamentariaModel> unidadeOrcamentariaModel = unidadeOrcamentariaRepository.findById(id);
            if (unidadeOrcamentariaModel.isPresent()) {
                return convertUnidadeOrcamentariaModelToUnidadeOrcamentariaDto(unidadeOrcamentariaModel.get());
            }
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + UnidadeOrcamentariaModel.class.getName());
        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + UnidadeOrcamentariaModel.class.getName());
        }
    }

    public List<UnidadeOrcamentariaDto> findAll() {
        List<UnidadeOrcamentariaModel> unidadeOrcamentariaList = unidadeOrcamentariaRepository.findAll();
        return convertListToDto(unidadeOrcamentariaList);
    }

    public UnidadeOrcamentariaDto insert(UnidadeOrcamentariaForm unidadeOrcamentariaForm) {
        try {
            UnidadeOrcamentariaModel unidadeOrcamentariaNovo = convertUnidadeOrcamentariaFormToUnidadeOrcamentariaModel(unidadeOrcamentariaForm);
            Optional<UnidadeOrcamentariaModel> byCodigo = unidadeOrcamentariaRepository.findByCodigo(unidadeOrcamentariaNovo.getCodigo());
            if (byCodigo.isPresent()) {
                throw new IllegalStateException("Codigo já registrado.");
            }
            unidadeOrcamentariaNovo = unidadeOrcamentariaRepository.save(unidadeOrcamentariaNovo);
            return convertUnidadeOrcamentariaModelToUnidadeOrcamentariaDto(unidadeOrcamentariaNovo);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) da Unidade Orçamentária não foi(foram) preenchido(s).");
        }
    }

    public UnidadeOrcamentariaDto update(UnidadeOrcamentariaUpdateForm unidadeOrcamentariaUpdateForm, Integer id) {
        try {
            Optional<UnidadeOrcamentariaModel> unidadeOrcamentariaExistente = unidadeOrcamentariaRepository.findById(id);
            if (unidadeOrcamentariaExistente.isPresent()) {
                UnidadeOrcamentariaModel unidadeOrcamentariaAtualizado = unidadeOrcamentariaExistente.get();
                unidadeOrcamentariaAtualizado.setNome(unidadeOrcamentariaUpdateForm.getNome());
                unidadeOrcamentariaAtualizado.setCodigo(unidadeOrcamentariaUpdateForm.getCodigo());
                unidadeOrcamentariaAtualizado.setDataCadastro(unidadeOrcamentariaUpdateForm.getDataCadastro());
                unidadeOrcamentariaAtualizado.setDataAlteracao(unidadeOrcamentariaUpdateForm.getDataAlteracao());
                unidadeOrcamentariaAtualizado = unidadeOrcamentariaRepository.save(unidadeOrcamentariaAtualizado);
                return convertUnidadeOrcamentariaModelToUnidadeOrcamentariaDto(unidadeOrcamentariaAtualizado);
            } else {
                throw new DataIntegrityException("O id da Unidade Orcamentária não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) da Unidade Orcamentária não foi(foram) preenchido(s).");
        }
    }

    public void delete(Integer id) {
        try {
            if (unidadeOrcamentariaRepository.existsById(id)) {
                unidadeOrcamentariaRepository.deleteById(id);
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir uma Unidade Orcamentária!");
        }
    }

    private UnidadeOrcamentariaModel convertUnidadeOrcamentariaFormToUnidadeOrcamentariaModel(UnidadeOrcamentariaForm unidadeOrcamentariaForm) {
        UnidadeOrcamentariaModel unidadeOrcamentariaModel = new UnidadeOrcamentariaModel();
        unidadeOrcamentariaModel.setNome(unidadeOrcamentariaForm.getNome());
        unidadeOrcamentariaModel.setCodigo(unidadeOrcamentariaForm.getCodigo());
        unidadeOrcamentariaModel.setDataCadastro(unidadeOrcamentariaForm.getDataCadastro());
        unidadeOrcamentariaModel.setDataAlteracao(unidadeOrcamentariaForm.getDataAlteracao());
        return unidadeOrcamentariaModel;
    }

    private UnidadeOrcamentariaDto convertUnidadeOrcamentariaModelToUnidadeOrcamentariaDto(UnidadeOrcamentariaModel unidadeOrcamentariaModel) {
        UnidadeOrcamentariaDto unidadeOrcamentariaDto = new UnidadeOrcamentariaDto();
        unidadeOrcamentariaDto.setCodigo(unidadeOrcamentariaModel.getCodigo());
        unidadeOrcamentariaDto.setId(unidadeOrcamentariaModel.getId());
        unidadeOrcamentariaDto.setNome(unidadeOrcamentariaModel.getNome());
        unidadeOrcamentariaDto.setDataAlteracao(unidadeOrcamentariaModel.getDataAlteracao());
        unidadeOrcamentariaDto.setDataCadastro(unidadeOrcamentariaModel.getDataCadastro());
        return unidadeOrcamentariaDto;
    }

    private List<UnidadeOrcamentariaDto> convertListToDto(List<UnidadeOrcamentariaModel> list) {
        List<UnidadeOrcamentariaDto> unidadeOrcamentariaDtoList = new ArrayList<>();
        for (UnidadeOrcamentariaModel unidadeOrcamentariaModel : list) {
            UnidadeOrcamentariaDto unidadeOrcamentariaDto = this.convertUnidadeOrcamentariaModelToUnidadeOrcamentariaDto(unidadeOrcamentariaModel);
            unidadeOrcamentariaDtoList.add(unidadeOrcamentariaDto);
        }
        return unidadeOrcamentariaDtoList;
    }
}
