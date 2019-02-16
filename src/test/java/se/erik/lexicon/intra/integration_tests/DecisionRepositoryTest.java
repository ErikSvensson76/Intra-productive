package se.erik.lexicon.intra.integration_tests;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import se.erik.lexicon.intra.data_access.DecisionRepository;
import se.erik.lexicon.intra.entity.CaseOfficer;
import se.erik.lexicon.intra.entity.Decision;
import se.erik.lexicon.intra.entity.Student;
import se.erik.lexicon.intra.enums.DecisionType;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DecisionRepositoryTest {
	
	@Autowired
	private TestEntityManager em;
	
	@Autowired
	private DecisionRepository testRepo;
	
	private String officerId;
	private String studentId;
	private Decision testDecision;
	
	@Before
	public void init() {
		CaseOfficer co = new CaseOfficer("test.af@af.se","Växjö","0470-1234567", "Nils", "Persson");
		Student student = new Student("test@gmail.com",LocalDate.parse("1999-09-09"),"Test", "Testsson");
		testDecision = new Decision(LocalDate.parse("2018-12-01"), 2, DecisionType.FUB, student, co);
		testRepo.save(testDecision);
		officerId = co.getOfficerId();
		studentId = student.getStudentId();
	}
	
	@After
	public void tearDown() {
		testRepo.deleteAll();
		em.flush();
		em.remove(em.find(CaseOfficer.class, officerId));
		em.flush();
		em.remove(em.find(Student.class, studentId));
		em.flush();
	}
	
	@Test
	public void test_findByDecisionDate_returns_listElements_all_match_param() {
		LocalDate param = LocalDate.parse("2018-12-01");
		List <Decision> result = testRepo.findByDecisionDate(param);
		
		assertTrue(result.stream().allMatch(d->d.getDecisionDate().equals(param)));		
	}
	
	@Test
	public void test_findByDurationInWeek_returns_listElements_all_match_param() {
		int weeksParam = 2;
		List<Decision> result = testRepo.findByDurationInWeeks(weeksParam);
		assertTrue(result.stream().allMatch(d->d.getDurationInWeeks() == weeksParam));
	}
	
	@Test
	public void test_findByDecisionType_param_FUB_return_listElements_match_FUB() {
		DecisionType expected = DecisionType.FUB;
		List<Decision> result = testRepo.findByDecisionType(DecisionType.FUB);
		assertTrue(result.stream().allMatch(d->d.getDecisionType() == expected));
	}
	
	@Test
	public void test_findDecisionsByStudentId_return_List_of_testDecision() {
		List<Decision> expected = Arrays.asList(testDecision);		
		assertEquals(expected, testRepo.findDecisionsByStudentId(studentId));
	}
	
	@Test
	public void test_findDecisionsByOfficerId_return_List_od_testDecision() {
		List<Decision> expected = Arrays.asList(testDecision);
		assertEquals(expected, testRepo.findDecisionByOfficerId(officerId));
	}
	
	@Test
	public void test_findDecisionByDecisionType_FUB_return_list_of_testDecision() {
		List<Decision> expected = Arrays.asList(testDecision);
		assertEquals(expected, testRepo.findByDecisionType(DecisionType.FUB));
	}
	
	
	
}
