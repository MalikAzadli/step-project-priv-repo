package dao;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {
    List<T> findAll();
    Optional<T> findById(int id);
    boolean create(T t);
    boolean remove(int id);
    void load();
    void save();
}
