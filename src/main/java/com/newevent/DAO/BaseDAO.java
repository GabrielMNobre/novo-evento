package com.newevent.DAO;


/**
 *
 * @author gabriel
 * @param <T>
 */
public interface BaseDAO<T> {
    
    public abstract boolean Cadastrar(T t);
    public abstract T ListarPorId(int id);
    public abstract boolean Deletar(int id);
    public abstract boolean Atualizar(T t);
    
}
