package se.erik.lexicon.intra.enity_tests;

import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import se.erik.lexicon.intra.entity.CaseOfficer;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

@RunWith(SpringJUnit4ClassRunner.class)
public class CaseOfficerTest {
	
	private CaseOfficer testCaseOfficer;
	private static final String EMAIL = "test@test.af.se";
	private static final String CITY = "Växjö";
	private static final String PHONE = "070-2222222";
	private static final String FIRST_NAME = "Test";
	private static final String LAST_NAME = "Testsson";
	
	@Before
	public void init() {
		testCaseOfficer = new CaseOfficer(EMAIL,CITY,PHONE,FIRST_NAME,LAST_NAME);
	}
	
	@Test
	public void test_caseOfficer_was_created_correctly() {
		assertTrue(testCaseOfficer.getOfficerEmail().equals(EMAIL) &
				testCaseOfficer.getOfficerCity().equals(CITY) &
				testCaseOfficer.getOfficerPhone().equals(PHONE) &
				testCaseOfficer.getFirstName().equals(FIRST_NAME) &
				testCaseOfficer.getLastName().equals(LAST_NAME));
	}
	
	@Test
	public void test_caseOfficer_fullName_is_Test_Testsson() {
		String expected = "Test Testsson";
		assertEquals(expected, testCaseOfficer.getFullName());
	}

}
