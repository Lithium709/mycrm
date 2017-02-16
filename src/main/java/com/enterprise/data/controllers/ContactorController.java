package com.enterprise.data.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.enterprise.data.repositories.ContactorRepository;

@Controller
public class ContactorController {

	@Autowired
	private ContactorRepository contractorRepository;
	
	@RequestMapping(value = "/contractors")
	public String orders(Model model) {

		model.addAttribute("contractors", contractorRepository.findAll());
		return "contractors";
	}
}
