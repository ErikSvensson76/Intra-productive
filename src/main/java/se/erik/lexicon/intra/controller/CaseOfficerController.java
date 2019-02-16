package se.erik.lexicon.intra.controller;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import se.erik.lexicon.intra.entity.CaseOfficer;
import se.erik.lexicon.intra.forms_and_views.CaseOfficerForm;
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
		ModelAndView model = new ModelAndView("redirect:/index/caseofficer/" + newOfficer.getOfficerId() + "/show");

		return model;			
	}
	
	@GetMapping("caseofficer/{caseOfficerId}/show")
	public String showCaseOfficer(@PathVariable("caseOfficerId")String id, Model model) {
		
		CompletableFuture<Optional<CaseOfficer>> future = service.findById(id);
		Optional<CaseOfficer> content = Optional.empty();
		try {
			content = future.get(1000, TimeUnit.MILLISECONDS);
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			System.err.println(e.getMessage());
			model.addAttribute("exception", e);
			return "error";
		}
		
		if(content.isPresent()) {
			model.addAttribute("caseOfficer", service.convertToView(content.get()));
			return "view-caseofficer";
		}else {
			return "error";
		}		
	}
		
}
