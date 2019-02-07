package com.java.vergilmoney.api.service;

import com.java.vergilmoney.api.model.Lancamento;
import com.java.vergilmoney.api.model.Pessoa;
import com.java.vergilmoney.api.repository.LancamentoRepository;
import com.java.vergilmoney.api.repository.PessoaRepository;
import com.java.vergilmoney.api.service.exception.PessoaInexistenteOuInativaException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class LancamentoService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private LancamentoRepository lancamentoRepository;


    public Lancamento salvar(Lancamento lancamento) {
        validarPessoa(lancamento);

        return lancamentoRepository.save(lancamento);
    }

    public Lancamento atualizar(Long id, Lancamento lancamento) {
        Lancamento lancamentoSalvo = buscarLancamentoExistente(id);
        if (!lancamento.getPessoa().equals(lancamentoSalvo.getPessoa())) {
            validarPessoa(lancamento);
        }

        BeanUtils.copyProperties(lancamento, lancamentoSalvo, "id");

        return lancamentoRepository.save(lancamentoSalvo);
    }
    private void validarPessoa(Lancamento lancamento) {
        Pessoa pessoa = null;
        if (lancamento.getPessoa().getId() != null) {
            Optional<Pessoa> pes = pessoaRepository.findById(lancamento.getPessoa().getId());
            if(!pes.isPresent()){
                throw new EmptyResultDataAccessException(1);
            }
            else{
                pessoa = pes.get();
            }
        }

        if (pessoa == null || pessoa.isInativo()) {
            throw new PessoaInexistenteOuInativaException();
        }
    }

    private Lancamento buscarLancamentoExistente(Long id) {
        Optional <Lancamento> lanc = lancamentoRepository.findById(id);
        if (!lanc.isPresent()) {
            throw new EmptyResultDataAccessException(1);
        }

        return lanc.get();
    }

}



