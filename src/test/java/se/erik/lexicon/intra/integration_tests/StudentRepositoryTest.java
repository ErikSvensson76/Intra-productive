package se.erik.lexicon.intra.integration_tests;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import se.erik.lexicon.intra.data_access.StudentRepository;
import se.erik.lexicon.intra.entity.student.Student;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class StudentRepositoryTest {
	
	private static final String LAST_NAME = "Testsson";

	private static final String FIRST_NAME = "Test";

	private static final LocalDate REG_DATE = LocalDate.parse("2018-12-01");

	private static final LocalDate BIRTH_DATE = LocalDate.parse("1999-09-09");

	private static final String EMAIL = "test@test.com";

	@Autowired
	private StudentRepository testRepo;
	
	@Autowired
	private TestEntityManager em;
	
	private List<Student> testStudents;
	private Student singleTestStudent;
	
	@Before
	public void init() {
		singleTestStudent = new Student(EMAIL, BIRTH_DATE, REG_DATE, FIRST_NAME, LAST_NAME);
		testStudents = new ArrayList<>();
		testStudents.add(singleTestStudent);
		testStudents.add(new Student("test2@test.com", BIRTH_DATE, LocalDate.parse("2018-09-11"), "Test2", "Testsson2"));
		testRepo.saveAll(testStudents);		
	}
	
	@After
	public void tearDown() {
		testRepo.deleteAll();
		em.flush();
	}
	
	@Test
	public void test_findByStudentEmail_return_Optional_of_singleTestStudent() {
		Optional<Student> expected = Optional.of(singleTestStudent);
		
		assertEquals(expected, testRepo.findByStudentEmail(EMAIL));
	}
	
	@Test
	public void test_findByBirthDate_return_Students_matching_given_birthDate() {
		List<Student> actual = testRepo.findByBirthDate(BIRTH_DATE);
		
		assertTrue(actual.stream().allMatch(student -> student.getBirthDate().equals(BIRTH_DATE)));
	}
	
	@Test
	public void test_findByRegDate_return_Students_matching_given_regDate() {
		List<Student> actual = testRepo.findByRegDate(REG_DATE);
		
		assertTrue(actual.stream().allMatch(student -> student.getRegDate().equals(REG_DATE)));
	}
	
	@Test
	public void test_searchAndFindByName_return_Students_with_fullName_containing_param() {
		String searchParam = "%test%";
		String expectedContains = "Test";
		
		List<Student> actual = testRepo.searchAndFindByName(searchParam);
		assertTrue(actual.stream().allMatch(student -> student.getFullName().contains(expectedContains)));
	}

}
