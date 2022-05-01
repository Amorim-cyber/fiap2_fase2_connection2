package com.example.demo.Repository;

import com.example.demo.model.Certificacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CertificacaoRepository extends JpaRepository<Certificacao,Integer> {
    Optional<Certificacao> findByCdCertificacao(String codigo);
}
