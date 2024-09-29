package org.example.DAO;

import java.util.List;
import java.util.function.Function;

public interface AbstractDAO<K extends Number, T> {
    List<T> findAll();

    T findEntityById(K id);

    boolean create(T entity);

    T update(T entity);

    boolean delete(K id);

    boolean delete(T entity);
}

