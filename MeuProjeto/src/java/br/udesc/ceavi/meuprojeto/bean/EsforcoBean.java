/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.ceavi.meuprojeto.bean;

import br.udesc.ceavi.meuprojeto.model.Esforco;
import br.udesc.ceavi.meuprojeto.service.EsforcoService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author marcoscamargo
 */
@SessionScoped
@ManagedBean
public class EsforcoBean implements Serializable {

    private Esforco esforco;
    private List<Esforco> esforcos;
    private List<Esforco> esforcosSelecionados;
    private EsforcoService esforcoService;

    @PostConstruct
    public void init() {
        this.esforco = new Esforco();
        this.esforcos = new ArrayList<>();
        this.esforcosSelecionados = new ArrayList<>();
        this.esforcoService = new EsforcoService();
    }

    public void cadastrar() {
        try {
            if (verificaCampos()) {
                this.esforcoService.insert(this.esforco);
                addMessage(FacesMessage.SEVERITY_INFO, "Perfeito", "Salvo com sucesso");
                limparDados();
            } else {
                addMessage(FacesMessage.SEVERITY_WARN, "Campos Nulos", "Preencha todos os campos!");
            }
        } catch (Exception ex) {
            Logger.getLogger(EsforcoBean.class.getName()).log(Level.SEVERE, null, ex);
            addMessage(FacesMessage.SEVERITY_ERROR, "Ops", "Erro ao salvar");
        }
    }

    public String editar() {
        try {
            this.esforcoService.edit(this.esforco);
            addMessage(FacesMessage.SEVERITY_INFO, "Perfeito", "Editado com sucesso");
            limparDados();
            return "esforco.jsf";
        } catch (Exception ex) {
            Logger.getLogger(UsuarioBean.class.getName()).log(Level.SEVERE, null, ex);
            addMessage(FacesMessage.SEVERITY_ERROR, "Ops", "Erro ao editar");
        }
        return "edit_esforco.jsf";
    }

    public String remover() {
        if (verificaSelecao() == true) {
            return "esforco.jsf";
        }
        for (Esforco esforcosSelecionado : esforcosSelecionados) {
            try {
                this.esforcoService.remove(esforcosSelecionado.getIdEsforco());
            } catch (Exception ex) {
                Logger.getLogger(UsuarioBean.class.getName()).log(Level.SEVERE, null, ex);
                addMessage(FacesMessage.SEVERITY_ERROR, "Ops", "Erro ao remover");
            }
        }
        this.esforcos.removeAll(this.esforcosSelecionados);
        addMessage(FacesMessage.SEVERITY_INFO, "Perfeito", "Removido com sucesso");
        limparDados();
        return "esforco.jsf";
    }

    public String preparaEditar() {
        return verificaSelecao() ? "esforco.jsf" : "edit_esforco.jsf";
    }

    public boolean verificaSelecao() {
        if (!esforcosSelecionados.isEmpty()) {
            this.esforco = esforcosSelecionados.get(0);
        } else {
            addMessage(FacesMessage.SEVERITY_ERROR, "Ops", "Selecione algum esforco");
            return true;
        }
        return false;
    }

    public void limparDados() {
        this.esforco = new Esforco();
    }

    public void addMessage(FacesMessage.Severity fc, String titulo, String descricao) {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage();
        message.setSeverity(fc);
        message.setSummary("<strong>" + titulo + "</strong>");
        message.setDetail(descricao);
        context.addMessage(null, message);
    }

    private boolean verificaCampos() {
        if (this.esforco.getNumeroHoras() <= 0.0) {
            return false;
        }
        return !this.esforco.getTamanho().equalsIgnoreCase("");
    }

    public Esforco getEsforco() {
        return esforco;
    }

    public void setEsforco(Esforco esforco) {
        this.esforco = esforco;
    }

    public List<Esforco> getEsforcos() {
        this.esforcos = this.esforcoService.listar();
        return esforcos;
    }

    public void setEsforcos(List<Esforco> esforcos) {
        this.esforcos = esforcos;
    }

    public List<Esforco> getEsforcosSelecionados() {
        return esforcosSelecionados;
    }

    public void setEsforcosSelecionados(List<Esforco> esforcosSelecionados) {
        this.esforcosSelecionados = esforcosSelecionados;
    }

    public EsforcoService getEsforcoService() {
        return esforcoService;
    }

    public void setEsforcoService(EsforcoService esforcoService) {
        this.esforcoService = esforcoService;
    }

}
