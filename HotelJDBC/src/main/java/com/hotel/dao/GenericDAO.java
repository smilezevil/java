package com.hotel.dao;

import java.util.List;

public interface GenericDAO<T> {
    void create(T t) throws Exception;
    T read(int id) throws Exception;
    List<T> readAll() throws Exception;
    void update(T t) throws Exception;
    void delete(int id) throws Exception;
    List<T> search(String keyword) throws Exception;
}
