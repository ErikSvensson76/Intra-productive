package se.erik.lexicon.intra.service_tests;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import se.erik.lexicon.intra.data_access.CaseOfficerRepository;
import se.erik.lexicon.intra.data_access.DecisionRepository;
import se.erik.lexicon.intra.entity.case_officer.CaseOfficer;
import se.erik.lexicon.intra.service.CaseOfficerService;
import se.erik.lexicon.intra.service.CaseOfficerServiceImpl;
import se.erik.lexicon.intra.utils.StringUtil;

import org.mockito.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.junit.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class CaseOfficerServiceTest {
	
	@TestConfiguration
	static class CaseOfficerServiceTestConfig{
		@Bean
		public CaseOfficerService caseOfficerService(CaseOfficerRepository officerRepo, DecisionRepository decisionRepo,
				StringUtil stringUtil) {
			return new CaseOfficerServiceImpl(officerRepo, decisionRepo, stringUtil);
		}
	}
	@MockBean
	private StringUtil stringUtil;
	@MockBean
	private CaseOfficerRepository officerRepo;
	@MockBean
	private DecisionRepository decisionRepo;
	@Autowired
	private CaseOfficerService officerService;
	
	private CaseOfficer testOfficer;
	private CaseOfficer testOfficer2;
		
	@Before
	public void init() {
		testOfficer = new CaseOfficer("test.af@test.com", "Växjö", "0470-1111111", "Test", "Testsson");
		testOfficer2 = new CaseOfficer("test2.af@test.com", "Ljungby", "070-1123111", "Test2", "Testsson2");
	}
	
	@Test
	public void test_findByEmail_with_valid_email_CompletableFuture_contains_Optional_with_testOfficer() throws InterruptedException, ExecutionException {
		String param = "Test.af@test.com";
		String toLowerCaseParam = "test.af@test.com";
		Optional<CaseOfficer> expected = Optional.of(testOfficer);
		
		when(stringUtil.toLowerCase(param)).thenReturn(toLowerCaseParam);
		when(officerRepo.findByEmail(toLowerCaseParam)).thenReturn(Optional.of(testOfficer));
		
		assertEquals(expected, officerService.findByEmail(param).get());		
	}
	
	@Test
	public void test_findByEmail_with_null_email_return_empty_optional() throws InterruptedException, ExecutionException {
		assertFalse(officerService.findByEmail(null).get().isPresent());
	}
	
	@Test
	public void test_findByCity_with_valid_param_CompletableFuture_contains_expected_list() throws InterruptedException, ExecutionException {
		String param = "Växjö";
		List<CaseOfficer> expected = Arrays.asList(testOfficer);
		when(officerRepo.findCaseOfficerByCity(param)).thenReturn(Arrays.asList(testOfficer));
		assertEquals(expected, officerService.findByCity(param).get());		
	}
	
	@Test
	public void test_test_findByCity_with_null_param_return_empty_arraylist() throws InterruptedException, ExecutionException {
		assertTrue(officerService.findByCity(null).get().isEmpty());
	}
	
	

}
