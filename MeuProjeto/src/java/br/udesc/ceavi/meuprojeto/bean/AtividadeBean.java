/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.ceavi.meuprojeto.bean;

import br.udesc.ceavi.meuprojeto.model.Atividade;
import br.udesc.ceavi.meuprojeto.model.Esforco;
import br.udesc.ceavi.meuprojeto.model.RecursoHumano;
import br.udesc.ceavi.meuprojeto.model.Usuario;
import br.udesc.ceavi.meuprojeto.model.Situacao;
import br.udesc.ceavi.meuprojeto.model.Tipo;
import br.udesc.ceavi.meuprojeto.model.TipoUsuario;
import br.udesc.ceavi.meuprojeto.service.AtividadeService;
import br.udesc.ceavi.meuprojeto.service.EsforcoService;
import br.udesc.ceavi.meuprojeto.service.UsuarioService;
import br.udesc.ceavi.meuprojeto.util.sessao.ControleSessao;
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
import org.primefaces.context.RequestContext;

/**
 *
 * @author marcoscamargo
 */
@SessionScoped
@ManagedBean
public class AtividadeBean implements Serializable {

    private Atividade atividade;
    private List<Atividade> atividades;
    private List<Atividade> atividadesAtrasadas;
    private List<Atividade> atividadesConcluidasAntesPrazo;
    private List<Atividade> atividadesSelecionados;
    private AtividadeService atividadeService;

    private List<Usuario> usuariosAux;
    private List<Usuario> usuariosSelecionados;
    private UsuarioService usuarioService;

    private List<Esforco> esforcosAux;
    private EsforcoService esforcoService;

    private String tagsUsuario;
    private String[] usuarios;
    private String[] esforcos;

    private int contAtividadeAberta;
    private int contAtividadeNaoIniciada;
    private int contAtividadeConcluida;
    private double totalHorasAtividades;

    @PostConstruct
    public void init() {
        this.atividade = new Atividade();
        this.atividades = new ArrayList<>();
        this.atividadesSelecionados = new ArrayList<>();
        this.atividadeService = new AtividadeService();

        this.usuarioService = new UsuarioService();

        this.esforcoService = new EsforcoService();
    }

    public void cadastrar() {
        try {
//            for (Usuario usuariosSelecionado : this.usuariosSelecionados) {
//                this.atividade.getPessoas().add(usuariosSelecionado.getPessoa());
//            }
            if (verificaCampos()) {
                String[] usuariosSplit = this.tagsUsuario.split(",");
                for (int i = 0; i < usuariosSplit.length; i++) {
                    String nome = usuariosSplit[i];
                    for (Usuario usuario : usuariosAux) {
                        if (nome.equalsIgnoreCase(usuario.getPessoa().getNome())) {
                            this.atividade.getPessoas().add(usuario.getPessoa());
                        }
                    }

                }

                for (Esforco esforco : esforcosAux) {
                    if (this.atividade.getEsforco().getTamanho().equalsIgnoreCase(esforco.getTamanho())) {
                        this.atividade.setEsforco(esforco);
                    }
                }

                this.atividadeService.insert(this.atividade);
                addMessage(FacesMessage.SEVERITY_INFO, "Perfeito", "Salvo com sucesso");
                limparDados();
            } else {
                addMessage(FacesMessage.SEVERITY_WARN, "Campos Nulos", "Preencha todos os campos!");
            }
        } catch (Exception ex) {
            Logger.getLogger(AtividadeBean.class.getName()).log(Level.SEVERE, null, ex);
            addMessage(FacesMessage.SEVERITY_ERROR, "Ops", "Erro ao salvar");
        }
    }

    public String editar() {
        try {
//            for (Usuario usuariosSelecionado : this.usuariosSelecionados) {
//                this.atividade.getPessoas().add(usuariosSelecionado.getPessoa());
//            }
 if (((TipoUsuario) ControleSessao.getTipoUsuario()).desc.equalsIgnoreCase("ADMINISTRADOR")) {
            String[] usuariosSplit = this.tagsUsuario.split(",");
            this.atividade.setPessoas(new ArrayList<>());
            for (int i = 0; i < usuariosSplit.length; i++) {
                String nome = usuariosSplit[i];
                for (Usuario usuario : usuariosAux) {
                    if (nome.equalsIgnoreCase(usuario.getPessoa().getNome())) {
                        this.atividade.getPessoas().add(usuario.getPessoa());
                    }
                }

            }

            for (Esforco esforco : esforcosAux) {
                if (this.atividade.getEsforco().getTamanho().equalsIgnoreCase(esforco.getTamanho())) {
                    this.atividade.setEsforco(esforco);
                }
            }
 }
            this.atividadeService.edit(this.atividade);
            addMessage(FacesMessage.SEVERITY_INFO, "Perfeito", "Salvo com sucesso");
            limparDados();
        } catch (Exception ex) {
            Logger.getLogger(AtividadeBean.class.getName()).log(Level.SEVERE, null, ex);
            addMessage(FacesMessage.SEVERITY_ERROR, "Ops", "Erro ao salvar");
        }
        return "atividade.jsf";
    }

