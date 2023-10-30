package com.orcamento.academico.service;

import com.orcamento.academico.model.AcaoModel;
import com.orcamento.academico.repository.AcaoRepository;
import com.orcamento.academico.rest.dto.AcaoDto;
import com.orcamento.academico.rest.form.acao.AcaoForm;
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
public class AcaoService {

    @Autowired
    AcaoRepository acaoRepository;

    public AcaoDto findById(Integer id) {
        try {
            Optional<AcaoModel> acaoModel = acaoRepository.findById(id);
            if (acaoModel.isPresent()) {
                return convertAcaoModelToAcaoDto(acaoModel.get());
            }
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + AcaoModel.class.getName());
        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + AcaoModel.class.getName());
        }
    }

    public List<AcaoDto> findAll() {
        List<AcaoModel> acaoList = acaoRepository.findAll();
        return convertListToDto(acaoList);
    }

    public AcaoDto insert(AcaoForm acaoForm) {
        try {
            AcaoModel acaoNovo = convertAcaoFormToAcaoModel(acaoForm);
            Optional<AcaoModel> byCodigo = acaoRepository.findByCodigo(acaoNovo.getCodigo());
            if (byCodigo.isPresent()) {
                throw new IllegalStateException("Codigo já registrado.");
            }
            acaoNovo = acaoRepository.save(acaoNovo);
            return convertAcaoModelToAcaoDto(acaoNovo);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) da Ação não foi(foram) preenchido(s).");
        }
    }

    public AcaoDto update(AcaoForm acaoUpdateForm, Integer id) {
        try {
            Optional<AcaoModel> acaoExistente = acaoRepository.findById(id);
            if (acaoExistente.isPresent()) {
                AcaoModel acaoAtualizado = acaoExistente.get();
                acaoAtualizado.setNome(acaoUpdateForm.getNome());
                acaoAtualizado.setCodigo(acaoUpdateForm.getCodigo());
                acaoAtualizado.setDataCadastro(acaoUpdateForm.getDataCadastro());
                acaoAtualizado.setDataAlteracao(acaoUpdateForm.getDataAlteracao());
                acaoAtualizado = acaoRepository.save(acaoAtualizado);
                return convertAcaoModelToAcaoDto(acaoAtualizado);
            } else {
                throw new DataIntegrityException("O id da Ação não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) da Ação não foi(foram) preenchido(s).");
        }
    }

    public void delete(Integer id) {
        try {
            if (acaoRepository.existsById(id)) {
                acaoRepository.deleteById(id);
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir uma Ação!");
        }
    }

    private AcaoModel convertAcaoFormToAcaoModel(AcaoForm acaoForm) {
        AcaoModel acaoModel = new AcaoModel();
        acaoModel.setNome(acaoForm.getNome());
        acaoModel.setCodigo(acaoForm.getCodigo());
        acaoModel.setDataCadastro(acaoForm.getDataCadastro());
        acaoModel.setDataAlteracao(acaoForm.getDataAlteracao());
        return acaoModel;
    }

    private AcaoDto convertAcaoModelToAcaoDto(AcaoModel acaoModel) {
        AcaoDto acaoDto = new AcaoDto();
        acaoDto.setCodigo(acaoModel.getCodigo());
        acaoDto.setId(acaoModel.getId());
        acaoDto.setNome(acaoModel.getNome());
        acaoDto.setDataAlteracao(acaoModel.getDataAlteracao());
        acaoDto.setDataCadastro(acaoModel.getDataCadastro());
        return acaoDto;
    }

    private List<AcaoDto> convertListToDto(List<AcaoModel> list) {
        List<AcaoDto> acaoDtoList = new ArrayList<>();
        for (AcaoModel acaoModel : list) {
            AcaoDto acaoDto = this.convertAcaoModelToAcaoDto(acaoModel);
            acaoDtoList.add(acaoDto);
        }
        return acaoDtoList;
    }
}
