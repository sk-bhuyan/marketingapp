package com.marketingapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.marketingapp.entities.Lead;
import com.marketingapp.services.LeadService;
import com.marketingapp.util.EmailServiceimpl;

@Controller
public class LeadController {
	
	@Autowired
	private EmailServiceimpl emailservice;
	
	@Autowired
	private LeadService leadservice;
	
	//Handler Methods
	@RequestMapping("/viewLead")
	public String viewCreateLeadPage() {
		return "create_lead";
	}
	
	@RequestMapping(value="/saveLead", method = RequestMethod.POST)		//"name" attribute value of create_lead.jsp file should match with Entity class variables.
	public String saveLead(@ModelAttribute("leads") Lead l, ModelMap model) {
		leadservice.saveOneLead(l);
		emailservice.sendSimpleMail(l.getEmail(), "Important Message", "Hello sir, This is Sanjib Bhuyan");
		model.addAttribute("msg", "Lead added successfully!!!");
		return "create_lead";
	}
	
	@RequestMapping("/listAll")
	public String listAll(ModelMap model) {
		List<Lead> leads = leadservice.listAllLeads();
		model.addAttribute("leads", leads);
		return "list_leads";
	}
	
	@RequestMapping("/deleteLead")
	public String deleteLeads(@RequestParam("id")long id, ModelMap model) {
		leadservice.deleteOneLead(id);
		List<Lead> leads = leadservice.listAllLeads();
		model.addAttribute("leads", leads);
		return "list_leads";
	}
	
	@RequestMapping("/updateLead")
	public String updateLead(@RequestParam("id")long id, ModelMap model) {
		Lead leads = leadservice.getById(id);
		model.addAttribute("lead", leads);
		return "update_lead";
	}
	
	@RequestMapping("/updateOneLead")
	public String updateOneLead(@ModelAttribute("leads") Lead l, ModelMap model) {
		leadservice.saveOneLead(l);
		List<Lead> leads = leadservice.listAllLeads();
		model.addAttribute("leads", leads);
		return "list_leads";
	}
	
//	@RequestMapping("/saveLead")
//	public String saveLead(@RequestParam("fname")String fname, @RequestParam("lname")String lname, @RequestParam("email")String email, @RequestParam("mobile")String mobile) {
//		Lead lead=new Lead();
//		lead.setFirstName(fname);
//		lead.setLastName(lname);
//		lead.setEmail(email);
//		lead.setMobile(mobile);
//		
//		leadservice.saveOneLead(lead);
//		return "create_lead";
//	}
	
//	@RequestMapping(value="/saveLead", method = RequestMethod.POST)
//	public String saveLead(LeadData data) {
//		Lead l=new Lead();
//		l.setFirstName(data.getFname());
//		l.setLastName(data.getLname());
//		l.setEmail(data.getEmail());
//		l.setMobile(data.getMobile());
//		leadservice.saveOneLead(l);
//		return "create_lead";
//	}
}