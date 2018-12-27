package com.java.vergilmoney.api.resource;

import com.java.vergilmoney.api.event.RecursoCriadoEvent;
import com.java.vergilmoney.api.exceptionhandler.MoneyExceptionHandler;
import com.java.vergilmoney.api.model.Lancamento;
import com.java.vergilmoney.api.repository.LancamentoRepository;
import com.java.vergilmoney.api.repository.filter.LancamentoFilter;
import com.java.vergilmoney.api.service.LancamentoService;
import com.java.vergilmoney.api.service.exception.PessoaInexistenteOuInativaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {

    @Autowired
    private LancamentoRepository lancamentoRepository;

    @Autowired
    private LancamentoService lancamentoService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private MessageSource messageSource;

    @GetMapping
    public List<Lancamento> pesquisar(LancamentoFilter lancamentoFilter) {
        return lancamentoRepository.filtrar(lancamentoFilter);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Lancamento> buscarPeloCodigo(@PathVariable long codigo) {

        Optional<Lancamento> lac = lancamentoRepository.findById(codigo);
        if (lac.isPresent()) {
            Lancamento lancamento = lac.get();
            return ResponseEntity.ok(lancamento);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Lancamento> criar(@Valid @RequestBody Lancamento lancamento, HttpServletResponse response) {
        Lancamento lancamentoSalvo = lancamentoService.salvar(lancamento);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, lancamentoSalvo.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoSalvo);
    }

    @ExceptionHandler({PessoaInexistenteOuInativaException.class })
    public ResponseEntity<Object> handlePessoaInexistenteOuInativaException(PessoaInexistenteOuInativaException ex ){
        String mensagemUsuario = messageSource.getMessage("pessoa.inexistente-ou-inativa", null, LocaleContextHolder.getLocale());
        String mensagemDev = ex.toString();
        List<MoneyExceptionHandler.Erro> erros = Arrays.asList(new MoneyExceptionHandler.Erro(mensagemUsuario, mensagemDev));
        return ResponseEntity.badRequest().body(erros);
    }

}
