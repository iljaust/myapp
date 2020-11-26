package com.iljaust.app.repository;

import java.sql.SQLException;
import java.util.List;

public interface GenericRepository <T,ID>{
    T getById(ID id);
    T save(T t);
    T update(T t);
    List<T> getAll() throws SQLException;
    void deleteById(ID id);
}
