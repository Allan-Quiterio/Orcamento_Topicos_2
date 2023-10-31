package com.orcamento.academico.repository;

import com.orcamento.academico.model.LancamentosModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LancamentosRepository extends JpaRepository<LancamentosModel, Integer> {
    Optional<LancamentosModel> findById(Integer id);

    List<LancamentosModel> findByOrderByIdDesc();

}
