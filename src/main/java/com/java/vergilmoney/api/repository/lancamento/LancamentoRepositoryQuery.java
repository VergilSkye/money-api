package com.java.vergilmoney.api.repository.lancamento;

import com.java.vergilmoney.api.model.Lancamento;
import com.java.vergilmoney.api.repository.filter.LancamentoFilter;

import java.util.List;

public interface LancamentoRepositoryQuery {

    public List<Lancamento> filtrar(LancamentoFilter lancamentoFilter);

}
