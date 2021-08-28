package ir.sharif.math.ap99_2.tinsta_server.controller.interfaces;

import java.util.LinkedList;

public interface DBSet<T> {
    T get (int id);
    void add(T t);
    void remove(T t);
    LinkedList<T> all();
    void update(T t);

}
