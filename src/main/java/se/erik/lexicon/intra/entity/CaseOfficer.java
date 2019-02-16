package se.erik.lexicon.intra.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class CaseOfficer {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String officerId;
	@Column(unique = true)
	private String officerEmail;
	private String officerCity;
	private String officerPhone;
	private String firstName;
	private String lastName;
	
	public CaseOfficer(String officerEmail, String officerCity, String officerPhone, String firstName,
			String lastName) {
		setOfficerEmail(officerEmail);
		setOfficerCity(officerCity);
		setOfficerPhone(officerPhone);
		setFirstName(firstName);
		setLastName(lastName);		
	}
	
	protected CaseOfficer() {}

	public String getOfficerEmail() {
		return officerEmail;
	}

	public void setOfficerEmail(String officerEmail) {
		this.officerEmail = officerEmail;
	}

	public String getOfficerCity() {
		return officerCity;
	}

	public void setOfficerCity(String officerCity) {
		this.officerCity = officerCity;
	}

	public String getOfficerPhone() {
		return officerPhone;
	}

	public void setOfficerPhone(String officerPhone) {
		this.officerPhone = officerPhone;
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

	public String getOfficerId() {
		return officerId;
	}
	
	public void setOfficerId(String officerId) {
		this.officerId = officerId;
	}
	
	public String getFullName() {
		return new StringBuilder(getFirstName() + " " + getLastName()).toString();
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((officerEmail == null) ? 0 : officerEmail.hashCode());
		result = prime * result + ((officerId == null) ? 0 : officerId.hashCode());
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
		CaseOfficer other = (CaseOfficer) obj;
		if (officerEmail == null) {
			if (other.officerEmail != null)
				return false;
		} else if (!officerEmail.equals(other.officerEmail))
			return false;
		if (officerId == null) {
			if (other.officerId != null)
				return false;
		} else if (!officerId.equals(other.officerId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CaseOfficer [officerId=" + officerId + ", officerEmail=" + officerEmail + ", officerCity=" + officerCity
				+ ", officerPhone=" + officerPhone + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}	
	
		

}
