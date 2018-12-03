package se.erik.lexicon.intra.entity.student;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Student {
	
	private String studentId;
	private String studentEmail;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthDate;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private final LocalDate regDate;
	private String firstName;
	private String lastName;
	
	public Student(String studentEmail, LocalDate birthDate, LocalDate regDate, String firstName, String lastName) {
		setStudentEmail(studentEmail);
		setBirthDate(birthDate);		
		this.regDate = regDate;
		setFirstName(firstName);
		setLastName(lastName);		
	}

	public Student(String studentEmail, LocalDate birthDate, String firstName, String lastName) {
		this(studentEmail,birthDate,LocalDate.now(), firstName,lastName);
	}
	
	protected Student() {
		this.regDate = LocalDate.now();
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

	public String getStudentId() {
		return studentId;
	}

	public LocalDate getRegDate() {
		return regDate;
	}
	
	public String getFullName() {
		return new StringBuilder(getFirstName() + " " + getLastName()).toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((studentEmail == null) ? 0 : studentEmail.hashCode());
		result = prime * result + ((studentId == null) ? 0 : studentId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (studentEmail == null) {
			if (other.studentEmail != null)
				return false;
		} else if (!studentEmail.equals(other.studentEmail))
			return false;
		if (studentId == null) {
			if (other.studentId != null)
				return false;
		} else if (!studentId.equals(other.studentId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", studentEmail=" + studentEmail + ", birthDate=" + birthDate
				+ ", regDate=" + regDate + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}
}
