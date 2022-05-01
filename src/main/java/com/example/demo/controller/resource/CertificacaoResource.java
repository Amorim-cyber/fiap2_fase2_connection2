package com.example.demo.controller.resource;


import com.example.demo.Repository.CertificacaoRepository;
import com.example.demo.exceptions.BusinessException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.model.Certificacao;
import com.example.demo.util.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("certificacao")
public class CertificacaoResource {
    @Autowired
    private CertificacaoRepository certificacaoRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Certificacao> findAll(){
        return certificacaoRepository.findAll();
    }

    @GetMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Certificacao findById(@PathVariable int id){
        return certificacaoRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @GetMapping(value="/{codigo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Certificacao findByCdCertificacao(@PathVariable String codigo){
        return certificacaoRepository.findByCdCertificacao(codigo).orElseThrow(NotFoundException::new);
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Certificacao save(@RequestBody Certificacao certificacao){

        Optional<Certificacao> condominioOptional =
                certificacaoRepository.findByCdCertificacao(certificacao.getCdCertificacao());

        if(condominioOptional.isPresent())
            throw new BusinessException(MessageUtils.CERTIFICACAO_ALREADY_EXISTS);

        return certificacaoRepository.save(certificacao);
    }
}
