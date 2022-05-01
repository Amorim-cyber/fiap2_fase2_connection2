package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name="tbl_skill")
public class Skill {
    @Id
    @SequenceGenerator(name="skill",sequenceName="sq_tbl_skill",allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="skill")
    @Column(name="id_skill")
    private int idSkill;

    @Column(name="nome_skill",nullable=false,length=100)
    private String nomeSkill;

    public Skill() {
    }

    public int getIdSkill() {
        return idSkill;
    }

    public void setIdSkill(int idSkill) {
        this.idSkill = idSkill;
    }

    public String getNomeSkill() {
        return nomeSkill;
    }

    public void setNomeSkill(String nomeSkill) {
        this.nomeSkill = nomeSkill;
    }

    @Override
    public String toString() {
        return nomeSkill;
    }


}
