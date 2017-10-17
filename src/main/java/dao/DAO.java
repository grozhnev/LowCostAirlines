package dao;

import java.sql.SQLException;
import java.util.Set;

public interface DAO<T>{
    Set<T> getAll() throws SQLException;
    T getById(Long id) throws SQLException;
    boolean insert(T t) throws SQLException;
    boolean update(T t) throws SQLException;
    boolean delete(T t) throws SQLException;
}
