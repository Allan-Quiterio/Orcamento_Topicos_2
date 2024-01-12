package com.orcamento.academico.service;

import com.orcamento.academico.model.LancamentosModel;
import com.orcamento.academico.repository.LancamentosRepository;
import com.orcamento.academico.rest.dto.LancamentosDto;
import com.orcamento.academico.rest.form.lancamentos.LancamentosForm;
import com.orcamento.academico.rest.form.lancamentos.LancamentosUpdateForm;
import com.orcamento.academico.service.exceptions.DataIntegrityException;
import com.orcamento.academico.service.exceptions.Exception;
import com.orcamento.academico.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class LancamentosService {
    @Autowired
    LancamentosRepository lancamentosRepository;

    public LancamentosDto findById(Integer id) {
        try {
            Optional<LancamentosModel> lancamentosModel = lancamentosRepository.findById(id);
            if (lancamentosModel.isPresent()) {
                return convertLancamentoModelToLancamentoDto(lancamentosModel.get());
            }
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + LancamentosModel.class.getName());
        } catch (NoSuchElementException e) {
            throw new Exception("Ocorreu um erro interno ao processar a solicitação");
        }
    }

    public List<LancamentosDto> findAll() {
        List<LancamentosModel> lancamentoList = lancamentosRepository.findAll();
        return convertListToDto(lancamentoList);
    }

    public LancamentosDto insert(LancamentosForm lancamentosForm) {
        try {
            LancamentosModel lancamentosNovo = convertLancamentoFormToLancamentoModel(lancamentosForm);

            lancamentosNovo = lancamentosRepository.save(lancamentosNovo);
            return convertLancamentoModelToLancamentoDto(lancamentosNovo);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) de Lançamentos não foi(foram) preenchido(s).");
        }
    }

    public LancamentosDto update(LancamentosUpdateForm lancamentosUpdateForm, Integer id) {
        try {
            Optional<LancamentosModel> lancamentosExistente = lancamentosRepository.findById(id);
            String dataCadastro = lancamentosExistente.get().getDataCadastro();
            if (lancamentosExistente.isPresent()) {
                LancamentosModel lancamentosAtualizado = lancamentosExistente.get();
                lancamentosAtualizado.setLancamentoInvalido(lancamentosUpdateForm.getLancamentoInvalido());
                lancamentosAtualizado.setNumeroLancamento(lancamentosUpdateForm.getNumeroLancamento());
                lancamentosAtualizado.setIdTipoLancamento(lancamentosUpdateForm.getIdTipoLancamento());
                lancamentosAtualizado.setDataLancamento(lancamentosUpdateForm.getDataLancamento());
                lancamentosAtualizado.setIdLancamentoPai(lancamentosUpdateForm.getIdLancamentoPai());
                lancamentosAtualizado.setIdUnidade(lancamentosUpdateForm.getIdUnidade());
                lancamentosAtualizado.setDescricao(lancamentosUpdateForm.getDescricao());
                lancamentosAtualizado.setIdUnidadeOrcamentaria(lancamentosUpdateForm.getIdUnidadeOrcamentaria());
                lancamentosAtualizado.setIdPrograma(lancamentosUpdateForm.getIdPrograma());
                lancamentosAtualizado.setIdAcao(lancamentosUpdateForm.getIdAcao());
                lancamentosAtualizado.setIdFonteRecurso(lancamentosUpdateForm.getIdFonteRecurso());
                lancamentosAtualizado.setIdGrupoDespesa(lancamentosUpdateForm.getIdGrupoDespesa());
                lancamentosAtualizado.setIdModalidadeAplicacao(lancamentosUpdateForm.getIdModalidadeAplicacao());
                lancamentosAtualizado.setIdElementoDespesa(lancamentosUpdateForm.getIdElementoDespesa());
                lancamentosAtualizado.setIdSolicitante(lancamentosUpdateForm.getIdSolicitante());
                lancamentosAtualizado.setGed(lancamentosUpdateForm.getGed());
                lancamentosAtualizado.setContratado(lancamentosUpdateForm.getContratado());
                lancamentosAtualizado.setIdObjetivoEstrategico(lancamentosUpdateForm.getIdObjetivoEstrategico());
                lancamentosAtualizado.setValor(lancamentosUpdateForm.getValor());
                lancamentosAtualizado.setIdTipoTransacao(lancamentosUpdateForm.getIdTipoTransacao());
                lancamentosAtualizado.setDataCadastro(dataCadastro);

                LocalDateTime currentDateTime = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
                        .withZone(ZoneId.of("America/Sao_Paulo"));
                String formattedDate = currentDateTime.format(formatter);

                lancamentosAtualizado.setDataAlteracao(formattedDate);
                lancamentosAtualizado.setAnoOrcamento(lancamentosUpdateForm.getAnoOrcamento());
                lancamentosAtualizado = lancamentosRepository.save(lancamentosAtualizado);
                return convertLancamentoModelToLancamentoDto(lancamentosAtualizado);
            } else {
                throw new DataIntegrityException("O id de Lançamentos não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) de Lançamentos não foi(foram) preenchido(s).");
        }
    }

    public void delete(Integer id) {
        try {
            if (lancamentosRepository.existsById(id)) {
                lancamentosRepository.deleteById(id);
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir um Lançamento!");
        }
    }

    private LancamentosModel convertLancamentoFormToLancamentoModel(LancamentosForm lancamentosForm) {
        LancamentosModel lancamentosModel = new LancamentosModel();
        lancamentosModel.setLancamentoInvalido(lancamentosForm.getLancamentoInvalido());
        lancamentosModel.setNumeroLancamento(lancamentosForm.getNumeroLancamento());
        lancamentosModel.setIdTipoLancamento(lancamentosForm.getIdTipoLancamento());
        lancamentosModel.setDataLancamento(lancamentosForm.getDataLancamento());
        lancamentosModel.setIdLancamentoPai(lancamentosForm.getIdLancamentoPai());
        lancamentosModel.setIdUnidade(lancamentosForm.getIdUnidade());
        lancamentosModel.setDescricao(lancamentosForm.getDescricao());
        lancamentosModel.setIdUnidadeOrcamentaria(lancamentosForm.getIdUnidadeOrcamentaria());
        lancamentosModel.setIdPrograma(lancamentosForm.getIdPrograma());
        lancamentosModel.setIdAcao(lancamentosForm.getIdAcao());
        lancamentosModel.setIdFonteRecurso(lancamentosForm.getIdFonteRecurso());
        lancamentosModel.setIdGrupoDespesa(lancamentosForm.getIdGrupoDespesa());
        lancamentosModel.setIdModalidadeAplicacao(lancamentosForm.getIdModalidadeAplicacao());
        lancamentosModel.setIdElementoDespesa(lancamentosForm.getIdElementoDespesa());
        lancamentosModel.setIdSolicitante(lancamentosForm.getIdSolicitante());
        lancamentosModel.setGed(lancamentosForm.getGed());
        lancamentosModel.setContratado(lancamentosForm.getContratado());
        lancamentosModel.setIdObjetivoEstrategico(lancamentosForm.getIdObjetivoEstrategico());
        lancamentosModel.setValor(lancamentosForm.getValor());
        lancamentosModel.setIdTipoTransacao(lancamentosForm.getIdTipoTransacao());

        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
                .withZone(ZoneId.of("America/Sao_Paulo"));
        String formattedDate = currentDateTime.format(formatter);

        lancamentosModel.setDataCadastro(formattedDate);
        lancamentosModel.setDataAlteracao(formattedDate);
        lancamentosModel.setAnoOrcamento(lancamentosForm.getAnoOrcamento());
        return lancamentosModel;
    }

    private LancamentosDto convertLancamentoModelToLancamentoDto(LancamentosModel lancamentosModel) {
        LancamentosDto lancamentosDto = new LancamentosDto();
        lancamentosDto.setId(lancamentosModel.getId());
        lancamentosDto.setLancamentoInvalido(lancamentosModel.getLancamentoInvalido());
        lancamentosDto.setNumeroLancamento(lancamentosModel.getNumeroLancamento());
        lancamentosDto.setIdTipoLancamento(lancamentosModel.getIdTipoLancamento());
        lancamentosDto.setDataLancamento(lancamentosModel.getDataLancamento());
        lancamentosDto.setIdLancamentoPai(lancamentosModel.getIdLancamentoPai());
        lancamentosDto.setIdUnidade(lancamentosModel.getIdUnidade());
        lancamentosDto.setDescricao(lancamentosModel.getDescricao());
        lancamentosDto.setIdUnidadeOrcamentaria(lancamentosModel.getIdUnidadeOrcamentaria());
        lancamentosDto.setIdPrograma(lancamentosModel.getIdPrograma());
        lancamentosDto.setIdAcao(lancamentosModel.getIdAcao());
        lancamentosDto.setIdFonteRecurso(lancamentosModel.getIdFonteRecurso());
        lancamentosDto.setIdGrupoDespesa(lancamentosModel.getIdGrupoDespesa());
        lancamentosDto.setIdModalidadeAplicacao(lancamentosModel.getIdModalidadeAplicacao());
        lancamentosDto.setIdElementoDespesa(lancamentosModel.getIdElementoDespesa());
        lancamentosDto.setIdSolicitante(lancamentosModel.getIdSolicitante());
        lancamentosDto.setGed(lancamentosModel.getGed());
        lancamentosDto.setContratado(lancamentosModel.getContratado());
        lancamentosDto.setIdObjetivoEstrategico(lancamentosModel.getIdObjetivoEstrategico());
        lancamentosDto.setValor(lancamentosModel.getValor());
        lancamentosDto.setIdTipoTransacao(lancamentosModel.getIdTipoTransacao());
        lancamentosDto.setDataCadastro(lancamentosModel.getDataCadastro());
        lancamentosDto.setDataAlteracao(lancamentosModel.getDataAlteracao());
        lancamentosDto.setAnoOrcamento(lancamentosModel.getAnoOrcamento());
        return lancamentosDto;
    }

    private List<LancamentosDto> convertListToDto(List<LancamentosModel> list) {
        List<LancamentosDto> lancamentosDtoList = new ArrayList<>();
        for (LancamentosModel lancamentosModel : list) {
            LancamentosDto lancamentosDto = this.convertLancamentoModelToLancamentoDto(lancamentosModel);
            lancamentosDtoList.add(lancamentosDto);
        }
        return lancamentosDtoList;
    }

}
