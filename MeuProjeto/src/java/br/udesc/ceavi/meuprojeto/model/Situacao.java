/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.ceavi.meuprojeto.model;

/**
 *
 * @author marcoscamargo
 */
public enum Situacao {
    INICIADA("INICIADA"), NAO_INICIADA("NÃO INICIADA"), CONCLUIDA("CONCLUÍDA");

    public String desc;

    Situacao(String desc) {
        this.desc = desc;
    }

//    @Override
//    public String toString() {
//        return desc;
//    }

    
}
