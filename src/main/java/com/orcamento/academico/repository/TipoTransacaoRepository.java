package com.orcamento.academico.repository;

import com.orcamento.academico.model.TipoTransacaoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface TipoTransacaoRepository extends JpaRepository<TipoTransacaoModel, Long> {

    List<TipoTransacaoModel> findByOrderByNomeDesc();

    Optional<TipoTransacaoModel> findByNomeContaining(String nome);
}
