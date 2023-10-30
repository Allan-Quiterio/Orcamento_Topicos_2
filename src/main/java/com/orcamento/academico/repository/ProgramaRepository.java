package com.orcamento.academico.repository;

import com.orcamento.academico.model.ProgramaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProgramaRepository extends JpaRepository<ProgramaModel, Integer> {
    Optional<ProgramaModel> findByCodigo(Integer codigo);

    List<ProgramaModel> findByOrderByCodigoDesc();

    Optional<ProgramaModel> findByNomeContaining(String nome);
}
