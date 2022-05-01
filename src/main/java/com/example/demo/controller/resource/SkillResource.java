package com.example.demo.controller.resource;


import com.example.demo.Repository.SkillRepository;
import com.example.demo.exceptions.BusinessException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.model.Skill;
import com.example.demo.util.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("skill")
public class SkillResource {

    @Autowired
    private SkillRepository skillRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Skill> findAll(){
        return skillRepository.findAll();
    }

    @GetMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Skill findById(@PathVariable int id){
        return skillRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @GetMapping(value="/{nome}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Skill findByNomeSkill(@PathVariable String nome){
        return skillRepository.findByNomeSkill(nome).orElseThrow(NotFoundException::new);
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Skill save(@RequestBody Skill skill){

        Optional<Skill> skillOptional =
                skillRepository.findByNomeSkill(skill.getNomeSkill());

        if(skillOptional.isPresent())
            throw new BusinessException(MessageUtils.SKILL_ALREADY_EXISTS);

        return skillRepository.save(skill);
    }

}
