package com.orcamento.academico.repository;

import com.orcamento.academico.model.SolicitanteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SolicitanteRepository extends JpaRepository<SolicitanteModel, Long> {

    List<SolicitanteModel> findByOrderByNomeDesc();

    Optional<SolicitanteModel> findByNomeContaining(String nome);
}
