package com.finvoices.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finvoices.dao.InvoiceDetailsDAO;
import com.finvoices.model.InvoiceDetails;

@Service
public class InvoiceDetailsService implements InvoiceDetailsDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	public InvoiceDetailsService() {
		
	}
	
	public InvoiceDetailsService(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public List<InvoiceDetails> list() {
		@SuppressWarnings("unchecked")
		List<InvoiceDetails> listUser = (List<InvoiceDetails>) sessionFactory.getCurrentSession()
				.createCriteria(InvoiceDetails.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

		return listUser;
	}


	@Transactional
	public void saveOrUpdate(InvoiceDetails invoiceDetails) {
		sessionFactory.getCurrentSession().saveOrUpdate(invoiceDetails);
	}


	@Transactional
	public void delete(int id) {
		InvoiceDetails invoiceToDelete = new InvoiceDetails();
		invoiceToDelete.setInvoiceDetails_id(id);
		sessionFactory.getCurrentSession().delete(invoiceToDelete);
	}

	@Transactional
	public InvoiceDetails get(int id) {
		String hql = "from InvoiceDetails where id=" + id;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<InvoiceDetails> listUser = (List<InvoiceDetails>) query.list();
		
		if (listUser != null && !listUser.isEmpty()) {
			return listUser.get(0);
		}
		
		return null;
	}
}