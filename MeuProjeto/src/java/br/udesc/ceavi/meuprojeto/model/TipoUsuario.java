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
public enum TipoUsuario {
    ADM("ADMINISTRADOR"), VISUALIZAR("VISUALIZAR");

    public String desc;

    TipoUsuario(String desc) {
        this.desc = desc;
    }
    
}
