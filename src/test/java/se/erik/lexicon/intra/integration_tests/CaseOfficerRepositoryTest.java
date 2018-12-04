package se.erik.lexicon.intra.integration_tests;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import se.erik.lexicon.intra.data_access.CaseOfficerRepository;
import se.erik.lexicon.intra.entity.case_officer.CaseOfficer;

import static org.junit.Assert.*;
import org.junit.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CaseOfficerRepositoryTest {
	
	@Autowired
	private CaseOfficerRepository testRepo;
	
	@Autowired
	private TestEntityManager em;
	
	private static final String EMAIL = "test.af@test.se";
	private static final String CITY = "Växjö";
	private static final String PHONE = "0470-1234567";
	private static final String FIRST_NAME = "Test";
	private static final String LAST_NAME = "Testsson";
	
	
	private List<CaseOfficer> testCaseOfficers;
	private CaseOfficer testCaseOfficer;
	
	@Before
	public void init() {
		testCaseOfficer = new CaseOfficer(EMAIL,CITY,PHONE,FIRST_NAME,LAST_NAME);
		testCaseOfficers = new ArrayList<>();
		testCaseOfficers.add(testCaseOfficer);
		testCaseOfficers.add(new CaseOfficer("test2.af@test.com","Ljungby","070-12345674","Test","Testsson2"));
		testCaseOfficers.add(new CaseOfficer("test3.af@test.com", "Växjö","0470-0943875","Nils","Persson"));
		testRepo.saveAll(testCaseOfficers);
	}
	
	@After
	public void tearDown() {
		testRepo.deleteAll();
		em.flush();
	}
	
	@Test
	public void test_findByEmail_return_optional_of_testCaseOfficer() {
		Optional<CaseOfficer> expected = Optional.of(testCaseOfficer);
		
		assertEquals(expected, testRepo.findByEmail(EMAIL));
	}
	
	@Test
	public void test_findCaseOfficerByCity_all_contains_expected_cityName() {
		String param = "Växjö";
		List<CaseOfficer> result = testRepo.findCaseOfficerByCity(param);
		assertTrue(result.stream().allMatch(co -> co.getOfficerCity().equals(param)));
	}
	
	@Test
	public void test_findByCaseOfficerPhoneNumber_contains_expected_phoneNumber() {
		String param = PHONE; //0470-1234567
		List<CaseOfficer> result = testRepo.findCaseOfficerByPhoneNumber(param);
		assertTrue(result.stream().allMatch(co -> co.getOfficerPhone().equals(param)));		
	}
	
	@Test
	public void test_searchAndFindByName_given_param_contains_expected() {
		String param = "%TeSt%";
		String expected = "Test";		
		List<CaseOfficer> result = testRepo.searchAndFindByName(param);
		assertTrue(result.stream().allMatch(co -> co.getFullName().contains(expected)));
	}
}
