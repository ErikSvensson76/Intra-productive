package se.erik.lexicon.intra.enity_tests;

import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import se.erik.lexicon.intra.entity.student.Student;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

@RunWith(SpringJUnit4ClassRunner.class)
public class StudentTest {
	
	
	private Student testStudent;
	private static final LocalDate BIRTH_DATE = LocalDate.parse("1999-09-09");
	private static final String EMAIL = "test@gmail.com";
	private static final LocalDate REG_DATE = LocalDate.parse("2018-01-01");
	private static final String FIRST_NAME = "Test";
	private static final String LAST_NAME = "Testsson";
	
	
	@Before
	public void init() {
		testStudent = new Student(EMAIL, BIRTH_DATE,REG_DATE,FIRST_NAME,LAST_NAME);
	}
	
	@Test
	public void test_testStudent_all_values_matches() {
		assertTrue(testStudent.getBirthDate().equals(BIRTH_DATE)
				& testStudent.getFirstName().equals(FIRST_NAME)
				& testStudent.getLastName().equals(LAST_NAME)
				& testStudent.getRegDate().equals(REG_DATE)
				& testStudent.getStudentEmail().equals(EMAIL));
	}
	
	@Test
	public void test_testStudent_fullName_is_Test_Testsson() {
		String expected = "Test Testsson";
		assertEquals(expected, testStudent.getFullName());
	}
	
	
}
