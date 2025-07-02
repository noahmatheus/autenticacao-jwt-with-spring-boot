package com.startjwtproject.repository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

public class GenericRepository<T extends Serializable, ID> {

	@PersistenceContext
	private EntityManager em;

	private final Class<T> persistentClass;

	@SuppressWarnings("unchecked")
	public GenericRepository() {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}

	// BuscaPorIdUsuario
	@SuppressWarnings("unchecked")
	public T findById(String pk, UUID id) {
		String condicao = " WHERE 1=1 and  " + pk + " = '" + id + "' ";
		T t = (T) em.createQuery("select t from " + persistentClass.getName() + " as t " + condicao).getSingleResult();

		return t;
	}

	@SuppressWarnings("unchecked")
	public List<T> listarTodos(Integer page, Integer size, String order) {

		String condicao = " WHERE 1=1 " + order;
		List<T> t = em.createQuery("select t from " + persistentClass.getName() + " as t " + condicao)
				.setFirstResult(page * size).setMaxResults(size).getResultList();

		return t;
	}

	// loadUserByUsername &SpringSecurity
	@SuppressWarnings("unchecked")
	public T findByField(String field, String value) {
		String condicao = " WHERE 1=1 and  " + field + " = '" + value + "' ";
		T t = (T) em.createQuery("select t from " + persistentClass.getName() + " as t " + condicao).getSingleResult();

		return t;
	}

	@Transactional
	public T persist(T entity) {
		T resultado;

		try {

			resultado = em.merge(entity);

		} catch (Exception e) {

			resultado = null;
			throw new java.lang.RuntimeException(e.getMessage());
		} finally {
			em.close();
		}

		return resultado;
	}

	@Transactional
	public Boolean deletar(T entity) {
		Boolean resultado = false;

		try {

			em.remove(entity);

			resultado = true;
		} catch (Throwable t) {
			resultado = false;
			t.printStackTrace();

		} finally {
			em.close();
		}

		return resultado;
	}

}
