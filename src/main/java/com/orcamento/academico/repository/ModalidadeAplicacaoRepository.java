package com.orcamento.academico.repository;

import com.orcamento.academico.model.ModalidadeAplicacaoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface ModalidadeAplicacaoRepository extends JpaRepository<ModalidadeAplicacaoModel, Long> {

    Optional<ModalidadeAplicacaoModel> findByCodigo(Float codigo);

    List<ModalidadeAplicacaoModel> findByOrderByCodigoDesc();

    Optional<ModalidadeAplicacaoModel> findByNomeContaining(String nome);
}
