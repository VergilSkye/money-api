package com.java.vergilmoney.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.vergilmoney.api.model.Lancamento;
import com.java.vergilmoney.api.repository.lancamento.LancamentoRepositoryQuery;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>, LancamentoRepositoryQuery {

}