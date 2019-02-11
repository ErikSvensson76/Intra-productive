package se.erik.lexicon.intra.controller;

import java.util.concurrent.ExecutionException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
	public ModelAndView register(@Valid @ModelAttribute("form") CaseOfficerForm form, BindingResult bindingResult) throws InterruptedException, ExecutionException {
		
		if(service.findByEmail(form.getEmail()).get().isPresent()) {
			FieldError err = new FieldError("form", "email", "Använd en annan email adress. " + form.getEmail() + " är redan registrerad.");
			bindingResult.addError(err);
		}
		
		if(bindingResult.hasErrors()) {
			ModelAndView model = new ModelAndView();
			model.setViewName("register");
			return model;
		}
				
		CaseOfficer newOfficer = new CaseOfficer(form.getEmail(), form.getCity(), form.getPhone(), form.getFirstName(), form.getLastName());
		newOfficer = service.save(newOfficer);
		ModelAndView model = new ModelAndView();
		model.addObject("caseOfficer", service.convertToView(newOfficer));
		model.setViewName("view-caseofficer");
		
		return model;			
	}
		
}
