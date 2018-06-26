package br.udesc.ceavi.meuprojeto.dao;

import java.util.List;
import java.util.Map;


public interface DefaultDAO<T> {

    public void incluir(T t) throws Exception;

    public void alterar(T t) throws Exception;

    public void excluir(Integer id) throws Exception;

    public List<T> listarTodos();

    public T buscarPorId(int id);

    public List listarConsultaNomeada(String nome, Map<String, Object> parametros);
    
    public Object consultaNomeada(String nome, Map<String, Object> parametros);
}
