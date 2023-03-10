package dao;

import java.util.List;
import java.util.Optional;

public interface Dao <E, V>{
    void insert(E e);
    Optional<E> findByEmailAndPassword(String email, String Password);
    void deleteById(V id);
    List<E> findAll();
    void update(E e);
}
