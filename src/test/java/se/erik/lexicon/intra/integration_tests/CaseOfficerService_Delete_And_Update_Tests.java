package se.erik.lexicon.intra.integration_tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import se.erik.lexicon.intra.data_access.CaseOfficerRepository;
import se.erik.lexicon.intra.data_access.DecisionRepository;
import se.erik.lexicon.intra.entity.case_officer.CaseOfficer;
import se.erik.lexicon.intra.service.CaseOfficerService;
import se.erik.lexicon.intra.service.CaseOfficerServiceImpl;
import se.erik.lexicon.intra.utils.StringUtil;


@RunWith(SpringRunner.class)
@DataJpaTest
public class CaseOfficerService_Delete_And_Update_Tests {
	
	@TestConfiguration
	static class CaseOfficer_Delete_And_Update_TestsConfig {
		@Bean
		public CaseOfficerService caseOfficerService(CaseOfficerRepository officerRepo, DecisionRepository decisionRepo,
				StringUtil stringUtil) {
			return new CaseOfficerServiceImpl(officerRepo,decisionRepo,stringUtil);			
		}
		
		@Bean
		public StringUtil stringUtil() {
			return new StringUtil();
		}
	}
	
	@Autowired
	private CaseOfficerService testService;
	
	@Autowired
	private TestEntityManager em;
	
	private CaseOfficer testCaseOfficer;

	@Before
	public void init() {
		testCaseOfficer = new CaseOfficer("test.af@test.com","Växjö","04701234567", "Test", "Testsson");
		em.persistAndFlush(testCaseOfficer);
	}
	
	@After
	public void tearDown() {
		try {
			em.remove(testCaseOfficer);			
		}catch(IllegalArgumentException e) {
			//muted
		}
		em.flush();		
	}
	
	@Test
	public void test_delete_testCaseOfficer_success_return_true() {
		assertTrue(testService.delete(testCaseOfficer));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_delete_nonExisting_CaseOfficer_throws_IllegalArgumentException() {
		CaseOfficer testCaseOfficer = new CaseOfficer("test@test.com","Ljungby","123456789","Test", "Testsson");
		testService.delete(testCaseOfficer);		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_delete_null_CaseOfficer_throws_IllegalArgumentException() {
		testService.delete(null);
	}
	
	@Test
	public void test_update_detatched_testCaseOfficer_return_testCaseOfficer() {
		testCaseOfficer.setFirstName("Nils");
		em.detach(testCaseOfficer);
		em.flush();
		
		assertEquals(testCaseOfficer, testService.update(testCaseOfficer));			
	}
	
		
}
