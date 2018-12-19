package se.erik.lexicon.intra.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import se.erik.lexicon.intra.entity.case_officer.CaseOfficer;
import se.erik.lexicon.intra.entity.case_officer.CaseOfficerForm;
import se.erik.lexicon.intra.service.CaseOfficerService;

@Controller
@RequestMapping("index/")
public class CaseOfficerController {
	
	@Autowired
	private CaseOfficerService service;
	
	@GetMapping("caseofficer/register")
	public String showForm(CaseOfficerForm form, Model model) {
		model.addAttribute("form", form);
		
		return "register";
	}
	
	@PostMapping("caseofficer/register")
	public String register(@Valid @ModelAttribute("form") CaseOfficerForm form, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return "register";
		}
				
		CaseOfficer newOfficer = new CaseOfficer(form.getEmail(), form.getCity(), form.getPhone(), form.getFirstName(), form.getLastName());
		service.save(newOfficer);
		
		return "register";				
		
	}
	
}