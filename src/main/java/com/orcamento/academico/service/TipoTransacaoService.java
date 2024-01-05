package com.orcamento.academico.service;

import com.orcamento.academico.model.TipoTransacaoModel;
import com.orcamento.academico.repository.TipoTransacaoRepository;
import com.orcamento.academico.rest.dto.TipoTransacaoDto;
import com.orcamento.academico.rest.form.tipoTransacao.TipoTransacaoForm;
import com.orcamento.academico.rest.form.tipoTransacao.TipoTransacaoUpdateForm;
import com.orcamento.academico.service.exceptions.DataIntegrityException;
import com.orcamento.academico.service.exceptions.Exception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import com.orcamento.academico.service.exceptions.ObjectNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TipoTransacaoService {

  @Autowired
  TipoTransacaoRepository tipoTransacaoRepository;

  public TipoTransacaoDto findById(long id) {
    try {
      Optional<TipoTransacaoModel> tipoTransacaoModel = tipoTransacaoRepository.findById(id);
      if (tipoTransacaoModel.isPresent()) {
        return convertTipoTransacaoModelToTipoTransacaoDto(tipoTransacaoModel.get());
      }
      throw new ObjectNotFoundException(
          "Objeto não encontrado! Id: " + id + ", Tipo: " + TipoTransacaoModel.class.getName());
    } catch (NoSuchElementException e) {
      throw new Exception("Ocorreu um erro interno ao processar a solicitação");
    }
  }

  public List<TipoTransacaoDto> findAll() {
    List<TipoTransacaoModel> tipoTransacaoList = tipoTransacaoRepository.findAll();
    return convertListToDto(tipoTransacaoList);
  }

  public TipoTransacaoDto insert(TipoTransacaoForm tipoTransacaoForm) {
    try {
      TipoTransacaoModel tipoTransacaoNovo = convertTipoTransacaoFormToTipoTransacaoModel(tipoTransacaoForm);
      tipoTransacaoNovo = tipoTransacaoRepository.save(tipoTransacaoNovo);
      return convertTipoTransacaoModelToTipoTransacaoDto(tipoTransacaoNovo);
    } catch (DataIntegrityViolationException e) {
      throw new DataIntegrityException("Campo(s) obrigatório(s) do Fonte Recurso não foi(foram) preenchido(s).");
    }
  }

  public TipoTransacaoDto update(TipoTransacaoUpdateForm tipoTransacaoUpdateForm, long id) {
    try {
      Optional<TipoTransacaoModel> tipoTransacaoExistente = tipoTransacaoRepository.findById(id);
      if (tipoTransacaoExistente.isPresent()) {
        TipoTransacaoModel tipoTransacaoAtualizado = tipoTransacaoExistente.get();
        tipoTransacaoAtualizado.setNome(tipoTransacaoUpdateForm.getNome());
        tipoTransacaoAtualizado.setDataCadastro(tipoTransacaoUpdateForm.getDataCadastro());
        tipoTransacaoAtualizado.setDataAlteracao(tipoTransacaoUpdateForm.getDataAlteracao());
        tipoTransacaoRepository.save(tipoTransacaoAtualizado);
        return convertTipoTransacaoModelToTipoTransacaoDto(tipoTransacaoAtualizado);
      } else {
        throw new DataIntegrityException("A id do Fonte Recurso não existe na base de dados!");
      }
    } catch (DataIntegrityViolationException e) {
      throw new DataIntegrityException("Campo(s) obrigatório(s) do Fonte Recurso não foi(foram) preenchido(s).");
    }
  }

  public void delete(long id) {
    try {
      if (tipoTransacaoRepository.existsById(id)) {
        tipoTransacaoRepository.deleteById(id);
      }
    } catch (DataIntegrityViolationException e) {
      throw new DataIntegrityException("Não é possível excluir um Fonte Recurso!");
    }
  }

  private TipoTransacaoModel convertTipoTransacaoFormToTipoTransacaoModel(TipoTransacaoForm tipoTransacaoForm) {
    TipoTransacaoModel tipoTransacaoModel = new TipoTransacaoModel();
    tipoTransacaoModel.setNome(tipoTransacaoForm.getNome());
    tipoTransacaoModel.setDataCadastro(tipoTransacaoForm.getDataCadastro());
    tipoTransacaoModel.setDataAlteracao(tipoTransacaoForm.getDataAlteracao());
    return tipoTransacaoModel;
  }

  private TipoTransacaoDto convertTipoTransacaoModelToTipoTransacaoDto(TipoTransacaoModel tipoTransacaoModel) {
    TipoTransacaoDto tipoTransacaoDto = new TipoTransacaoDto();
    tipoTransacaoDto.setId(tipoTransacaoModel.getId());
    tipoTransacaoDto.setNome(tipoTransacaoModel.getNome());
    tipoTransacaoDto.setDataAlteracao(tipoTransacaoModel.getDataAlteracao());
    tipoTransacaoDto.setDataCadastro(tipoTransacaoModel.getDataCadastro());
    return tipoTransacaoDto;
  }

  private List<TipoTransacaoDto> convertListToDto(List<TipoTransacaoModel> list) {
    List<TipoTransacaoDto> tipoTransacaoDtoList = new ArrayList<>();
    for (TipoTransacaoModel tipoTransacaoModel : list) {
      TipoTransacaoDto tipoTransacaoDto = this.convertTipoTransacaoModelToTipoTransacaoDto(tipoTransacaoModel);
      tipoTransacaoDtoList.add(tipoTransacaoDto);
    }
    return tipoTransacaoDtoList;
  }
}