    public String remover() {
        if (verificaSelecao() == true) {
            return "atividade.jsf";
        }

        for (Atividade atividade : atividadesSelecionados) {
            try {
                this.atividadeService.remove(atividade.getIdAtividade());
            } catch (Exception ex) {
                Logger.getLogger(AtividadeBean.class.getName()).log(Level.SEVERE, null, ex);
                addMessage(FacesMessage.SEVERITY_ERROR, "Ops", "Erro ao remover");
            }
        }
        this.atividades.removeAll(this.atividadesSelecionados);
        addMessage(FacesMessage.SEVERITY_INFO, "Perfeito", "Removido com sucesso");
        limparDados();
        return "atividade.jsf";
    }

    public String preparaEditar() {
        return verificaSelecao() ? "atividade.jsf" : "edit_atividade.jsf";
    }

    public boolean verificaSelecao() {
        if (!atividadesSelecionados.isEmpty()) {
            this.atividade = atividadesSelecionados.get(0);
            List<RecursoHumano> recursosHumanos = this.atividade.getPessoas();
            this.tagsUsuario = "";
            for (RecursoHumano recursosHumano : recursosHumanos) {
                this.tagsUsuario = this.tagsUsuario + recursosHumano.getNome() + ",";
            }

        } else {
            addMessage(FacesMessage.SEVERITY_ERROR, "Ops", "Selecione alguma atividade");
            return true;
        }
        return false;
    }

    public void selecionaUsuarios() {
        this.tagsUsuario = "";
        for (Usuario usuariosSelecionado : usuariosSelecionados) {
            this.tagsUsuario = this.tagsUsuario + usuariosSelecionado.getPessoa().getNome() + ",";
        }
        this.usuariosSelecionados = new ArrayList<>();
        fechaModal("modalUsuario");
    }

    public void limparDados() {
        this.atividade = new Atividade();
        this.tagsUsuario = "";
        this.usuariosSelecionados = new ArrayList<>();
    }

    public void addMessage(FacesMessage.Severity fc, String titulo, String descricao) {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage();
        message.setSeverity(fc);
        message.setSummary("<strong>" + titulo + "</strong>");
        message.setDetail(descricao);
        context.addMessage(null, message);
    }

    public void fechaModal(String nome) {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("$('." + nome + "').modal('hide')");
    }

    private boolean verificaCampos() {
        if (this.atividade.getDataInicio() == null) {
            return false;
        }
        if (this.atividade.getDataPrevista() == null) {
            return false;
        }
        if (this.atividade.getDescricao().equalsIgnoreCase("")) {
            return false;
        }
        if (this.atividade.getEsforco().getTamanho().equalsIgnoreCase("")) {
            return false;
        }
        if (this.atividade.getTitulo().equalsIgnoreCase("")) {
            return false;
        }
        if (this.atividade.getDataInicio() == null) {
            return false;
        }
        if (this.atividade.getStatus() == null) {
            return false;
        }
        if (this.tagsUsuario.equalsIgnoreCase("")) {
            return false;
        }

        return this.atividade.getTipo() != null;
    }

    public Atividade getAtividade() {
        return atividade;
    }

    public void setAtividade(Atividade atividade) {
        this.atividade = atividade;
    }

    public List<Atividade> getAtividades() {
        if (((TipoUsuario) ControleSessao.getTipoUsuario()).desc.equalsIgnoreCase("ADMINISTRADOR")) {
            this.atividades = this.atividadeService.listar();
        } else {
            atividades = (List<Atividade>) ControleSessao.getAtividades();
        }
        return atividades;
    }

    public void setAtividades(List<Atividade> atividades) {
        this.atividades = atividades;
    }

