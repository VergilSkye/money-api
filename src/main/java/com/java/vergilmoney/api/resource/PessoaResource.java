package com.java.vergilmoney.api.resource;

import com.java.vergilmoney.api.event.RecursoCriadoEvent;
import com.java.vergilmoney.api.model.Categoria;
import com.java.vergilmoney.api.model.Pessoa;
import com.java.vergilmoney.api.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping
    public List<Pessoa> listar() {
        return pessoaRepository.findAll();

    }

    @PostMapping
    public ResponseEntity<Pessoa> criar(@Valid @RequestBody Pessoa pessoa, HttpServletResponse response) {
        Pessoa pessoaSalva = pessoaRepository.save(pessoa);


        publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoaSalva.getId()));

        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);

    }


    @GetMapping("/{codigo}")
    public ResponseEntity<Pessoa> buscarPeloCodigo(@PathVariable Long codigo) {

        Optional<Pessoa> pes = pessoaRepository.findById(codigo);
        if (pes.isPresent()) {
            Pessoa pessoa = pes.get();
            return ResponseEntity.ok(pessoa);
        } else {
            return ResponseEntity.notFound().build();
        }

    }
}
