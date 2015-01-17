package com.finvoices.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finvoices.dao.BuyerPartyDetailsDAO;
import com.finvoices.model.BuyerPartyDetails;


@Service
public class BuyerPartyDetailsService implements BuyerPartyDetailsDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	public BuyerPartyDetailsService() {
		
	}
	
	public BuyerPartyDetailsService(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public List<BuyerPartyDetails> list() {
		@SuppressWarnings("unchecked")
		List<BuyerPartyDetails> listUser = (List<BuyerPartyDetails>) sessionFactory.getCurrentSession()
				.createCriteria(BuyerPartyDetails.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

		return listUser;
	}


	@Transactional
	public void saveOrUpdate(BuyerPartyDetails buyerPartyDetails) {
		sessionFactory.getCurrentSession().saveOrUpdate(buyerPartyDetails);
	}


	@Transactional
	public void delete(int id) {
		BuyerPartyDetails buyerToDelete = new BuyerPartyDetails();
		buyerToDelete.setBuyerPartyDetails_id(id);
		sessionFactory.getCurrentSession().delete(buyerToDelete);
	}

	@Transactional
	public BuyerPartyDetails get(int id) {
		String hql = "from BuyerPartyDetails where id=" + id;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<BuyerPartyDetails> listUser = (List<BuyerPartyDetails>) query.list();
		
		if (listUser != null && !listUser.isEmpty()) {
			return listUser.get(0);
		}
		
		return null;
	}
}