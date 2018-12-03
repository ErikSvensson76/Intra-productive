package se.erik.lexicon.intra.enity_tests;

import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import se.erik.lexicon.intra.entity.case_officer.CaseOfficer;
import se.erik.lexicon.intra.entity.decision.Decision;
import se.erik.lexicon.intra.entity.student.Student;
import se.erik.lexicon.intra.enums.DecisionType;

import static org.junit.Assert.*;

import java.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

@RunWith(SpringJUnit4ClassRunner.class)
public class DecisionTest {

	private Decision testDecision;
	private Student testStudent;
	private CaseOfficer testCaseOfficer;
	
	@Before
	public void init() {
		testStudent = new Student("test@test.com", LocalDate.parse("1999-09-09"), LocalDate.parse("2018-12-01"),"Test", "Testsson");
		testCaseOfficer = new CaseOfficer("test@test.af.se","Växjö","070-1112223","TestOfficer","OfficerLastName");
		testDecision = new Decision(LocalDate.parse("2018-12-01"), 2, DecisionType.FUB, testStudent, testCaseOfficer);
	}
	
	@Test
	public void testDecision_was_successfully_created() {
		assertTrue(testDecision.getDecisionDate().equals(LocalDate.parse("2018-12-01")) &
				testDecision.getDurationInWeeks() == 2 &
				testDecision.getDecisionType() == DecisionType.FUB &
				testDecision.getStudent().equals(testStudent) &
				testDecision.getCaseOfficer().equals(testCaseOfficer) &
				testDecision.getEndDate().equals(LocalDate.parse("2018-12-01").plusWeeks(2)));
	}
	
	@Test
	public void testDecision_daysLeft_return_expected_value() {
		int expectedDaysLeft = 5;
		LocalDate paramToday = LocalDate.parse("2018-12-10");
		
		assertEquals(expectedDaysLeft, testDecision.daysLeft(paramToday));
	}
	
	@Test
	public void testDecision_daysLeft_return_negativeValue_and_setDecision_to_NONE() {
		int expectedValue = -1;
		DecisionType expectedType = DecisionType.NONE;
		LocalDate paramToday = LocalDate.parse("2018-12-16");
		int actualValue = testDecision.daysLeft(paramToday);
		
		assertTrue(expectedValue == actualValue & expectedType == testDecision.getDecisionType());
	}
	
}
