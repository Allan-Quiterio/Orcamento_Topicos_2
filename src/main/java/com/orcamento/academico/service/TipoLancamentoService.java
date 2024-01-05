package com.orcamento.academico.service;

import com.orcamento.academico.model.TipoLancamentoModel;
import com.orcamento.academico.repository.TipoLancamentoRepository;
import com.orcamento.academico.rest.dto.TipoLancamentoDto;
import com.orcamento.academico.rest.form.tipoLancamento.TipoLancamentoForm;
import com.orcamento.academico.rest.form.tipoLancamento.TipoLancamentoUpdateForm;
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
public class TipoLancamentoService {

  @Autowired
  TipoLancamentoRepository tipoLancamentoRepository;

  public TipoLancamentoDto findById(long id) {
    try {
      Optional<TipoLancamentoModel> tipoLancamentoModel = tipoLancamentoRepository.findById(id);
      if (tipoLancamentoModel.isPresent()) {
        return convertTipoLancamentoModelToTipoLancamentoDto(tipoLancamentoModel.get());
      }
      throw new ObjectNotFoundException(
          "Objeto não encontrado! Id: " + id + ", Tipo: " + TipoLancamentoModel.class.getName());
    } catch (NoSuchElementException e) {
      throw new Exception("Ocorreu um erro interno ao processar a solicitação");
    }
  }

  public List<TipoLancamentoDto> findAll() {
    List<TipoLancamentoModel> tipoLancamentoList = tipoLancamentoRepository.findAll();
    return convertListToDto(tipoLancamentoList);
  }

  public TipoLancamentoDto insert(TipoLancamentoForm tipoLancamentoForm) {
    try {
      TipoLancamentoModel tipoLancamentoNovo = convertTipoLancamentoFormToTipoLancamentoModel(tipoLancamentoForm);
      tipoLancamentoNovo = tipoLancamentoRepository.save(tipoLancamentoNovo);
      return convertTipoLancamentoModelToTipoLancamentoDto(tipoLancamentoNovo);
    } catch (DataIntegrityViolationException e) {
      throw new DataIntegrityException("Campo(s) obrigatório(s) não foi(foram) preenchido(s).");
    }
  }

  public TipoLancamentoDto update(TipoLancamentoUpdateForm tipoLancamentoUpdateForm, long id) {
    try {
      Optional<TipoLancamentoModel> tipoLancamentoExistente = tipoLancamentoRepository.findById(id);
      if (tipoLancamentoExistente.isPresent()) {
        TipoLancamentoModel tipoLancamentoAtualizado = tipoLancamentoExistente.get();
        tipoLancamentoAtualizado.setNome(tipoLancamentoUpdateForm.getNome());
        tipoLancamentoAtualizado.setDataCadastro(tipoLancamentoUpdateForm.getDataCadastro());
        tipoLancamentoAtualizado.setDataAlteracao(tipoLancamentoUpdateForm.getDataAlteracao());
        tipoLancamentoRepository.save(tipoLancamentoAtualizado);
        return convertTipoLancamentoModelToTipoLancamentoDto(tipoLancamentoAtualizado);
      } else {
        throw new DataIntegrityException("A id da Unidade não existe na base de dados!");
      }
    } catch (DataIntegrityViolationException e) {
      throw new DataIntegrityException("Campo(s) obrigatório(s) da Unidade não foi(foram) preenchido(s).");
    }
  }

  public void delete(long id) {
    try {
      if (tipoLancamentoRepository.existsById(id)) {
        tipoLancamentoRepository.deleteById(id);
      }
    } catch (DataIntegrityViolationException e) {
      throw new DataIntegrityException("Não é possível excluir o Tipo de Lançamento!");
    }
  }

  private TipoLancamentoModel convertTipoLancamentoFormToTipoLancamentoModel(TipoLancamentoForm tipoLancamentoForm) {
    TipoLancamentoModel tipoLancamentoModel = new TipoLancamentoModel();
    tipoLancamentoModel.setNome(tipoLancamentoForm.getNome());
    tipoLancamentoModel.setDataCadastro(tipoLancamentoForm.getDataCadastro());
    tipoLancamentoModel.setDataAlteracao(tipoLancamentoForm.getDataAlteracao());
    return tipoLancamentoModel;
  }

  private TipoLancamentoDto convertTipoLancamentoModelToTipoLancamentoDto(TipoLancamentoModel tipoLancamentoModel) {
    TipoLancamentoDto tipoLancamentoDto = new TipoLancamentoDto();
    tipoLancamentoDto.setId(tipoLancamentoModel.getId());
    tipoLancamentoDto.setNome(tipoLancamentoModel.getNome());
    tipoLancamentoDto.setDataAlteracao(tipoLancamentoModel.getDataAlteracao());
    tipoLancamentoDto.setDataCadastro(tipoLancamentoModel.getDataCadastro());
    return tipoLancamentoDto;
  }

  private List<TipoLancamentoDto> convertListToDto(List<TipoLancamentoModel> list) {
    List<TipoLancamentoDto> tipoLancamentoDtoList = new ArrayList<>();
    for (TipoLancamentoModel tipoLancamentoModel : list) {
      TipoLancamentoDto tipoLancamentoDto = this.convertTipoLancamentoModelToTipoLancamentoDto(tipoLancamentoModel);
      tipoLancamentoDtoList.add(tipoLancamentoDto);
    }
    return tipoLancamentoDtoList;
  }
}
