package se.erik.lexicon.intra.forms_and_views;

import java.util.ArrayList;
import java.util.List;

import org.springframework.lang.Nullable;

import se.erik.lexicon.intra.entity.Decision;
import se.erik.lexicon.intra.entity.Student;

public class CaseOfficerView {
	
	private String id;
	private String email;
	private String city;
	private String phone;
	private String firstName;
	private String lastName;
	@Nullable
	private List<Decision> decisions = new ArrayList<>();
	@Nullable
	private List<Student> students = new ArrayList<>();	
	
	public CaseOfficerView() {}
	
	public CaseOfficerView(String id, String email, String city, String phone, String firstName, String lastName,
			List<Decision> decisions, List<Student> students) {
		this(id, email, city, phone, firstName, lastName);
		this.decisions = decisions;
		this.students = students;
	}	

	public CaseOfficerView(String id, String email, String city, String phone, String firstName, String lastName) {
		this.id = id;
		this.email = email;
		this.city = city;
		this.phone = phone;
		this.firstName = firstName;
		this.lastName = lastName;
	}	

	public String getId() {return id;}

	public String getEmail() {return email;}

	public String getCity() {return city;}

	public String getPhone() {return phone;}

	public String getFirstName() {return firstName;}

	public String getLastName() {return lastName;}

	public List<Decision> getDecisions() {return decisions;}
	
	public void setDecisions(List<Decision> decisions) {this.decisions = decisions;}	

	public List<Student> getStudents() {return students;}

	public void setStudents(List<Student> students) {this.students = students;}
	
	public String getFullName() {
		return new StringBuilder().append(firstName).append(" ").append(lastName).toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		CaseOfficerView other = (CaseOfficerView) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
