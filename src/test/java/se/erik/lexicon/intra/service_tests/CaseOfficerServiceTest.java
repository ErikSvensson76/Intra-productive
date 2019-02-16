package se.erik.lexicon.intra.service_tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import se.erik.lexicon.intra.data_access.CaseOfficerRepository;
import se.erik.lexicon.intra.data_access.DecisionRepository;
import se.erik.lexicon.intra.entity.CaseOfficer;
import se.erik.lexicon.intra.entity.Decision;
import se.erik.lexicon.intra.entity.Student;
import se.erik.lexicon.intra.enums.DecisionType;
import se.erik.lexicon.intra.service.CaseOfficerService;
import se.erik.lexicon.intra.service.CaseOfficerServiceImpl;
import se.erik.lexicon.intra.utils.StringUtil;

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
	public void test_findByCity_with_null_param_return_empty_arraylist() throws InterruptedException, ExecutionException {
		assertTrue(officerService.findByCity(null).get().isEmpty());
	}
	
	@Test
	public void test_findByPhoneNumber_with_valid_param_CompletableFuture_contains_expected_list() throws InterruptedException, ExecutionException {
		 String param = "0470-1111111";
		 List<CaseOfficer> expected = Arrays.asList(testOfficer);
		 when(officerRepo.findCaseOfficerByPhoneNumber(param)).thenReturn(Arrays.asList(testOfficer));
		 assertEquals(expected, officerService.findByPhoneNumber(param).get());		 
	}
	
	@Test
	public void test_findByPhoneNUmber_with_null_param_return_empty_arraylist() throws InterruptedException, ExecutionException {
		assertTrue(officerService.findByPhoneNumber(null).get().isEmpty());
	}
	
	@Test
	public void test_findByFullName_valid_param_returns_list_all_contains_param() throws InterruptedException, ExecutionException {
		String param = "Test";
		String paramAfterStringUtil = "%Test%";
		when(stringUtil.surroundWithSqlWildCards(param)).thenReturn(paramAfterStringUtil);
		when(officerRepo.searchAndFindByName(paramAfterStringUtil)).thenReturn(Arrays.asList(testOfficer, testOfficer2));
		List<CaseOfficer> actual = officerService.findByFullNameContains(param).get();
		
		assertTrue(actual.stream().allMatch(officer -> officer.getFullName().contains(param)));		
	}
	
	@Test
	public void test_findByFullName_with_null_param_return_empty_arrayList() throws InterruptedException, ExecutionException {
		assertTrue(officerService.findByFullNameContains(null).get().isEmpty());
	}
	
	@Test
	public void test_findById_with_valid_param_CompletableFuture_contains_Optional_of_testOfficer() throws InterruptedException, ExecutionException {
		String param = anyString();
		Optional<CaseOfficer> expected = Optional.of(testOfficer);
		when(officerRepo.findById(param)).thenReturn(Optional.of(testOfficer));
		
		assertEquals(expected, officerService.findById(param).get());
	}
	
	@Test
	public void test_findById_with_null_param_CompletableFuture_contains_empty_Optional() throws InterruptedException, ExecutionException {
		assertFalse(officerService.findById(null).get().isPresent());
	}
	
	@Test
	public void test_findAll_CompletableFuture_contains_list_of_2_elements() throws InterruptedException, ExecutionException {
		int expectedSize = 2;
		when(officerRepo.findAll()).thenReturn(Arrays.asList(testOfficer,testOfficer2));
		assertEquals(expectedSize, officerService.findAll().get().size());
	}
	
	@Test
	public void test_fetchAllMadeDecisions_valid_param_CompletableFuture_contains_list_of_testDecision() throws InterruptedException, ExecutionException {
		Decision testDecision = new Decision(
				LocalDate.parse("2018-12-01"), 
				2, 
				DecisionType.FUB, 
				new Student("test@test.com",LocalDate.parse("1999-09-09"),"Erik","Svensson"), 
				testOfficer
				);
		
		List<Decision> expected = Arrays.asList(testDecision);		
		String officerId = anyString();		
		
		when(decisionRepo.findDecisionByOfficerId(officerId)).thenReturn(Arrays.asList(testDecision));
		
		assertEquals(expected, officerService.fetchAllMadeDecisions(officerId).get());
	}
	
	@Test
	public void test_fetchAllMadeDecisions_with_null_param_CompletableFuture_contains_empty_arrayList() throws InterruptedException, ExecutionException {
		assertTrue(officerService.fetchAllMadeDecisions(null).get().isEmpty());
	}

}
