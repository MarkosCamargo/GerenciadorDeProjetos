/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.ceavi.meuprojeto.model;

import java.io.Serializable;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author marcoscamargo
 */
@NamedQueries({
    @NamedQuery(name = "usuario.login",
            query = "SELECT u FROM Usuario as u Where (u.pessoa.email = :email) and (u.senha = :senha)")
})
@Entity
@Table
@Cacheable(false)
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int IdUsuario;
    @Column
    private String senha;
    @OneToOne(cascade = CascadeType.ALL)
    private RecursoHumano pessoa;

    @Enumerated(EnumType.STRING)
    private TipoUsuario tipoUsuario;

    public Usuario(int IdUsuario, String senha, RecursoHumano pessoa, TipoUsuario tipoUsuario) {
        this.IdUsuario = IdUsuario;
        this.senha = senha;
        this.pessoa = pessoa;
        this.tipoUsuario = tipoUsuario;
    }

    public Usuario() {
        this.IdUsuario = 0;
        this.pessoa= new RecursoHumano();
        this.senha = "";
    }
    
    

    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int IdUsuario) {
        this.IdUsuario = IdUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public RecursoHumano getPessoa() {
        return pessoa;
    }

    public void setPessoa(RecursoHumano pessoa) {
        this.pessoa = pessoa;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + this.IdUsuario;
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
        final Usuario other = (Usuario) obj;
        if (this.IdUsuario != other.IdUsuario) {
            return false;
        }
        return true;
    }

}
