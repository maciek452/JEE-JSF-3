package pl.gda.pg.eti.kask.javaee.jsf.business.boundary.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public abstract class JpaDao<T extends Serializable> implements Dao<T> {

  @PersistenceContext
  protected EntityManager entityManager;

  private final Class<T> persistentClass;

  public JpaDao(final Class<T> persistentClass) {
    this.persistentClass = persistentClass;
  }

  public T add(final T entity) {
    entityManager.persist(entity);
    return entity;
  }

  public void addAll(final List<T> entities){
    entities.stream().filter(Objects::nonNull).forEach(e -> entityManager.persist(e));
  }

  public T update(final T entity) {
    if (isNull(entity)) {
      return null;
    }
    return entityManager.merge(entity);
  }

  public List<T> findAll() {
    final String queryString = "SELECT t FROM " + persistentClass.getSimpleName() + " t";
    return entityManager.createQuery(queryString).getResultList();
  }

  public void remove(T entity) {
    if (nonNull(entity)) {
      entity = entityManager.merge(entity);
      entityManager.remove(entity);
    }
  }

  public T find(final int id) {
    return entityManager.find(persistentClass, id);
  }

}