package com.orcamento.academico.repository;

import com.orcamento.academico.model.UnidadeOrcamentariaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UnidadeOrcamentariaRepository extends JpaRepository<UnidadeOrcamentariaModel, Integer> {
    Optional<UnidadeOrcamentariaModel> findByCodigo(Integer codigo);

    List<UnidadeOrcamentariaModel> findByOrderByCodigoDesc();

    Optional<UnidadeOrcamentariaModel> findByNomeContaining(String nome);
}
