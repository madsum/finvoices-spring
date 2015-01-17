package com.finvoices.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finvoices.dao.DefinitionDetailsDAO;
import com.finvoices.model.DefinitionDetails;

@Service
public class DefinitionDetailsService implements DefinitionDetailsDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public DefinitionDetailsService() {
		
	}
	
	public DefinitionDetailsService(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public List<DefinitionDetails> list() {
		@SuppressWarnings("unchecked")
		List<DefinitionDetails> listUser = (List<DefinitionDetails>) sessionFactory.getCurrentSession()
				.createCriteria(DefinitionDetails.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

		return listUser;
	}


	@Transactional
	public void saveOrUpdate(DefinitionDetails user) {
		sessionFactory.getCurrentSession().saveOrUpdate(user);
	}


	@Transactional
	public void delete(int id) {
		DefinitionDetails userToDelete = new DefinitionDetails();
		userToDelete.setDefinitionDetails_id(id);
		sessionFactory.getCurrentSession().delete(userToDelete);
	}

	@Transactional
	public DefinitionDetails get(int id) {
		String hql = "from definitionDetails where id=" + id;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<DefinitionDetails> listUser = (List<DefinitionDetails>) query.list();
		
		if (listUser != null && !listUser.isEmpty()) {
			return listUser.get(0);
		}
		
		return null;
	}
}