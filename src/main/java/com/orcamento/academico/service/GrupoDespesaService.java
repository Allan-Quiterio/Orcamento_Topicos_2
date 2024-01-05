package com.orcamento.academico.service;

import com.orcamento.academico.model.GrupoDespesaModel;
import com.orcamento.academico.repository.GrupoDespesaRepository;
import com.orcamento.academico.rest.dto.GrupoDespesaDto;
import com.orcamento.academico.rest.form.grupoDespesa.GrupoDespesaForm;
import com.orcamento.academico.rest.form.grupoDespesa.GrupoDespesaUpdateForm;
import com.orcamento.academico.service.exceptions.DataIntegrityException;
import com.orcamento.academico.service.exceptions.Exception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import com.orcamento.academico.service.exceptions.ObjectNotFoundException;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class GrupoDespesaService {

  @Autowired
  GrupoDespesaRepository grupoDespesaRepository;

  public GrupoDespesaDto findById(long id) {
    try {
      Optional<GrupoDespesaModel> grupoDespesaModel = grupoDespesaRepository.findById(id);
      if (grupoDespesaModel.isPresent()) {
        return convertGrupoDespesaModelToGrupoDespesaDto(grupoDespesaModel.get());
      }
      throw new ObjectNotFoundException(
          "Objeto não encontrado! Id: " + id + ", Tipo: " + GrupoDespesaModel.class.getName());
    } catch (NoSuchElementException e) {
      throw new Exception("Ocorreu um erro interno ao processar a solicitação");
    }
  }

  public List<GrupoDespesaDto> findAll() {
    List<GrupoDespesaModel> grupoDespesaList = grupoDespesaRepository.findAll();
    return convertListToDto(grupoDespesaList);
  }

  public GrupoDespesaDto insert(GrupoDespesaForm grupoDespesaForm) {
    try {
      GrupoDespesaModel grupoDespesaNovo = convertGrupoDespesaFormToGrupoDespesaModel(grupoDespesaForm);
      Optional<GrupoDespesaModel> byCodigo = grupoDespesaRepository.findByCodigo(grupoDespesaNovo.getCodigo());
      if (byCodigo.isPresent()) {
        throw new IllegalStateException("Codigo já registrado.");
      }
      grupoDespesaNovo = grupoDespesaRepository.save(grupoDespesaNovo);
      return convertGrupoDespesaModelToGrupoDespesaDto(grupoDespesaNovo);
    } catch (DataIntegrityViolationException e) {
      throw new DataIntegrityException("Campo(s) obrigatório(s) do Fonte Recurso não foi(foram) preenchido(s).");
    }
  }

  public GrupoDespesaDto update(GrupoDespesaUpdateForm grupoDespesaUpdateForm, long id) {
    try {
      Optional<GrupoDespesaModel> grupoDespesaExistente = grupoDespesaRepository.findById(id);
      String dataCadastro = grupoDespesaExistente.get().getDataCadastro();
      if (grupoDespesaExistente.isPresent()) {
        GrupoDespesaModel grupoDespesaAtualizado = grupoDespesaExistente.get();
        grupoDespesaAtualizado.setNome(grupoDespesaUpdateForm.getNome());
        grupoDespesaAtualizado.setCodigo(grupoDespesaUpdateForm.getCodigo());
        grupoDespesaAtualizado.setDataCadastro(dataCadastro);

        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
                .withZone(ZoneId.of("America/Sao_Paulo"));
        String formattedDate = currentDateTime.format(formatter);

        grupoDespesaAtualizado.setDataAlteracao(formattedDate);
        grupoDespesaRepository.save(grupoDespesaAtualizado);
        return convertGrupoDespesaModelToGrupoDespesaDto(grupoDespesaAtualizado);
      } else {
        throw new DataIntegrityException("A id do Fonte Recurso não existe na base de dados!");
      }
    } catch (DataIntegrityViolationException e) {
      throw new DataIntegrityException("Campo(s) obrigatório(s) do Fonte Recurso não foi(foram) preenchido(s).");
    }
  }

  public void delete(long id) {
    try {
      if (grupoDespesaRepository.existsById(id)) {
        grupoDespesaRepository.deleteById(id);
      }
    } catch (DataIntegrityViolationException e) {
      throw new DataIntegrityException("Não é possível excluir um Fonte Recurso!");
    }
  }

  private GrupoDespesaModel convertGrupoDespesaFormToGrupoDespesaModel(GrupoDespesaForm grupoDespesaForm) {
    GrupoDespesaModel grupoDespesaModel = new GrupoDespesaModel();
    grupoDespesaModel.setNome(grupoDespesaForm.getNome());
    grupoDespesaModel.setCodigo(grupoDespesaForm.getCodigo());

    LocalDateTime currentDateTime = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
            .withZone(ZoneId.of("America/Sao_Paulo"));
    String formattedDate = currentDateTime.format(formatter);

    grupoDespesaModel.setDataCadastro(formattedDate);
    grupoDespesaModel.setDataAlteracao(formattedDate);
    return grupoDespesaModel;
  }

  private GrupoDespesaDto convertGrupoDespesaModelToGrupoDespesaDto(GrupoDespesaModel grupoDespesaModel) {
    GrupoDespesaDto grupoDespesaDto = new GrupoDespesaDto();
    grupoDespesaDto.setCodigo(grupoDespesaModel.getCodigo());
    grupoDespesaDto.setId(grupoDespesaModel.getId());
    grupoDespesaDto.setNome(grupoDespesaModel.getNome());
    grupoDespesaDto.setDataAlteracao(grupoDespesaModel.getDataAlteracao());
    grupoDespesaDto.setDataCadastro(grupoDespesaModel.getDataCadastro());
    return grupoDespesaDto;
  }

  private List<GrupoDespesaDto> convertListToDto(List<GrupoDespesaModel> list) {
    List<GrupoDespesaDto> grupoDespesaDtoList = new ArrayList<>();
    for (GrupoDespesaModel grupoDespesaModel : list) {
      GrupoDespesaDto grupoDespesaDto = this.convertGrupoDespesaModelToGrupoDespesaDto(grupoDespesaModel);
      grupoDespesaDtoList.add(grupoDespesaDto);
    }
    return grupoDespesaDtoList;
  }
}
