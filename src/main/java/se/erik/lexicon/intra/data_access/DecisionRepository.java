package se.erik.lexicon.intra.data_access;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import se.erik.lexicon.intra.entity.decision.Decision;
import se.erik.lexicon.intra.enums.DecisionType;

public interface DecisionRepository extends CrudRepository<Decision, String> {
	
	List<Decision> findByDecisionDate(LocalDate date);
	List<Decision> findByDurationInWeeks(int weeks);
	List<Decision> findByDecisionType(DecisionType type);
	
	@Query("SELECT d FROM Decision d WHERE d.student.studentId = :studentId")
	List<Decision> findDecisionsByStudentId(@Param("studentId")String studentId);
	
	@Query("SELECT d FROM Decision d WHERE d.caseOfficer.officerId = :officerId")
	List<Decision> findDecisionByOfficerId(@Param("officerId") String officerId);

}
