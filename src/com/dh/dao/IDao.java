package com.dh.dao;

import java.sql.SQLException;
import java.util.List;

public interface IDao<T> {
    public T guardar(T t) throws Exception;
    public List<T> buscarTodos() throws  SQLException;
    public T buscarId(Integer id) throws Exception;
}
