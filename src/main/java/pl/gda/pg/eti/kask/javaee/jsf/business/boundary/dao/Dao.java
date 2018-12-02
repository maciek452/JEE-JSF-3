package pl.gda.pg.eti.kask.javaee.jsf.business.boundary.dao;

import java.io.Serializable;
import java.util.List;


public interface Dao<T extends Serializable> {
  T add(final T entity);

  T update(final T entity);

  List<T> findAll();

  void remove(final T entity);

  T find(final int id);
}
