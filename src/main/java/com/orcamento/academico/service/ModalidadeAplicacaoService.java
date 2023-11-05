package com.orcamento.academico.service;

import com.orcamento.academico.model.ModalidadeAplicacaoModel;
import com.orcamento.academico.repository.ModalidadeAplicacaoRepository;
import com.orcamento.academico.rest.dto.ModalidadeAplicacaoDto;
import com.orcamento.academico.rest.form.modalidadeAplicacao.ModalidadeAplicacaoForm;
import com.orcamento.academico.rest.form.modalidadeAplicacao.ModalidadeAplicacaoUpdateForm;
import com.orcamento.academico.service.exceptions.DataIntegrityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import com.orcamento.academico.service.exceptions.ObjectNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ModalidadeAplicacaoService {

  @Autowired
  ModalidadeAplicacaoRepository modalidadeAplicacaoRepository;

  public ModalidadeAplicacaoDto findById(long id) {
    try {
      Optional<ModalidadeAplicacaoModel> modalidadeAplicacaoModel = modalidadeAplicacaoRepository.findById(id);
      if (modalidadeAplicacaoModel.isPresent()) {
        return convertModalidadeAplicacaoModelToModalidadeAplicacaoDto(modalidadeAplicacaoModel.get());
      }
      throw new ObjectNotFoundException(
          "Objeto não encontrado! Id: " + id + ", Tipo: " + ModalidadeAplicacaoModel.class.getName());
    } catch (NoSuchElementException e) {
      throw new ObjectNotFoundException(
          "Objeto não encontrado! Id: " + id + ", Tipo: " + ModalidadeAplicacaoModel.class.getName());
    }
  }

  public List<ModalidadeAplicacaoDto> findAll() {
    List<ModalidadeAplicacaoModel> modalidadeAplicacaoList = modalidadeAplicacaoRepository.findAll();
    return convertListToDto(modalidadeAplicacaoList);
  }

  public ModalidadeAplicacaoDto insert(ModalidadeAplicacaoForm modalidadeAplicacaoForm) {
    try {
      ModalidadeAplicacaoModel modalidadeAplicacaoNovo = convertModalidadeAplicacaoFormToModalidadeAplicacaoModel(
          modalidadeAplicacaoForm);
      Optional<ModalidadeAplicacaoModel> byCodigo = modalidadeAplicacaoRepository
          .findByCodigo(modalidadeAplicacaoNovo.getCodigo());
      if (byCodigo.isPresent()) {
        throw new IllegalStateException("Codigo já registrado.");
      }
      modalidadeAplicacaoNovo = modalidadeAplicacaoRepository.save(modalidadeAplicacaoNovo);
      return convertModalidadeAplicacaoModelToModalidadeAplicacaoDto(modalidadeAplicacaoNovo);
    } catch (DataIntegrityViolationException e) {
      throw new DataIntegrityException("Campo(s) obrigatório(s) do Fonte Recurso não foi(foram) preenchido(s).");
    }
  }

  public ModalidadeAplicacaoDto update(ModalidadeAplicacaoUpdateForm modalidadeAplicacaoUpdateForm, long id) {
    try {
      Optional<ModalidadeAplicacaoModel> modalidadeAplicacaoExistente = modalidadeAplicacaoRepository.findById(id);
      if (modalidadeAplicacaoExistente.isPresent()) {
        ModalidadeAplicacaoModel modalidadeAplicacaoAtualizado = modalidadeAplicacaoExistente.get();
        modalidadeAplicacaoAtualizado.setNome(modalidadeAplicacaoUpdateForm.getNome());
        modalidadeAplicacaoAtualizado.setCodigo(modalidadeAplicacaoUpdateForm.getCodigo());
        modalidadeAplicacaoAtualizado.setDataCadastro(modalidadeAplicacaoUpdateForm.getDataCadastro());
        modalidadeAplicacaoAtualizado.setDataAlteracao(modalidadeAplicacaoUpdateForm.getDataAlteracao());
        modalidadeAplicacaoRepository.save(modalidadeAplicacaoAtualizado);
        return convertModalidadeAplicacaoModelToModalidadeAplicacaoDto(modalidadeAplicacaoAtualizado);
      } else {
        throw new DataIntegrityException("A id do Fonte Recurso não existe na base de dados!");
      }
    } catch (DataIntegrityViolationException e) {
      throw new DataIntegrityException("Campo(s) obrigatório(s) do Fonte Recurso não foi(foram) preenchido(s).");
    }
  }

  public void delete(long id) {
    try {
      if (modalidadeAplicacaoRepository.existsById(id)) {
        modalidadeAplicacaoRepository.deleteById(id);
      }
    } catch (DataIntegrityViolationException e) {
      throw new DataIntegrityException("Não é possível excluir um Fonte Recurso!");
    }
  }

  private ModalidadeAplicacaoModel convertModalidadeAplicacaoFormToModalidadeAplicacaoModel(
      ModalidadeAplicacaoForm modalidadeAplicacaoForm) {
    ModalidadeAplicacaoModel modalidadeAplicacaoModel = new ModalidadeAplicacaoModel();
    modalidadeAplicacaoModel.setNome(modalidadeAplicacaoForm.getNome());
    modalidadeAplicacaoModel.setCodigo(modalidadeAplicacaoForm.getCodigo());
    modalidadeAplicacaoModel.setDataCadastro(modalidadeAplicacaoForm.getDataCadastro());
    modalidadeAplicacaoModel.setDataAlteracao(modalidadeAplicacaoForm.getDataAlteracao());
    return modalidadeAplicacaoModel;
  }

  private ModalidadeAplicacaoDto convertModalidadeAplicacaoModelToModalidadeAplicacaoDto(
      ModalidadeAplicacaoModel modalidadeAplicacaoModel) {
    ModalidadeAplicacaoDto modalidadeAplicacaoDto = new ModalidadeAplicacaoDto();
    modalidadeAplicacaoDto.setCodigo(modalidadeAplicacaoModel.getCodigo());
    modalidadeAplicacaoDto.setId(modalidadeAplicacaoModel.getId());
    modalidadeAplicacaoDto.setNome(modalidadeAplicacaoModel.getNome());
    modalidadeAplicacaoDto.setDataAlteracao(modalidadeAplicacaoModel.getDataAlteracao());
    modalidadeAplicacaoDto.setDataCadastro(modalidadeAplicacaoModel.getDataCadastro());
    return modalidadeAplicacaoDto;
  }

  private List<ModalidadeAplicacaoDto> convertListToDto(List<ModalidadeAplicacaoModel> list) {
    List<ModalidadeAplicacaoDto> modalidadeAplicacaoDtoList = new ArrayList<>();
    for (ModalidadeAplicacaoModel modalidadeAplicacaoModel : list) {
      ModalidadeAplicacaoDto modalidadeAplicacaoDto = this
          .convertModalidadeAplicacaoModelToModalidadeAplicacaoDto(modalidadeAplicacaoModel);
      modalidadeAplicacaoDtoList.add(modalidadeAplicacaoDto);
    }
    return modalidadeAplicacaoDtoList;
  }
}
