/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.ceavi.meuprojeto.util.filtro;

import br.udesc.ceavi.meuprojeto.model.TipoUsuario;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author marcoscamargo
 */
@WebFilter(servletNames = {"Faces Servlet"})
public class FiltroAcessoSistema implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest requisicao = (HttpServletRequest) request;
        HttpSession session = requisicao.getSession();
        if ((session.getAttribute("userid") != null) || (requisicao.getRequestURI().endsWith("index.jsf")) || (requisicao.getRequestURI().contains("javax.faces.resource/"))) {
            TipoUsuario t = (TipoUsuario) session.getAttribute("usertype");
            if (t != null) {
                if (t.equals(TipoUsuario.VISUALIZAR) && (requisicao.getRequestURI().endsWith("cad_usuario.jsf")
                        | requisicao.getRequestURI().endsWith("edit_usuario.jsf") | requisicao.getRequestURI().endsWith("cad_atividade.jsf")
                        | requisicao.getRequestURI().endsWith("cad_esforco.jsf")  | requisicao.getRequestURI().endsWith("usuario.jsf")
                        | requisicao.getRequestURI().endsWith("edit_esforco.jsf") | requisicao.getRequestURI().endsWith("atividade_atrasada.jsf") 
                        | requisicao.getRequestURI().endsWith("atividade_concluida_antes.jsf"))) {//paginas que o usuario não pode acessar
                    redireciona("dashboard.jsf", response);
                } else
                    chain.doFilter(request, response);
            } else {
                chain.doFilter(request, response);
            }
        } else {
            redireciona("index.jsf", response);
        }
    }

    private void redireciona(String url, ServletResponse response) throws IOException {
        HttpServletResponse res = (HttpServletResponse) response;
        res.sendRedirect(url);
    }

    @Override
    public void destroy() {
    }

}
