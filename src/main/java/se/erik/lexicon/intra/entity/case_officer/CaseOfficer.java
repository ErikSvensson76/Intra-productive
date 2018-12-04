package se.erik.lexicon.intra.entity.case_officer;

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
	
	public String getFullName() {
		return new StringBuilder(getFirstName() + " " + getLastName()).toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((officerCity == null) ? 0 : officerCity.hashCode());
		result = prime * result + ((officerEmail == null) ? 0 : officerEmail.hashCode());
		result = prime * result + ((officerId == null) ? 0 : officerId.hashCode());
		result = prime * result + ((officerPhone == null) ? 0 : officerPhone.hashCode());
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
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (officerCity == null) {
			if (other.officerCity != null)
				return false;
		} else if (!officerCity.equals(other.officerCity))
			return false;
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
		if (officerPhone == null) {
			if (other.officerPhone != null)
				return false;
		} else if (!officerPhone.equals(other.officerPhone))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CaseOfficer [officerId=" + officerId + ", officerEmail=" + officerEmail + ", officerCity=" + officerCity
				+ ", officerPhone=" + officerPhone + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}
	
	
		

}
