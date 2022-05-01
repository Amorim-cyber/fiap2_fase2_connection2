package com.example.demo.Repository;

import com.example.demo.model.Candidato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CandidatoRepository extends JpaRepository<Candidato,Integer> {
    Optional<Candidato> findByCpf(String cpf);
}
