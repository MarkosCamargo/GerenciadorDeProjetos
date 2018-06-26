package br.udesc.ceavi.meuprojeto.util.sessao;

import br.udesc.ceavi.meuprojeto.model.Atividade;
import br.udesc.ceavi.meuprojeto.model.TipoUsuario;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@ManagedBean
@SessionScoped
public class ControleSessao implements Serializable {
      private String nomeUsuario;

    public static HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    }

    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }

    public static void invalidate() {
        getSession().invalidate();
    }

    public static Object getUserId() {
        return getSession().getAttribute("userid");
    }

    public static void setUserId(int id) {
        getSession().setAttribute("userid", id);
    }

    public static Object getNomeUsuarioLogado() {
        return getSession().getAttribute("username");
    }

    public static void setNomeUsuarioLogado(String nomeUsuarioLogado) {
        getSession().setAttribute("username", nomeUsuarioLogado);
    }

    public static Object getTipoUsuario() {
        return getSession().getAttribute("usertype");
    }

    public static void setTipoUsuario(TipoUsuario tipoUsuario) {
        getSession().setAttribute("usertype", tipoUsuario);
    }
    
    public static Object getAtividades() {
        return getSession().getAttribute("useracti");
    }

    public static void setAtividades(List<Atividade> tipoUsuario) {
        getSession().setAttribute("useracti", tipoUsuario);
    }


    public String getNomeUsuario() {
        return (String) getNomeUsuarioLogado();
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }
    
    
}
