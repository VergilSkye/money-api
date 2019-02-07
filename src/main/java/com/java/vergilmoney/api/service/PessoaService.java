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

    public Pessoa atualizar(Long id, Pessoa pessoa) {
        Pessoa pessoalSalva = buscarPessoaPeloCodigo(id);
        BeanUtils.copyProperties(pessoa, pessoalSalva, "id");
        return pessoaRepository.save(pessoalSalva);
    }

    public void atualizarPropriedadeAtivo(Long id, Boolean ativo) {
        Pessoa pessoalSalva = buscarPessoaPeloCodigo(id);
        pessoalSalva.setAtivo(ativo);
        pessoaRepository.save(pessoalSalva);

    }

    public Pessoa buscarPessoaPeloCodigo(Long id) {
        Optional<Pessoa> pes = pessoaRepository.findById(id);
        if (!pes.isPresent()) {
            throw new EmptyResultDataAccessException(1);
        }

        return pes.get();
    }
}
