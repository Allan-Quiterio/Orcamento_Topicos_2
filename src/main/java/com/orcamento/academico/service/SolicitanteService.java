package com.orcamento.academico.service;

import com.orcamento.academico.model.SolicitanteModel;
import com.orcamento.academico.repository.SolicitanteRepository;
import com.orcamento.academico.rest.dto.SolicitanteDto;
import com.orcamento.academico.rest.form.SolicitanteForm;
import com.orcamento.academico.rest.form.SolicitanteUpdateForm;
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
public class SolicitanteService {

  @Autowired
  SolicitanteRepository solicitanteRepository;

  public SolicitanteDto findById(long id) {
    try {
      Optional<SolicitanteModel> solicitanteModel = solicitanteRepository.findById(id);
      if (solicitanteModel.isPresent()) {
        return convertSolicitanteModelToSolicitanteDto(solicitanteModel.get());
      }
      throw new ObjectNotFoundException(
          "Objeto não encontrado! Id: " + id + ", Tipo: " + SolicitanteModel.class.getName());
    } catch (NoSuchElementException e) {
      throw new ObjectNotFoundException(
          "Objeto não encontrado! Id: " + id + ", Tipo: " + SolicitanteModel.class.getName());
    }
  }

  public List<SolicitanteDto> findAll() {
    List<SolicitanteModel> solicitanteList = solicitanteRepository.findAll();
    return convertListToDto(solicitanteList);
  }

  public SolicitanteDto insert(SolicitanteForm solicitanteForm) {
    try {
      SolicitanteModel solicitanteNovo = convertSolicitanteFormToSolicitanteModel(solicitanteForm);
      solicitanteNovo = solicitanteRepository.save(solicitanteNovo);
      return convertSolicitanteModelToSolicitanteDto(solicitanteNovo);
    } catch (DataIntegrityViolationException e) {
      throw new DataIntegrityException("Campo(s) obrigatório(s) do Solicitante não foi(foram) preenchido(s).");
    }
  }

  public SolicitanteDto update(SolicitanteUpdateForm solicitanteUpdateForm, long id) {
    try {
      Optional<SolicitanteModel> solicitanteExistente = solicitanteRepository.findById(id);
      if (solicitanteExistente.isPresent()) {
        SolicitanteModel solicitanteAtualizado = solicitanteExistente.get();
        solicitanteAtualizado.setNome(solicitanteUpdateForm.getNome());
        solicitanteAtualizado.setDataCadastro(solicitanteUpdateForm.getDataCadastro());
        solicitanteAtualizado.setDataAlteracao(solicitanteUpdateForm.getDataAlteracao());
        solicitanteRepository.save(solicitanteAtualizado);
        return convertSolicitanteModelToSolicitanteDto(solicitanteAtualizado);
      } else {
        throw new DataIntegrityException("A id do Solicitante não existe na base de dados!");
      }
    } catch (DataIntegrityViolationException e) {
      throw new DataIntegrityException("Campo(s) obrigatório(s) do Solicitante não foi(foram) preenchido(s).");
    }
  }

  public void delete(long id) {
    try {
      if (solicitanteRepository.existsById(id)) {
        solicitanteRepository.deleteById(id);
      }
    } catch (DataIntegrityViolationException e) {
      throw new DataIntegrityException("Não é possível excluir o Solicitante!");
    }
  }

  private SolicitanteModel convertSolicitanteFormToSolicitanteModel(SolicitanteForm solicitanteForm) {
    SolicitanteModel solicitanteModel = new SolicitanteModel();
    solicitanteModel.setNome(solicitanteForm.getNome());
    solicitanteModel.setDataCadastro(solicitanteForm.getDataCadastro());
    solicitanteModel.setDataAlteracao(solicitanteForm.getDataAlteracao());
    return solicitanteModel;
  }

  private SolicitanteDto convertSolicitanteModelToSolicitanteDto(SolicitanteModel solicitanteModel) {
    SolicitanteDto solicitanteDto = new SolicitanteDto();
    solicitanteDto.setId(solicitanteModel.getId());
    solicitanteDto.setNome(solicitanteModel.getNome());
    solicitanteDto.setDataAlteracao(solicitanteModel.getDataAlteracao());
    solicitanteDto.setDataCadastro(solicitanteModel.getDataCadastro());
    return solicitanteDto;
  }

  private List<SolicitanteDto> convertListToDto(List<SolicitanteModel> list) {
    List<SolicitanteDto> solicitanteDtoList = new ArrayList<>();
    for (SolicitanteModel solicitanteModel : list) {
      SolicitanteDto solicitanteDto = this.convertSolicitanteModelToSolicitanteDto(solicitanteModel);
      solicitanteDtoList.add(solicitanteDto);
    }
    return solicitanteDtoList;
  }
}
