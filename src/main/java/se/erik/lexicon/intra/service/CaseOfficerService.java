package se.erik.lexicon.intra.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import se.erik.lexicon.intra.entity.CaseOfficer;
import se.erik.lexicon.intra.entity.Decision;
import se.erik.lexicon.intra.forms_and_views.CaseOfficerView;

public interface CaseOfficerService {

	CompletableFuture<Optional<CaseOfficer>> findByEmail(String email);

	CompletableFuture<List<CaseOfficer>> findByCity(String city);

	CompletableFuture<List<CaseOfficer>> findByPhoneNumber(String phoneNumber);

	CompletableFuture<List<CaseOfficer>> findByFullNameContains(String name);

	CompletableFuture<Optional<CaseOfficer>> findById(String id);

	CompletableFuture<List<CaseOfficer>> findAll();

	CompletableFuture<List<Decision>> fetchAllMadeDecisions(String officerId);

	CaseOfficer save(CaseOfficer caseOfficer) throws IllegalArgumentException;

	boolean delete(CaseOfficer caseOfficer) throws IllegalArgumentException;

	CaseOfficer update(CaseOfficer updated) throws IllegalArgumentException;

	CaseOfficerView convertToView(CaseOfficer caseOfficer);
}

	


