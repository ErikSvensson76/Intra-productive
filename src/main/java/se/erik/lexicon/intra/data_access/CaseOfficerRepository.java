package se.erik.lexicon.intra.data_access;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import se.erik.lexicon.intra.entity.CaseOfficer;

public interface CaseOfficerRepository extends CrudRepository<CaseOfficer, String> {
	
	@Query("SELECT co FROM CaseOfficer co WHERE co.officerEmail = :email")
	Optional<CaseOfficer> findByEmail(@Param("email")String email);
	
	@Query("SELECT co FROM CaseOfficer co WHERE co.officerCity = :city")
	List<CaseOfficer> findCaseOfficerByCity(@Param("city")String city);
	
	@Query("SELECT co FROM CaseOfficer co WHERE co.officerPhone = :phone")
	List<CaseOfficer> findCaseOfficerByPhoneNumber(@Param("phone")String phone);
	
	@Query("SELECT co FROM CaseOfficer co WHERE UPPER(CONCAT(co.firstName, ' ', co.lastName)) like UPPER(:name)")
	List<CaseOfficer> searchAndFindByName(@Param("name") String name);
	
}
