package com.orcamento.academico.service;

import com.orcamento.academico.model.ProgramaModel;
import com.orcamento.academico.repository.ProgramaRepository;
import com.orcamento.academico.rest.dto.ProgramaDto;
import com.orcamento.academico.rest.form.programa.ProgramaForm;
import com.orcamento.academico.rest.form.programa.ProgramaUpdateForm;
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
public class ProgramaService {

    @Autowired
    ProgramaRepository programaRepository;

    public ProgramaDto findById(Long id) {
        try {
            Optional<ProgramaModel> programaModel = programaRepository.findById(id);
            if (programaModel.isPresent()) {
                return convertProgramaModelToProgramaDto(programaModel.get());
            }
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + ProgramaModel.class.getName());
        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + ProgramaModel.class.getName());
        }
    }

    public List<ProgramaDto> findAll() {
        List<ProgramaModel> programaList = programaRepository.findAll();
        return convertListToDto(programaList);
    }

    public ProgramaDto insert(ProgramaForm programaForm) {
        try {
            ProgramaModel programaNovo = convertProgramaFormToProgramaModel(programaForm);
            Optional<ProgramaModel> byCodigo = programaRepository.findByCodigo(programaNovo.getCodigo());
            if (byCodigo.isPresent()) {
                throw new IllegalStateException("Codigo já registrado.");
            }
            programaNovo = programaRepository.save(programaNovo);
            return convertProgramaModelToProgramaDto(programaNovo);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do Programa não foi(foram) preenchido(s).");
        }
    }

    public ProgramaDto update(ProgramaUpdateForm programaUpdateForm, Long id) {
        try {
            Optional<ProgramaModel> programaExistente = programaRepository.findById(id);
            if (programaExistente.isPresent()) {
                ProgramaModel programaAtualizado = programaExistente.get();
                programaAtualizado.setNome(programaUpdateForm.getNome());
                programaAtualizado.setCodigo(programaUpdateForm.getCodigo());
                programaAtualizado.setDataCadastro(programaUpdateForm.getDataCadastro());
                programaAtualizado.setDataAlteracao(programaUpdateForm.getDataAlteracao());
                programaAtualizado = programaRepository.save(programaAtualizado);
                return convertProgramaModelToProgramaDto(programaAtualizado);
            } else {
                throw new DataIntegrityException("O id do Programa não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do Programa não foi(foram) preenchido(s).");
        }
    }

    public void delete(Long id) {
        try {
            if (programaRepository.existsById(id)) {
                programaRepository.deleteById(id);
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir um Programa!");
        }
    }

    private ProgramaModel convertProgramaFormToProgramaModel(ProgramaForm programaForm) {
        ProgramaModel programaModel = new ProgramaModel();
        programaModel.setNome(programaForm.getNome());
        programaModel.setCodigo(programaForm.getCodigo());
        programaModel.setDataCadastro(programaForm.getDataCadastro());
        programaModel.setDataAlteracao(programaForm.getDataAlteracao());
        return programaModel;
    }

    private ProgramaDto convertProgramaModelToProgramaDto(ProgramaModel programaModel) {
        ProgramaDto programaDto = new ProgramaDto();
        programaDto.setCodigo(programaModel.getCodigo());
        programaDto.setId(programaModel.getId());
        programaDto.setNome(programaModel.getNome());
        programaDto.setDataAlteracao(programaModel.getDataAlteracao());
        programaDto.setDataCadastro(programaModel.getDataCadastro());
        return programaDto;
    }

    private List<ProgramaDto> convertListToDto(List<ProgramaModel> list) {
        List<ProgramaDto> programaDtoList = new ArrayList<>();
        for (ProgramaModel programaModel : list) {
            ProgramaDto programaDto = this.convertProgramaModelToProgramaDto(programaModel);
            programaDtoList.add(programaDto);
        }
        return programaDtoList;
    }
}
