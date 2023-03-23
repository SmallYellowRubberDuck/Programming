package DAO;

import java.util.Set;
import java.util.Optional;

/**
 * Интерфейс DAO
 * @param <T>
 */
public interface Dao<T> {

    T get(int id);
    void create(T t);
    Set<T> getAll();

    void save();

    void update(T t, int id);

    void delete(T t);
}