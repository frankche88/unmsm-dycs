package unmsm.dycs.commons.infrastructure.persistence.hibernate.repository;

import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import unmsm.dycs.commons.domain.repository.BaseRepository;

@Named
public class BaseHibernateRepository<T> implements BaseRepository<T> {

	protected SessionFactory sessionFactory;

	@Inject
	public void setSessionFactory(final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public void persist(T entity) {
		getSession().persist(entity);
	}
	
	public void save(T entity) {
		getSession().save(entity);
	}
	
	public void update(T entity) {
		getSession().update(entity);
	}

	public void merge(T entity) {
		getSession().merge(entity);
	}
	
	public void saveOrUpdate(T entity) {
		getSession().saveOrUpdate(entity);
	}
}
