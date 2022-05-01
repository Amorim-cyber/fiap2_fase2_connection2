package com.example.demo.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name="tbl_candidato")
public class Candidato {
    @Id
    @SequenceGenerator(name="candidato",sequenceName="sq_tbl_candidato",allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="candidato")
    @Column(name="id_candidato")
    private int idCandidato;

    @Column(name="nome_candidato",nullable=false,length=100)
    private String nomeCandidato;

    @Column(name="cpf",nullable=false,length=50)
    private String cpf;

    @Column(name="email",nullable=false,length=100)
    private String email;

    @Column(name="telefone",nullable=false,length=50)
    private String telefone;

    @Enumerated(EnumType.STRING)
    @Column(name="genero")
    private Genero genero;

    @Column(name="dt_nasc",nullable=false,length=50)
    private String dtNasc;

    @ManyToMany(cascade=CascadeType.PERSIST)
    @JoinTable(joinColumns = @JoinColumn(name="id_candidato"),
            inverseJoinColumns = @JoinColumn(name="id_skill"), name = "tbl_skill_aux")
    private List<Skill> skills;

    @ManyToMany(cascade=CascadeType.PERSIST)
    @JoinTable(joinColumns = @JoinColumn(name="id_candidato"),
            inverseJoinColumns = @JoinColumn(name="id_certificacao"), name = "tbl_cert_aux")
    private List<Certificacao> certificados;

    public Candidato(){}

    public int getIdCandidato() {
        return idCandidato;
    }

    public void setIdCandidato(int idCandidato) {
        this.idCandidato = idCandidato;
    }

    public String getNomeCandidato() {
        return nomeCandidato;
    }

    public void setNomeCandidato(String nomeCandidato) {
        this.nomeCandidato = nomeCandidato;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public String getDtNasc() {
        return dtNasc;
    }

    public void setDtNasc(String dtNasc) {
        this.dtNasc = dtNasc;
    }

    public String getAllSkills(){
        return Arrays.toString(skills.toArray());
    }

    public int getSizeCertificados(){
        return certificados.size();
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public String getAllCertificados(){
        return Arrays.toString(certificados.toArray());
    }

    public List<Certificacao> getCertificados() {
        return certificados;
    }

    public void setCertificados(List<Certificacao> certificados) {
        this.certificados = certificados;
    }

    @Override
    public String toString() {
        return "Candidato{" +
                "idCandidato=" + idCandidato +
                ", nomeCandidato='" + nomeCandidato + '\'' +
                ", cpf='" + cpf + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                ", genero=" + genero +
                ", dtNasc='" + dtNasc + '\'' +
                ", skills=" + skills +
                ", certificados=" + certificados +
                '}';
    }
}
