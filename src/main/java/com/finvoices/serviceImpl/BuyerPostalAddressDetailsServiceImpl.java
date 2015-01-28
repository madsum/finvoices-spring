package com.finvoices.serviceImpl;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finvoices.dao.BuyerPostalAddressDetailsDAO;
import com.finvoices.model.BuyerPostalAddressDetails;

@Service
public class BuyerPostalAddressDetailsServiceImpl implements BuyerPostalAddressDetailsDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	public BuyerPostalAddressDetailsServiceImpl() {
		
	}
	
	public BuyerPostalAddressDetailsServiceImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public List<BuyerPostalAddressDetails> list() {
		@SuppressWarnings("unchecked")
		List<BuyerPostalAddressDetails> listUser = (List<BuyerPostalAddressDetails>) sessionFactory.getCurrentSession()
				.createCriteria(BuyerPostalAddressDetails.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

		return listUser;
	}


	@Transactional
	public void saveOrUpdate(BuyerPostalAddressDetails buyerPostalAddressDetails) {
		sessionFactory.getCurrentSession().saveOrUpdate(buyerPostalAddressDetails);
	}


	@Transactional
	public void delete(int id) {
		BuyerPostalAddressDetails addressToDelete = new BuyerPostalAddressDetails();
		addressToDelete.setBuyerPartyDetails_id(id);;
		sessionFactory.getCurrentSession().delete(addressToDelete);
	}

	@Transactional
	public BuyerPostalAddressDetails get(int id) {
		String hql = "from BuyerPostalAddressDetails where id=" + id;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<BuyerPostalAddressDetails> listUser = (List<BuyerPostalAddressDetails>) query.list();
		
		if (listUser != null && !listUser.isEmpty()) {
			return listUser.get(0);
		}
		
		return null;
	}
	
	@Transactional
	public BuyerPostalAddressDetails getByBuyerId(int id){
		
		String hql = "from BuyerPostalAddressDetails where BuyerPartyDetails_id=" + id;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<BuyerPostalAddressDetails> listUser = (List<BuyerPostalAddressDetails>) query.list();
		
		if (listUser != null && !listUser.isEmpty()) {
			return listUser.get(0);
		}
		
		return null;	
	}
}