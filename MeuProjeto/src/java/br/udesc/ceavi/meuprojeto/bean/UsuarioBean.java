/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.ceavi.meuprojeto.bean;

import br.udesc.ceavi.meuprojeto.model.TipoUsuario;
import br.udesc.ceavi.meuprojeto.model.Usuario;
import br.udesc.ceavi.meuprojeto.service.UsuarioService;
import br.udesc.ceavi.meuprojeto.util.GerarMD5;
import br.udesc.ceavi.meuprojeto.util.sessao.ControleSessao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author marcoscamargo
 */
@SessionScoped
@ManagedBean
public class UsuarioBean implements Serializable {

    private Usuario usuario;
    private List<Usuario> usuarios;
    private List<Usuario> usuariosSelecionados;
    private String email;
    private String senha;
    private String senhaAnterior;
    private UsuarioService usuarioService;
    private boolean mostraCampo = true;

    @PostConstruct
    public void init() {
        this.email = "";
        this.senha = "";
        this.usuario = new Usuario();
        this.usuarios = new ArrayList<>();
        this.usuariosSelecionados = new ArrayList<>();
        this.usuarioService = new UsuarioService();
    }

    public String signIn() {
        this.usuario = this.usuarioService.login(this.email, GerarMD5.criptografar(this.senha));
        if (usuario != null) {
            ControleSessao.setUserId(this.usuario.getIdUsuario());
            ControleSessao.setNomeUsuarioLogado(this.usuario.getPessoa().getNome());
            ControleSessao.setTipoUsuario(this.usuario.getTipoUsuario());
            ControleSessao.setAtividades(this.usuario.getPessoa().getAtividades());
            if (this.usuario.getTipoUsuario().equals(TipoUsuario.VISUALIZAR)) {
                this.mostraCampo = false;
            }

            addMessage(FacesMessage.SEVERITY_INFO, "Bem Vindo!", this.usuario.getPessoa().getNome() + ", Gerencie o seu projeto!");
            limparDados();
            return "dashboard.jsf";
        }
        addMessage(FacesMessage.SEVERITY_ERROR, "Ops!", " Credenciais Incorretass!");
        return "index.jsf";
    }

    public String signOut() {
        HttpSession session = ControleSessao.getSession();
        session.invalidate();
        addMessage(FacesMessage.SEVERITY_INFO, "Até mais!", " Volte logo :)");
        return "index.jsf";
    }

    public void cadastrar() {
        try {
            if (verificaCampos()) {
                String SenhaCriptografada = this.usuario.getSenha();
                this.usuario.setSenha(GerarMD5.criptografar(SenhaCriptografada));
                this.usuarioService.insert(this.usuario);
                addMessage(FacesMessage.SEVERITY_INFO, "Perfeito", "Salvo com sucesso");

                limparDados();
            } else {
                  addMessage(FacesMessage.SEVERITY_WARN, "Campos Nulos", "Preencha todos os campos!");
            }
        } catch (Exception ex) {
            Logger.getLogger(UsuarioBean.class.getName()).log(Level.SEVERE, null, ex);
            addMessage(FacesMessage.SEVERITY_ERROR, "Ops", "Erro ao salvar");
        }
    }

    public String preparaEditar() {
        return verificaSelecao() ? "usuario.jsf" : "edit_usuario.jsf";
    }

    public boolean verificaSelecao() {
        if (!usuariosSelecionados.isEmpty()) {
            this.usuario = usuariosSelecionados.get(0);
        } else {
            addMessage(FacesMessage.SEVERITY_ERROR, "Ops", "Selecione algum usuário");
            return true;
        }
        return false;
    }

    public String editar() {
        try {
            if (this.usuario.getSenha().equals(GerarMD5.criptografar(this.senhaAnterior))) {
                this.usuario.setSenha(GerarMD5.criptografar(this.senha));
                this.usuarioService.edit(this.usuario);
                addMessage(FacesMessage.SEVERITY_INFO, "Perfeito", "Editado com sucesso");
                return "usuario.jsf";
            } else {
                addMessage(FacesMessage.SEVERITY_ERROR, "Ops", "Senha anterior incorreta");
                this.senhaAnterior = "";
                this.senha = "";
            }
        } catch (Exception ex) {
            Logger.getLogger(UsuarioBean.class.getName()).log(Level.SEVERE, null, ex);
            addMessage(FacesMessage.SEVERITY_ERROR, "Ops", "Erro ao editar");
        }
        return "edit_usuario.jsf";
    }

    public String remover() {
        if (verificaSelecao() == true) {
            return "usuario.jsf";
        }
        for (Usuario usuariosSelecionado : usuariosSelecionados) {
            try {
                this.usuarioService.remove(usuariosSelecionado.getIdUsuario());
            } catch (Exception ex) {
                Logger.getLogger(UsuarioBean.class.getName()).log(Level.SEVERE, null, ex);
                addMessage(FacesMessage.SEVERITY_ERROR, "Ops", "Erro ao remover");
            }
        }
        this.usuarios.removeAll(this.usuariosSelecionados);
        addMessage(FacesMessage.SEVERITY_INFO, "Perfeito", "Removido com sucesso");
        limparDados();
        return "usuario.jsf";
    }

    public void limparDados() {
        this.usuario = new Usuario();
        this.senha = "";
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Usuario> getUsuarios() {
        this.usuarios = this.usuarioService.listar();
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public List<Usuario> getUsuariosSelecionados() {
        return usuariosSelecionados;
    }

    public String getSenhaAnterior() {
        return senhaAnterior;
    }

    public void setSenhaAnterior(String senhaAnterior) {
        this.senhaAnterior = senhaAnterior;
    }

    public void setUsuariosSelecionados(List<Usuario> usuariosSelecionados) {
        this.usuariosSelecionados = usuariosSelecionados;
    }

    public TipoUsuario[] getTipoUsuarios() {
        return TipoUsuario.values();
    }

    public boolean isMostraCampo() {
        return mostraCampo;
    }

    public void setMostraCampo(boolean mostraCampo) {
        this.mostraCampo = mostraCampo;
    }

    public void addMessage(Severity fc, String titulo, String descricao) {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage();
        message.setSeverity(fc);
        message.setSummary("<strong>" + titulo + "</strong>");
        message.setDetail(descricao);
        context.addMessage(null, message);
    }

    private boolean verificaCampos() {
        if (this.usuario.getTipoUsuario() == null) {
            return false;
        }
        if (this.usuario.getSenha().equalsIgnoreCase("")) {
            return false;
        }
        if (this.usuario.getPessoa().getNome().equalsIgnoreCase("")) {
            return false;
        }
        if (this.usuario.getPessoa().getEmail().equalsIgnoreCase("")) {
            return false;
        }
        return !this.usuario.getPessoa().getTelefone().equalsIgnoreCase("");
    }

}
