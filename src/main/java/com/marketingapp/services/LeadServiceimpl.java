package com.marketingapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marketingapp.entities.Lead;
import com.marketingapp.repositories.LeadRepository;
@Service
public class LeadServiceimpl implements LeadService {

	@Autowired
	private LeadRepository leadrepo;
	
	@Override
	public void saveOneLead(Lead l) {
		leadrepo.save(l);
	}

	@Override
	public List<Lead> listAllLeads() {
		List<Lead> leads = leadrepo.findAll();
		return leads;
	}

	@Override
	public void deleteOneLead(long id) {
		leadrepo.deleteById(id);
	}

	@Override
	public Lead getById(long id) {
		Optional<Lead> findById = leadrepo.findById(id);
		Lead lead = findById.get();
		return lead;
	}
}