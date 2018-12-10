package se.erik.lexicon.intra.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import se.erik.lexicon.intra.data_access.CaseOfficerRepository;
import se.erik.lexicon.intra.data_access.DecisionRepository;
import se.erik.lexicon.intra.entity.case_officer.CaseOfficer;
import se.erik.lexicon.intra.entity.decision.Decision;
import se.erik.lexicon.intra.utils.StringUtil;

@Service
@Transactional
public class CaseOfficerServiceImpl implements CaseOfficerService {
	
	private CaseOfficerRepository officerRepo;
	private DecisionRepository decisionRepo;
	private StringUtil stringUtil;
	
	@Autowired	
	public CaseOfficerServiceImpl(CaseOfficerRepository officerRepo, DecisionRepository decisionRepo,
			StringUtil stringUtil) {
		this.officerRepo = officerRepo;
		this.decisionRepo = decisionRepo;
		this.stringUtil = stringUtil;
	}

	@Override
	@Async
	public CompletableFuture<Optional<CaseOfficer>> findByEmail(String email) {
		return email == null ? CompletableFuture.completedFuture(Optional.empty()) 
				: CompletableFuture.completedFuture(officerRepo.findByEmail(stringUtil.toLowerCase(email)));		 
	}

	@Override
	@Async
	public CompletableFuture<List<CaseOfficer>> findByCity(String city) {
		return city == null ? CompletableFuture.completedFuture(new ArrayList<>()) 
				: CompletableFuture.completedFuture(officerRepo.findCaseOfficerByCity(city));
	}

	@Override
	@Async
	public CompletableFuture<List<CaseOfficer>> findByPhoneNumber(String phoneNumber) {
		return phoneNumber == null ? CompletableFuture.completedFuture(new ArrayList<>()) 
				: CompletableFuture.completedFuture(officerRepo.findCaseOfficerByPhoneNumber(phoneNumber));
	}

	@Override
	@Async
	public CompletableFuture<List<CaseOfficer>> findByFullNameContains(String name) {
		return name == null ? CompletableFuture.completedFuture(new ArrayList<>()) 
				: CompletableFuture.completedFuture(officerRepo.searchAndFindByName(stringUtil.surroundWithSqlWildCards(name)));
	}

	@Override
	@Async
	public CompletableFuture<Optional<CaseOfficer>> findById(String id) {
		return id == null ? CompletableFuture.completedFuture(Optional.empty()) : CompletableFuture.completedFuture(officerRepo.findById(id));
	}

	@Override
	@Async
	public CompletableFuture<List<CaseOfficer>> findAll() {
		return CompletableFuture.completedFuture((List<CaseOfficer>)officerRepo.findAll());
	}

	@Override
	@Async
	public CompletableFuture<List<Decision>> fetchAllMadeDecisions(String officerId) {
		return officerId == null ? CompletableFuture.completedFuture(new ArrayList<>()) 
				: CompletableFuture.completedFuture(decisionRepo.findDecisionByOfficerId(officerId));
	}

	@Override
	public CaseOfficer save(CaseOfficer caseOfficer) throws IllegalArgumentException {
		if(caseOfficer == null) throw new IllegalArgumentException("CaseOfficer caseOfficer was " + caseOfficer);
		
		return officerRepo.save(caseOfficer);
	}

	@Override
	public boolean delete(CaseOfficer caseOfficer) throws IllegalArgumentException{		
		if(caseOfficer == null) throw new IllegalArgumentException("Not possible to delete a null entity");
		if(caseOfficer.getOfficerId() == null) throw new IllegalArgumentException("CaseOfficer caseOfficer has a null id");
		
		officerRepo.delete(caseOfficer);
		
		//Inverted Boolean expression because in order to return true when delete operation was successful
		return !officerRepo.existsById(caseOfficer.getOfficerId());		
	}

	@Override
	public CaseOfficer update(CaseOfficer updated) throws IllegalArgumentException {
		if(updated == null) throw new IllegalArgumentException("Param passed in was null");
		if(updated.getOfficerId() == null) throw new IllegalArgumentException("CaseOfficer has a null id");
		
		Optional<CaseOfficer> result = officerRepo.findById(updated.getOfficerId());
		if(result.isPresent()) {
			CaseOfficer original = result.get();
			original.setFirstName(updated.getFirstName());
			original.setLastName(updated.getLastName());
			original.setOfficerCity(updated.getOfficerCity());
			original.setOfficerEmail(updated.getOfficerEmail());
			original.setOfficerPhone(updated.getOfficerPhone());
			return save(original);
		}else {
			return save(updated);
		}		
	}

}
