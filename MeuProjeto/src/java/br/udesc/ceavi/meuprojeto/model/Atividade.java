/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.ceavi.meuprojeto.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author marcoscamargo
 */
@NamedQueries({
    @NamedQuery(name = "atividade.porSituacao",
            query = "SELECT u FROM Atividade as u Where (u.status = :situacao)"),
    @NamedQuery(name = "atividade.atrasada",
            query = "SELECT u FROM Atividade as u Where (u.dataPrevista <= :dataAgora AND u.status <> :situacao)"),
    @NamedQuery(name = "atividade.concluidaAntesPrazo",
            query = "SELECT u FROM Atividade as u Where (u.dataPrevista > u.dataTermino AND u.status = :situacao)")
})
@Entity
@Table
public class Atividade implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int IdAtividade;
    
    @Enumerated(EnumType.STRING)
    private Tipo tipo;
    
    @Enumerated(EnumType.STRING)
    private Situacao status;
    
    @ManyToMany
    private List<RecursoHumano> pessoas;
    
    @OneToOne
    private Esforco esforco;

    @Temporal(TemporalType.DATE)
    @Column
    private Date dataInicio;

    @Temporal(TemporalType.DATE)
    @Column
    private Date dataPrevista;
    
    @Temporal(TemporalType.DATE)
    @Column
    private Date dataTermino;

    @Column
    private String titulo;
    
    @Column
    private String descricao;
    
    @Column
    private String observacao;

    public Atividade() {
        esforco = new Esforco();
        pessoas = new ArrayList<>();
    }
    
    

    public int getIdAtividade() {
        return IdAtividade;
    }

    public void setIdAtividade(int IdAtividade) {
        this.IdAtividade = IdAtividade;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Situacao getStatus() {
        return status;
    }

    public void setStatus(Situacao status) {
        this.status = status;
    }

    public List<RecursoHumano> getPessoas() {
        return pessoas;
    }

    public void setPessoas(List<RecursoHumano> pessoas) {
        this.pessoas = pessoas;
    }

    public Esforco getEsforco() {
        return esforco;
    }

    public void setEsforco(Esforco esforco) {
        this.esforco = esforco;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataPrevista() {
        return dataPrevista;
    }

    public void setDataPrevista(Date dataPrevista) {
        this.dataPrevista = dataPrevista;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(Date dataTermino) {
        this.dataTermino = dataTermino;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + this.IdAtividade;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Atividade other = (Atividade) obj;
        if (this.IdAtividade != other.IdAtividade) {
            return false;
        }
        return true;
    }
    
    

}
