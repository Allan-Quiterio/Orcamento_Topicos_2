package com.orcamento.academico.service;

import com.orcamento.academico.model.ObjetivoEstrategicoModel;
import com.orcamento.academico.repository.ObjetivoEstrategicoRepository;
import com.orcamento.academico.rest.dto.ObjetivoEstrategicoDto;
import com.orcamento.academico.rest.form.objetivoEstrategico.ObjetivoEstrategicoForm;
import com.orcamento.academico.rest.form.objetivoEstrategico.ObjetivoEstrategicoUpdateForm;
import com.orcamento.academico.service.exceptions.DataIntegrityException;
import com.orcamento.academico.service.exceptions.Exception;
import com.orcamento.academico.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ObjetivoEstrategicoService {

  @Autowired
  ObjetivoEstrategicoRepository objetivoEstrategicoRepository;

  public ObjetivoEstrategicoDto findById(long id) {
    try {
      Optional<ObjetivoEstrategicoModel> objetivoEstrategicoModel = objetivoEstrategicoRepository.findById(id);
      if (objetivoEstrategicoModel.isPresent()) {
        return convertObjetivoEstrategicoModelToObjetivoEstrategicoDto(objetivoEstrategicoModel.get());
      }
      throw new ObjectNotFoundException(
          "Objeto não encontrado! Id: " + id + ", Tipo: " + ObjetivoEstrategicoModel.class.getName());
    } catch (NoSuchElementException e) {
      throw new Exception("Ocorreu um erro interno ao processar a solicitação");
    }
  }

  public List<ObjetivoEstrategicoDto> findAll() {
    List<ObjetivoEstrategicoModel> objetivoEstrategicoList = objetivoEstrategicoRepository.findAll();
    return convertListToDto(objetivoEstrategicoList);
  }

  public ObjetivoEstrategicoDto insert(ObjetivoEstrategicoForm objetivoEstrategicoForm) {
    try {
      ObjetivoEstrategicoModel objetivoEstrategicoNovo = convertObjetivoEstrategicoFormToObjetivoEstrategicoModel(objetivoEstrategicoForm);
      objetivoEstrategicoNovo = objetivoEstrategicoRepository.save(objetivoEstrategicoNovo);
      return convertObjetivoEstrategicoModelToObjetivoEstrategicoDto(objetivoEstrategicoNovo);
    } catch (DataIntegrityViolationException e) {
      throw new DataIntegrityException("Campo(s) obrigatório(s) do Objetivo Estrategico não foi(foram) preenchido(s).");
    }
  }

  public ObjetivoEstrategicoDto update(ObjetivoEstrategicoUpdateForm objetivoEstrategicoUpdateForm, long id) {
    try {
      Optional<ObjetivoEstrategicoModel> objetivoEstrategicoExistente = objetivoEstrategicoRepository.findById(id);
      if (objetivoEstrategicoExistente.isPresent()) {
        ObjetivoEstrategicoModel objetivoEstrategicoAtualizado = objetivoEstrategicoExistente.get();
        objetivoEstrategicoAtualizado.setNome(objetivoEstrategicoUpdateForm.getNome());
        objetivoEstrategicoAtualizado.setDataCadastro(objetivoEstrategicoUpdateForm.getDataCadastro());
        objetivoEstrategicoAtualizado.setDataAlteracao(objetivoEstrategicoUpdateForm.getDataAlteracao());
        objetivoEstrategicoRepository.save(objetivoEstrategicoAtualizado);
        return convertObjetivoEstrategicoModelToObjetivoEstrategicoDto(objetivoEstrategicoAtualizado);
      } else {
        throw new DataIntegrityException("A id do Objetivo Estrategico não existe na base de dados!");
      }
    } catch (DataIntegrityViolationException e) {
      throw new DataIntegrityException("Campo(s) obrigatório(s) do Objetivo Estrategico não foi(foram) preenchido(s).");
    }
  }

  public void delete(long id) {
    try {
      if (objetivoEstrategicoRepository.existsById(id)) {
        objetivoEstrategicoRepository.deleteById(id);
      }
    } catch (DataIntegrityViolationException e) {
      throw new DataIntegrityException("Não é possível excluir o Objetivo Estrategico!");
    }
  }

  private ObjetivoEstrategicoModel convertObjetivoEstrategicoFormToObjetivoEstrategicoModel(ObjetivoEstrategicoForm objetivoEstrategicoForm) {
    ObjetivoEstrategicoModel objetivoEstrategicoModel = new ObjetivoEstrategicoModel();
    objetivoEstrategicoModel.setNome(objetivoEstrategicoForm.getNome());
    objetivoEstrategicoModel.setDataCadastro(objetivoEstrategicoForm.getDataCadastro());
    objetivoEstrategicoModel.setDataAlteracao(objetivoEstrategicoForm.getDataAlteracao());
    return objetivoEstrategicoModel;
  }

  private ObjetivoEstrategicoDto convertObjetivoEstrategicoModelToObjetivoEstrategicoDto(ObjetivoEstrategicoModel objetivoEstrategicoModel) {
    ObjetivoEstrategicoDto objetivoEstrategicoDto = new ObjetivoEstrategicoDto();
    objetivoEstrategicoDto.setId(objetivoEstrategicoModel.getId());
    objetivoEstrategicoDto.setNome(objetivoEstrategicoModel.getNome());
    objetivoEstrategicoDto.setDataAlteracao(objetivoEstrategicoModel.getDataAlteracao());
    objetivoEstrategicoDto.setDataCadastro(objetivoEstrategicoModel.getDataCadastro());
    return objetivoEstrategicoDto;
  }

  private List<ObjetivoEstrategicoDto> convertListToDto(List<ObjetivoEstrategicoModel> list) {
    List<ObjetivoEstrategicoDto> objetivoEstrategicoDtoList = new ArrayList<>();
    for (ObjetivoEstrategicoModel objetivoEstrategicoModel : list) {
      ObjetivoEstrategicoDto objetivoEstrategicoDto = this.convertObjetivoEstrategicoModelToObjetivoEstrategicoDto(objetivoEstrategicoModel);
      objetivoEstrategicoDtoList.add(objetivoEstrategicoDto);
    }
    return objetivoEstrategicoDtoList;
  }
}
