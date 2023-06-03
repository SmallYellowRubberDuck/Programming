package DAO;
import java.util.Set;

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