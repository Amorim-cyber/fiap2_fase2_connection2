package com.example.demo.controller;


import com.example.demo.controller.resource.CandidatoResource;
import com.example.demo.controller.resource.CertificacaoResource;
import com.example.demo.controller.resource.SkillResource;
import com.example.demo.exceptions.BusinessException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.model.Candidato;
import com.example.demo.model.Certificacao;
import com.example.demo.model.Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("home")
public class MainController {

    @Autowired
    private SkillResource skillResource = new SkillResource();

    @Autowired
    private CertificacaoResource certificacaoResource = new CertificacaoResource();

    @Autowired
    private CandidatoResource candidatoResource = new CandidatoResource();

    @GetMapping()
    public String open(Model model, Candidato candidato){
        model.addAttribute("skills",skillResource.findAll());
        model.addAttribute("candidatos",candidatoResource.findAll());
        return "home";
    }

    @PostMapping("novoCandidato")
    public String reopen(Model model, Candidato candidato, HttpServletRequest request){

        String[] namesOfSkills = {
                request.getParameter("skillSelected1"),
                request.getParameter("skillSelected2"),
                request.getParameter("skillSelected3"),
                request.getParameter("skillSelected4"),
                request.getParameter("skillSelected5")
        };

        candidato.setSkills(new ArrayList<Skill>());

        for (String skillName: namesOfSkills) {
            if(skillName.equals(""))
                continue;
            candidato.getSkills().add(skillResource.findByNomeSkill(skillName));
        }

        System.err.println(candidato);
        System.err.println(candidato.getCertificados());

        candidato.setCertificados(
        candidato.getCertificados().stream()
                .filter(x->!x.getCdCertificacao().isBlank())
                .collect(Collectors.toList())
        );

        System.err.println(candidato);
        try {
            candidatoResource.save(candidato);
        }catch(BusinessException e){
            model.addAttribute("error","Candidato ou Cetificação já existente no banco tente novamente!");
            model.addAttribute("skills",skillResource.findAll());
            model.addAttribute("candidatos",candidatoResource.findAll());
            return "home";
        }
        model.addAttribute("skills",skillResource.findAll());
        model.addAttribute("candidatos",candidatoResource.findAll());
        return "home";
    }
}
