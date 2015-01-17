package com.finvoices.dao;

import java.util.List;

import com.finvoices.model.DefinitionDetails;

public interface DefinitionDetailsDAO {
	public List<DefinitionDetails> list();
	
	public DefinitionDetails get(int id);
	
	public void saveOrUpdate(DefinitionDetails user);
	
	public void delete(int id);
}