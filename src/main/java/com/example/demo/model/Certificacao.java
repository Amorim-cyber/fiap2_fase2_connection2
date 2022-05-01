package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name="tbl_certificacao")
public class Certificacao {
    @Id
    @SequenceGenerator(name="certificacao",sequenceName="sq_tbl_certificacao",allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="certificacao")
    @Column(name="id_certificacao")
    private int idCertificacao;

    @Column(name="cd_certificacao",nullable=false,length=100)
    private String cdCertificacao;

    @Column(name="dt_conclusao",nullable=false,length=50)
    private String dtConclusao;

    @JoinColumn(name = "id_tipo_cert")
    @ManyToOne(cascade= CascadeType.PERSIST)
    private TipoDeCertificacao tipo;

    public Certificacao() {
    }

    public int getIdCertificacao() {
        return idCertificacao;
    }

    public void setIdCertificacao(int idCertificacao) {
        this.idCertificacao = idCertificacao;
    }

    public String getCdCertificacao() {
        return cdCertificacao;
    }

    public void setCdCertificacao(String cdCertificacao) {
        this.cdCertificacao = cdCertificacao;
    }

    public String getDtConclusao() {
        return dtConclusao;
    }

    public void setDtConclusao(String dtConclusao) {
        this.dtConclusao = dtConclusao;
    }

    public TipoDeCertificacao getTipo() {
        return tipo;
    }

    public void setTipo(TipoDeCertificacao tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Código = " + cdCertificacao +
                "| Nome = " + tipo.getNomeTipoCert() +
                "| Conclusão = " + dtConclusao ;
    }
}
