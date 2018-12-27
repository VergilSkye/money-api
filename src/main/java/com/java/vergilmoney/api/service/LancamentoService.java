package com.java.vergilmoney.api.service;

import com.java.vergilmoney.api.model.Lancamento;
import com.java.vergilmoney.api.model.Pessoa;
import com.java.vergilmoney.api.repository.LancamentoRepository;
import com.java.vergilmoney.api.repository.PessoaRepository;
import com.java.vergilmoney.api.service.exception.PessoaInexistenteOuInativaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class LancamentoService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private LancamentoRepository lancamentoRepository;

    public Lancamento salvar(Lancamento lancamento) {

        Optional<Pessoa> pes = pessoaRepository.findById(lancamento.getPessoa().getId());
        System.out.println(lancamento.getPessoa().getId() + "AAAAAAAAAAAA");
        Pessoa pessoa;
        if (pes.isPresent()) {
            pessoa = pes.get();
        } else {
            pessoa = null;
        }

        if (pessoa == null || pessoa.isInativo()) {
            throw new PessoaInexistenteOuInativaException();
        }

        return lancamentoRepository.save(lancamento);
    }

}



