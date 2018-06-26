/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.ceavi.meuprojeto.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author marcoscamargo
 */
@Entity
@Table
public class RecursoHumano implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int IdRecursoHumano;

    @Column
    private String nome;

    @Column
    private String email;

    @Column
    private String telefone;

    @ManyToMany(mappedBy = "pessoas")
    private List<Atividade> atividades;

    public RecursoHumano(int IdRecursoHumano, String nome, String email, String telefone, List<Atividade> atividades) {
        this.IdRecursoHumano = IdRecursoHumano;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.atividades = atividades;
    }

    public RecursoHumano() {
        this.IdRecursoHumano = 0;
        this.nome = "";
        this.email = "";
        this.telefone = "";
        this.atividades = new ArrayList<>();
    }

    public int getIdRecursoHumano() {
        return IdRecursoHumano;
    }

    public void setIdRecursoHumano(int IdRecursoHumano) {
        this.IdRecursoHumano = IdRecursoHumano;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public List<Atividade> getAtividades() {
        return atividades;
    }

    public void setAtividades(List<Atividade> atividades) {
        this.atividades = atividades;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.IdRecursoHumano;
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
        final RecursoHumano other = (RecursoHumano) obj;
        if (this.IdRecursoHumano != other.IdRecursoHumano) {
            return false;
        }
        return true;
    }

}
