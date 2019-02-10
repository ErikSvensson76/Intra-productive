package se.erik.lexicon.intra.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import se.erik.lexicon.intra.entity.case_officer.CaseOfficer;
import se.erik.lexicon.intra.entity.case_officer.CaseOfficerView;
import se.erik.lexicon.intra.entity.decision.Decision;

public interface CaseOfficerService {
	
	CompletableFuture<Optional<CaseOfficer>> findByEmail(String email);
	CompletableFuture<List<CaseOfficer>> findByCity(String city);
	CompletableFuture<List<CaseOfficer>> findByPhoneNumber(String phoneNumber);
	CompletableFuture<List<CaseOfficer>> findByFullNameContains(String name);
	CompletableFuture<Optional<CaseOfficer>> findById(String id);
	CompletableFuture<List<CaseOfficer>> findAll();
	CompletableFuture<List<Decision>> fetchAllMadeDecisions(String officerId);
	CaseOfficer save(CaseOfficer caseOfficer);
	CaseOfficer update(CaseOfficer updated);
	boolean delete(CaseOfficer caseOfficer);
	CaseOfficerView convertToView(CaseOfficer caseOfficer);
}
