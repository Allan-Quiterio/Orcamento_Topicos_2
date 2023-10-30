package com.orcamento.academico.repository;

import com.orcamento.academico.model.AcaoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AcaoRepository extends JpaRepository<AcaoModel, Integer> {
    Optional<AcaoModel> findByCodigo(Integer codigo);

    List<AcaoModel> findByOrderByCodigoDesc();

    Optional<AcaoModel> findByNomeContaining(String nome);
}
