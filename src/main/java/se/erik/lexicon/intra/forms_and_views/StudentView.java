package se.erik.lexicon.intra.forms_and_views;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class StudentView {

	private String studentId;
	private String studentEmail;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthDate;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate regDate;
	private String firstName;
	private String lastName;
	private DecisionView decision;
	
	public StudentView() {
	}

	public StudentView(String studentId, String studentEmail, LocalDate birthDate, LocalDate regDate, String firstName,
			String lastName, DecisionView decision) {
		this.studentId = studentId;
		this.studentEmail = studentEmail;
		this.birthDate = birthDate;
		this.regDate = regDate;
		this.firstName = firstName;
		this.lastName = lastName;
		this.decision = decision;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getStudentEmail() {
		return studentEmail;
	}

	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public LocalDate getRegDate() {
		return regDate;
	}

	public void setRegDate(LocalDate regDate) {
		this.regDate = regDate;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public DecisionView getDecision() {
		return decision;
	}

	public void setDecision(DecisionView decision) {
		this.decision = decision;
	}	
}
