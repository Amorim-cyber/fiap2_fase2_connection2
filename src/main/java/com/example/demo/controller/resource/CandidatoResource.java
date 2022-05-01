package com.example.demo.controller.resource;


import com.example.demo.Repository.CandidatoRepository;
import com.example.demo.exceptions.BusinessException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.model.Candidato;
import com.example.demo.util.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("candidato")
public class CandidatoResource {

    @Autowired
    private CandidatoRepository candidatoRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Candidato> findAll(){
        return candidatoRepository.findAll()
                .stream()
                .sorted(Comparator.comparingInt(Candidato::getSizeCertificados).reversed())
                .collect(Collectors.toList());
    }

    @GetMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Candidato findById(@PathVariable int id){
        return candidatoRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @GetMapping(value="/{cpf}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Candidato findByCpf(@PathVariable String cpf){
        return candidatoRepository.findByCpf(cpf).orElseThrow(NotFoundException::new);
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Candidato save(@RequestBody Candidato candidato){

        Optional<Candidato> condominioOptional =
                candidatoRepository.findByCpf(candidato.getCpf());

        if(condominioOptional.isPresent())
            throw new BusinessException(MessageUtils.CANDIDATO_ALREADY_EXISTS);

        return candidatoRepository.save(candidato);
    }

}
