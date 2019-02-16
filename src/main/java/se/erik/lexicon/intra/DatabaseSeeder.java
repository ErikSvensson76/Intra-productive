package se.erik.lexicon.intra;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import se.erik.lexicon.intra.data_access.CaseOfficerRepository;
import se.erik.lexicon.intra.data_access.DecisionRepository;
import se.erik.lexicon.intra.data_access.StudentRepository;
import se.erik.lexicon.intra.entity.case_officer.CaseOfficer;
import se.erik.lexicon.intra.entity.decision.Decision;
import se.erik.lexicon.intra.entity.student.Student;
import se.erik.lexicon.intra.enums.DecisionType;

@Component
@Transactional
public class DatabaseSeeder implements CommandLineRunner{
	
	private CaseOfficerRepository caseOfficerRepo;
	private DecisionRepository decisionRepo;
	private StudentRepository studentRepo;
	
	@Autowired	
	public DatabaseSeeder(CaseOfficerRepository caseOfficerRepo, DecisionRepository decisionRepo,
			StudentRepository studentRepo) {
		this.caseOfficerRepo = caseOfficerRepo;
		this.decisionRepo = decisionRepo;
		this.studentRepo = studentRepo;
	}

	@Override
	public void run(String... args) throws Exception {
		seedCaseOfficers();
		seedStudents();
		seedDecisions();
		
	}
	
	private void seedCaseOfficers() {
		CaseOfficer c1 = new CaseOfficer("test@test.com","Test city", "010-1234567","Test","Testsson");
		CaseOfficer c2 = new CaseOfficer("test2@test2.com", "Test city", "010-8910111","Test2","Testsson2");
		
		caseOfficerRepo.save(c1);
		caseOfficerRepo.save(c2);
		
		System.err.println(c1.getOfficerId());
	}
	
	private void seedStudents() {
		Student s1 = new Student("teststudent1@test.com", LocalDate.parse("2000-01-01"), "TestStudent", "Testsson");
		Student s2 = new Student("teststudent2@test.com", LocalDate.parse("1992-08-21"), "TestStudent2", "Testsson2");
		
		studentRepo.save(s1);
		studentRepo.save(s2);
	}
	
	private void seedDecisions() {
		Decision d1 = new Decision(
				LocalDate.now().minusWeeks(1),
				2,
				DecisionType.FUB, 
				studentRepo.findByStudentEmail("teststudent1@test.com").get(),
				caseOfficerRepo.findByEmail("test@test.com").get());
		
		decisionRepo.save(d1);				
	}

}
