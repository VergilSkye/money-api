package com.java.vergilmoney.api.repository.lancamento;

import com.java.vergilmoney.api.model.Lancamento;
import com.java.vergilmoney.api.repository.filter.LancamentoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LancamentoRepositoryQuery {

    public Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable);

}
