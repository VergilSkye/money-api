package com.java.vergilmoney.api.service;

import com.java.vergilmoney.api.model.Pessoa;
import com.java.vergilmoney.api.repository.PessoaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public Pessoa atualizar(Long codigo, Pessoa pessoa) {
        Pessoa pessoalSalva = buscarPessoaPeloCodigo(codigo);
        BeanUtils.copyProperties(pessoa, pessoalSalva, "id");
        return pessoaRepository.save(pessoalSalva);
    }

    public void atualizarPropriedadeAtivo(Long codigo, Boolean ativo) {
        Pessoa pessoalSalva = buscarPessoaPeloCodigo(codigo);
        pessoalSalva.setAtivo(ativo);
        pessoaRepository.save(pessoalSalva);

    }

    private Pessoa buscarPessoaPeloCodigo(Long codigo) {
        Optional<Pessoa> pes = pessoaRepository.findById(codigo);
        if (!pes.isPresent()) {
            throw new EmptyResultDataAccessException(1);
        }

        return pes.get();
    }
}
