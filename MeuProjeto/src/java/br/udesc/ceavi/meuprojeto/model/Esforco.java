/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.ceavi.meuprojeto.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author marcoscamargo
 */
@Entity
@Table
public class Esforco implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int IdEsforco;
    @Column
    private String tamanho;
    @Column
    private double numeroHoras;

    public int getIdEsforco() {
        return IdEsforco;
    }

    public void setIdEsforco(int IdEsforco) {
        this.IdEsforco = IdEsforco;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public double getNumeroHoras() {
        return numeroHoras;
    }

    public void setNumeroHoras(double numeroHoras) {
        this.numeroHoras = numeroHoras;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.IdEsforco;
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
        final Esforco other = (Esforco) obj;
        if (this.IdEsforco != other.IdEsforco) {
            return false;
        }
        return true;
    }
    
    
    
}
