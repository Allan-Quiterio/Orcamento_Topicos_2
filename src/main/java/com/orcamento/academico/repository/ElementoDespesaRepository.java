package com.orcamento.academico.repository;

import com.orcamento.academico.model.ElementoDespesaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ElementoDespesaRepository extends JpaRepository<ElementoDespesaModel, Integer> {
    Optional<ElementoDespesaModel> findByCodigo(Integer codigo);

    List<ElementoDespesaModel> findByOrderByCodigoDesc();

    Optional<ElementoDespesaModel> findByNomeContaining(String nome);
}
