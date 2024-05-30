package main.dao;

import java.util.List;

public interface GenericDAO<T> {
    void add(T t);
    T read(int id);
    void update(T t);
    void delete(int id);
    List<T> getAll();
}
