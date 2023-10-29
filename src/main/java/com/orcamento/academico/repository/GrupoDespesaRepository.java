package com.orcamento.academico.repository;

import com.orcamento.academico.model.GrupoDespesaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GrupoDespesaRepository extends JpaRepository<GrupoDespesaModel, Long> {
    Optional<GrupoDespesaModel> findByCodigo(Float codigo);

    List<GrupoDespesaModel> findByOrderByCodigoDesc();

    Optional<GrupoDespesaModel> findByNomeContaining(String nome);
}
