package com.orcamento.academico.repository;

import com.orcamento.academico.model.TipoLancamentoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TipoLancamentoRepository extends JpaRepository<TipoLancamentoModel, Long> {

    List<TipoLancamentoModel> findByOrderByNomeDesc();

    Optional<TipoLancamentoModel> findByNomeContaining(String nome);
}
