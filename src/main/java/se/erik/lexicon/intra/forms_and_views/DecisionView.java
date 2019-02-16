package se.erik.lexicon.intra.forms_and_views;

import java.time.LocalDate;

import se.erik.lexicon.intra.enums.DecisionType;

public class DecisionView {
	
	private String decisionId;	
	private LocalDate decisionDate;	
	private int durationInWeeks;	
	private DecisionType decisionType;
	private StudentView student = new StudentView();
	private CaseOfficerView caseOfficer = new CaseOfficerView();
	
	public DecisionView() {
	}

	public DecisionView(String decisionId, LocalDate decisionDate, int durationInWeeks, DecisionType decisionType,
			StudentView student, CaseOfficerView caseOfficer) {
		this.decisionId = decisionId;
		this.decisionDate = decisionDate;
		this.durationInWeeks = durationInWeeks;
		this.decisionType = decisionType;
		this.student = student;
		this.caseOfficer = caseOfficer;
	}

	public String getDecisionId() {
		return decisionId;
	}

	public void setDecisionId(String decisionId) {
		this.decisionId = decisionId;
	}

	public LocalDate getDecisionDate() {
		return decisionDate;
	}

	public void setDecisionDate(LocalDate decisionDate) {
		this.decisionDate = decisionDate;
	}

	public int getDurationInWeeks() {
		return durationInWeeks;
	}

	public void setDurationInWeeks(int durationInWeeks) {
		this.durationInWeeks = durationInWeeks;
	}

	public DecisionType getDecisionType() {
		return decisionType;
	}

	public void setDecisionType(DecisionType decisionType) {
		this.decisionType = decisionType;
	}

	public StudentView getStudent() {
		return student;
	}

	public void setStudent(StudentView student) {
		this.student = student;
	}

	public CaseOfficerView getCaseOfficer() {
		return caseOfficer;
	}

	public void setCaseOfficer(CaseOfficerView caseOfficer) {
		this.caseOfficer = caseOfficer;
	}
}