    public List<Atividade> getAtividadesSelecionados() {
        return atividadesSelecionados;
    }

    public void setAtividadesSelecionados(List<Atividade> atividadesSelecionados) {
        this.atividadesSelecionados = atividadesSelecionados;
    }

    public AtividadeService getAtividadeService() {
        return atividadeService;
    }

    public void setAtividadeService(AtividadeService atividadeService) {
        this.atividadeService = atividadeService;
    }

    public Situacao[] getSituacoes() {
        return Situacao.values();
    }

    public Tipo[] getTipos() {
        return Tipo.values();
    }

    public List<Usuario> getUsuariosAux() {
        this.usuariosAux = this.usuarioService.listar();
        return usuariosAux;
    }

    public void setUsuariosAux(List<Usuario> usuariosAux) {
        this.usuariosAux = usuariosAux;
    }

    public List<Usuario> getUsuariosSelecionados() {
        return usuariosSelecionados;
    }

    public void setUsuariosSelecionados(List<Usuario> usuariosSelecionados) {
        this.usuariosSelecionados = usuariosSelecionados;
    }

    public String getTagsUsuario() {
        return tagsUsuario;
    }

    public void setTagsUsuario(String tagsUsuario) {
        this.tagsUsuario = tagsUsuario;
    }

    public String[] getUsuarios() {
        this.usuariosAux = this.usuarioService.listar();
        this.usuarios = new String[this.usuariosAux.size()];
        for (int i = 0; i < usuarios.length; i++) {
            this.usuarios[i] = this.usuariosAux.get(i).getPessoa().getNome();
        }

        return usuarios;
    }

    public void setUsuarios(String[] usuarios) {
        this.usuarios = usuarios;
    }

    public String[] getEsforcos() {
        this.esforcosAux = this.esforcoService.listar();
        this.esforcos = new String[this.esforcosAux.size()];
        for (int i = 0; i < esforcos.length; i++) {
            this.esforcos[i] = this.esforcosAux.get(i).getTamanho();
        }

        return esforcos;
    }

    public void setEsforcos(String[] esforcos) {
        this.esforcos = esforcos;
    }

    public int getContAtividadeAberta() {
        contAtividadeAberta = this.atividadeService.listarIniciadas().size();
        return contAtividadeAberta;
    }

    public void setContAtividadeAberta(int contAtividadeAberta) {
        this.contAtividadeAberta = contAtividadeAberta;
    }

    public int getContAtividadeNaoIniciada() {
        contAtividadeNaoIniciada = this.atividadeService.listarNaoIniciadas().size();
        return contAtividadeNaoIniciada;
    }

    public void setContAtividadeNaoIniciada(int contAtividadeNaoIniciada) {
        this.contAtividadeNaoIniciada = contAtividadeNaoIniciada;
    }

    public int getContAtividadeConcluida() {
        contAtividadeConcluida = this.atividadeService.listarConcluidas().size();
        return contAtividadeConcluida;
    }

    public void setContAtividadeConcluida(int contAtividadeConcluida) {
        this.contAtividadeConcluida = contAtividadeConcluida;
    }

    public double getTotalHorasAtividades() {
        totalHorasAtividades = 0;
        List<Atividade> at = this.atividadeService.listar();
        for (Atividade atividade1 : at) {
            totalHorasAtividades = totalHorasAtividades + atividade1.getEsforco().getNumeroHoras();
        }
        return totalHorasAtividades;
    }

    public void setTotalHorasAtividades(double totalHorasAtividades) {
        this.totalHorasAtividades = totalHorasAtividades;
    }

    public List<Atividade> getAtividadesAtrasadas() {
        this.atividadesAtrasadas = this.atividadeService.listarAtrasadas();
        return atividadesAtrasadas;
    }

    public void setAtividadesAtrasadas(List<Atividade> atividadesAtrasadas) {
        this.atividadesAtrasadas = atividadesAtrasadas;
    }

    public List<Atividade> getAtividadesConcluidasAntesPrazo() {
       this.atividadesConcluidasAntesPrazo = this.atividadeService.listarConcluidasAntesPrazo();
        return atividadesConcluidasAntesPrazo;
    }

    public void setAtividadesConcluidasAntesPrazo(List<Atividade> atividadesConcluidasAntesPrazo) {
        this.atividadesConcluidasAntesPrazo = atividadesConcluidasAntesPrazo;
    }

}
