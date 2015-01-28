package com.finvoices.serviceImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finvoices.dao.BuyerPartyDetailsDAO;
import com.finvoices.exception.BuyerNotFound;
import com.finvoices.model.BuyerPartyDetails;



@Service
public class BuyerPartyDetailsServiceImpl implements BuyerPartyDetailsDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public BuyerPartyDetailsServiceImpl() {

	}

	public BuyerPartyDetailsServiceImpl(SessionFactory sessionFactory) {
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

	@Transactional(rollbackFor=BuyerNotFound.class)
	public BuyerPartyDetails delete(int id) {
		BuyerPartyDetails buyerToDelete = this.get(id);
		sessionFactory.getCurrentSession().delete(buyerToDelete);
		return buyerToDelete;
	}

	@Transactional(rollbackFor=BuyerNotFound.class)
	public BuyerPartyDetails get(int id) {
		String hql = "from BuyerPartyDetails where BuyerPartyDetails_id=" + id;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);


		@SuppressWarnings("unchecked")
		List<BuyerPartyDetails> listUser = (List<BuyerPartyDetails>) query.list();

		if (listUser != null && !listUser.isEmpty()) {
			return listUser.get(0);
		}
		return null;
	}

	@Transactional(rollbackFor=BuyerNotFound.class)
	public BuyerPartyDetails getByBuyerPartyIdentifier(String buyer_id)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("from BuyerPartyDetails as buyer where buyer.buyid =?"); 
		query.setString(0,buyer_id); 

		@SuppressWarnings("unchecked")
		List<BuyerPartyDetails> listUser = (List<BuyerPartyDetails>) query.list();

		if (listUser != null && !listUser.isEmpty()) {
			return listUser.get(0);
		}
		return null;	
	}

	@Transactional(rollbackFor=BuyerNotFound.class)
	public List<BuyerPartyDetails> listByfileName(String fileName){
		Query query = sessionFactory.getCurrentSession().createQuery("from BuyerPartyDetails as buyer where buyer.xmlFileName =?"); 
		query.setString(0,fileName); 

		@SuppressWarnings("unchecked")
		List<BuyerPartyDetails> buyerList = (List<BuyerPartyDetails>) query.list();

		if (buyerList != null && !buyerList.isEmpty()) {
			return buyerList;
		}
		return null;	

	}

	@Transactional(rollbackFor=BuyerNotFound.class)
	public BuyerPartyDetails getByBuyerPartyByName(String name){
		
		Query query = sessionFactory.getCurrentSession().createQuery("from BuyerPartyDetails as buyer where buyer.buyerOrganisationName =?"); 
		query.setString(0,name); 		
		@SuppressWarnings("unchecked")
		List<BuyerPartyDetails> buyerList = (List<BuyerPartyDetails>) query.list();

		if (buyerList != null && !buyerList.isEmpty()) {
			return buyerList.get(0);
		}
		return null;	
	}


}