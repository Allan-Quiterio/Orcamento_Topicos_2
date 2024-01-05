package com.orcamento.academico.service;

import com.orcamento.academico.model.FonteRecursoModel;
import com.orcamento.academico.repository.FonteRecursoRepository;
import com.orcamento.academico.rest.dto.FonteRecursoDto;
import com.orcamento.academico.rest.form.fonteRecurso.FonteRecursoForm;
import com.orcamento.academico.rest.form.fonteRecurso.FonteRecursoUpdateForm;
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
public class FonteRecursoService {

    @Autowired
    FonteRecursoRepository fonteRecursoRepository;

    public FonteRecursoDto findById(long id) {
        try {
            Optional<FonteRecursoModel> fonteRecursoModel = fonteRecursoRepository.findById(id);
            if (fonteRecursoModel.isPresent()) {
                return convertFonteRecursoModelToFonteRecursoDto(fonteRecursoModel.get());
            }
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + FonteRecursoModel.class.getName());
        } catch (NoSuchElementException e) {
            throw new Exception("Ocorreu um erro interno ao processar a solicitação");
        }
    }

    public List<FonteRecursoDto> findAll() {
        List<FonteRecursoModel> fonteRecursoList = fonteRecursoRepository.findAll();
        return convertListToDto(fonteRecursoList);
    }

    public FonteRecursoDto insert(FonteRecursoForm fonteRecursoForm) {
        try {
            FonteRecursoModel fonteRecursoNovo = convertFonteRecursoFormToFonteRecursoModel(fonteRecursoForm);
            Optional<FonteRecursoModel> byCodigo = fonteRecursoRepository.findByCodigo(fonteRecursoNovo.getCodigo());
            if (byCodigo.isPresent()) {
                throw new IllegalStateException("Codigo já registrado.");
            }
            fonteRecursoNovo = fonteRecursoRepository.save(fonteRecursoNovo);
            return convertFonteRecursoModelToFonteRecursoDto(fonteRecursoNovo);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do Fonte Recurso não foi(foram) preenchido(s).");
        }
    }

    public FonteRecursoDto update(FonteRecursoUpdateForm fonteRecursoUpdateForm, long id) {
        try {
            Optional<FonteRecursoModel> fonteRecursoExistente = fonteRecursoRepository.findById(id);
            if (fonteRecursoExistente.isPresent()) {
                FonteRecursoModel fonteRecursoAtualizado = fonteRecursoExistente.get();
                fonteRecursoAtualizado.setNome(fonteRecursoUpdateForm.getNome());
                fonteRecursoAtualizado.setCodigo(fonteRecursoUpdateForm.getCodigo());
                fonteRecursoAtualizado.setDataCadastro(fonteRecursoUpdateForm.getDataCadastro());
                fonteRecursoAtualizado.setDataAlteracao(fonteRecursoUpdateForm.getDataAlteracao());
                fonteRecursoRepository.save(fonteRecursoAtualizado);
                return convertFonteRecursoModelToFonteRecursoDto(fonteRecursoAtualizado);
            } else {
                throw new DataIntegrityException("A id do Fonte Recurso não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do Fonte Recurso não foi(foram) preenchido(s).");
        }
    }

    public void delete(long id) {
        try {
            if (fonteRecursoRepository.existsById(id)) {
                fonteRecursoRepository.deleteById(id);
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir um Fonte Recurso!");
        }
    }

    private FonteRecursoModel convertFonteRecursoFormToFonteRecursoModel(FonteRecursoForm fonteRecursoForm) {
        FonteRecursoModel fonteRecursoModel = new FonteRecursoModel();
        fonteRecursoModel.setNome(fonteRecursoForm.getNome());
        fonteRecursoModel.setCodigo(fonteRecursoForm.getCodigo());
        fonteRecursoModel.setDataCadastro(fonteRecursoForm.getDataCadastro());
        fonteRecursoModel.setDataAlteracao(fonteRecursoForm.getDataAlteracao());
        return fonteRecursoModel;
    }

    private FonteRecursoDto convertFonteRecursoModelToFonteRecursoDto(FonteRecursoModel fonteRecursoModel) {
        FonteRecursoDto fonteRecursoDto = new FonteRecursoDto();
        fonteRecursoDto.setCodigo(fonteRecursoModel.getCodigo());
        fonteRecursoDto.setId(fonteRecursoModel.getId());
        fonteRecursoDto.setNome(fonteRecursoModel.getNome());
        fonteRecursoDto.setDataAlteracao(fonteRecursoModel.getDataAlteracao());
        fonteRecursoDto.setDataCadastro(fonteRecursoModel.getDataCadastro());
        return fonteRecursoDto;
    }

    private List<FonteRecursoDto> convertListToDto(List<FonteRecursoModel> list) {
        List<FonteRecursoDto> fonteRecursoDtoList = new ArrayList<>();
        for (FonteRecursoModel fonteRecursoModel : list) {
            FonteRecursoDto fonteRecursoDto = this.convertFonteRecursoModelToFonteRecursoDto(fonteRecursoModel);
            fonteRecursoDtoList.add(fonteRecursoDto);
        }
        return fonteRecursoDtoList;
    }
}
