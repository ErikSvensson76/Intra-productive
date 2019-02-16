package se.erik.lexicon.intra.data_access;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import se.erik.lexicon.intra.entity.Student;

public interface StudentRepository extends CrudRepository<Student, String> {
	
	Optional<Student> findByStudentEmail(String studentEmail);
	List<Student> findByBirthDate(LocalDate birthDate);
	List<Student> findByRegDate(LocalDate regDate);	
	@Query("SELECT s FROM Student s WHERE UPPER(CONCAT(s.firstName, ' ', s.lastName)) like UPPER(:name)")
	List<Student> searchAndFindByName(@Param("name")String name);	
}
