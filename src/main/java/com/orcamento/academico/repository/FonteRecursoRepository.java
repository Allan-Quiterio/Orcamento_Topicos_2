package com.orcamento.academico.repository;

import com.orcamento.academico.model.FonteRecursoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FonteRecursoRepository extends JpaRepository<FonteRecursoModel, Long> {
    Optional<FonteRecursoModel> findByCodigo(Float codigo);

    List<FonteRecursoModel> findByOrderByCodigoDesc();

    Optional<FonteRecursoModel> findByNomeContaining(String nome);
}
