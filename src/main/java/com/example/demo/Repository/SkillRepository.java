package com.example.demo.Repository;

import com.example.demo.model.Candidato;
import com.example.demo.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SkillRepository extends JpaRepository<Skill,Integer> {
    Optional<Skill> findByNomeSkill(String nome);
}
