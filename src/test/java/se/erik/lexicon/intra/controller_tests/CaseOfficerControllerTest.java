package se.erik.lexicon.intra.controller_tests;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import se.erik.lexicon.intra.controller.CaseOfficerController;
import se.erik.lexicon.intra.entity.case_officer.CaseOfficer;
import se.erik.lexicon.intra.entity.case_officer.CaseOfficerForm;
import se.erik.lexicon.intra.service.CaseOfficerService;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static org.mockito.Mockito.*;
import org.mockito.internal.util.reflection.FieldSetter;


public class CaseOfficerControllerTest {
	
	@Mock
	private CaseOfficerService service;
	
	@Mock
	private Model model;
	
	private CaseOfficerController controller;

	private MockMvc mockMvc;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		controller = new CaseOfficerController();
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	@Test
	public void test_showForm() throws Exception{
		
		
		mockMvc.perform(get("/index/caseofficer/register/"))
			.andExpect(status().isOk())
			.andExpect(view().name("register"))
			.andExpect(model().attributeExists("form"));
	}
	
	
	
//	@Test
//	public void test_Post_new_CaseOfficer_form() throws Exception {
//		CaseOfficer test = new CaseOfficer("test.testsson@gmail.com", "Växjö", "0701234567", "Test", "Testsson");
//		FieldSetter.setField(test, test.getClass().getDeclaredField("officerId"), "test");
//		CaseOfficerForm form = new CaseOfficerForm();
//
//		
//		
//		when(service.findByEmail(any())).thenReturn(CompletableFuture.completedFuture(Optional.of(test)));		
//		when(service.save(test)).thenReturn(test);
//		
//		mockMvc.perform(post("/index/caseofficer/register/")
//				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
//				.param("id","")
//				.param("email", "test.testsson@gmail.com")
//				.param("city", "Växjö")
//				.param("phone", "0701234567")
//				.param("firstName", "Test")
//				.param("lastName", "Testsson"))
//				.andExpect(status().is3xxRedirection())
//				.andExpect(view().name("register"));				
//	}

}
