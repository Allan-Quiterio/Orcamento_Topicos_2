package com.orcamento.academico.service;

import com.orcamento.academico.model.UnidadeModel;
import com.orcamento.academico.repository.UnidadeRepository;
import com.orcamento.academico.rest.dto.UnidadeDto;
import com.orcamento.academico.rest.form.UnidadeForm;
import com.orcamento.academico.rest.form.UnidadeUpdateForm;
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
public class UnidadeService {

  @Autowired
  UnidadeRepository unidadeRepository;

  public UnidadeDto findById(long id) {
    try {
      Optional<UnidadeModel> unidadeModel = unidadeRepository.findById(id);
      if (unidadeModel.isPresent()) {
        return convertUnidadeModelToUnidadeDto(unidadeModel.get());
      }
      throw new ObjectNotFoundException(
          "Objeto não encontrado! Id: " + id + ", Tipo: " + UnidadeModel.class.getName());
    } catch (NoSuchElementException e) {
      throw new ObjectNotFoundException(
          "Objeto não encontrado! Id: " + id + ", Tipo: " + UnidadeModel.class.getName());
    }
  }

  public List<UnidadeDto> findAll() {
    List<UnidadeModel> unidadeList = unidadeRepository.findAll();
    return convertListToDto(unidadeList);
  }

  public UnidadeDto insert(UnidadeForm unidadeForm) {
    try {
      UnidadeModel unidadeNovo = convertUnidadeFormToUnidadeModel(unidadeForm);
      unidadeNovo = unidadeRepository.save(unidadeNovo);
      return convertUnidadeModelToUnidadeDto(unidadeNovo);
    } catch (DataIntegrityViolationException e) {
      throw new DataIntegrityException("Campo(s) obrigatório(s) não foi(foram) preenchido(s).");
    }
  }

  public UnidadeDto update(UnidadeUpdateForm unidadeUpdateForm, long id) {
    try {
      Optional<UnidadeModel> unidadeExistente = unidadeRepository.findById(id);
      if (unidadeExistente.isPresent()) {
        UnidadeModel unidadeAtualizado = unidadeExistente.get();
        unidadeAtualizado.setNome(unidadeUpdateForm.getNome());
        unidadeAtualizado.setDataCadastro(unidadeUpdateForm.getDataCadastro());
        unidadeAtualizado.setDataAlteracao(unidadeUpdateForm.getDataAlteracao());
        unidadeRepository.save(unidadeAtualizado);
        return convertUnidadeModelToUnidadeDto(unidadeAtualizado);
      } else {
        throw new DataIntegrityException("A id da Unidade não existe na base de dados!");
      }
    } catch (DataIntegrityViolationException e) {
      throw new DataIntegrityException("Campo(s) obrigatório(s) da Unidade não foi(foram) preenchido(s).");
    }
  }

  public void delete(long id) {
    try {
      if (unidadeRepository.existsById(id)) {
        unidadeRepository.deleteById(id);
      }
    } catch (DataIntegrityViolationException e) {
      throw new DataIntegrityException("Não é possível excluir uma Unidade!");
    }
  }

  private UnidadeModel convertUnidadeFormToUnidadeModel(UnidadeForm unidadeForm) {
    UnidadeModel unidadeModel = new UnidadeModel();
    unidadeModel.setNome(unidadeForm.getNome());
    unidadeModel.setDataCadastro(unidadeForm.getDataCadastro());
    unidadeModel.setDataAlteracao(unidadeForm.getDataAlteracao());
    return unidadeModel;
  }

  private UnidadeDto convertUnidadeModelToUnidadeDto(UnidadeModel unidadeModel) {
    UnidadeDto unidadeDto = new UnidadeDto();
    unidadeDto.setId(unidadeModel.getId());
    unidadeDto.setNome(unidadeModel.getNome());
    unidadeDto.setDataAlteracao(unidadeModel.getDataAlteracao());
    unidadeDto.setDataCadastro(unidadeModel.getDataCadastro());
    return unidadeDto;
  }

  private List<UnidadeDto> convertListToDto(List<UnidadeModel> list) {
    List<UnidadeDto> unidadeDtoList = new ArrayList<>();
    for (UnidadeModel unidadeModel : list) {
      UnidadeDto unidadeDto = this.convertUnidadeModelToUnidadeDto(unidadeModel);
      unidadeDtoList.add(unidadeDto);
    }
    return unidadeDtoList;
  }
}
