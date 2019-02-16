package se.erik.lexicon.intra.entity.decision;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CaseOfficerForm {
	
	private String id;
	@NotBlank
	@Email(message = "Måste vara en giltig email adress som innehåller @ och en domän (t.ex XXX@XXX.com).")
	@Size(min = 10, max = 255, message = "Måste innehålla minst 10 tecken.")
	private String email;
	@NotBlank
	@Size(min = 3, max = 255, message = "Måste innehålla minst 3 tecken.")
	private String city;
	@NotBlank
	@Size(min = 10, max = 255,  message = "Telefonnummer Måste innehålla minst 10 tecken.")
	private String phone;
	@NotBlank
	@Size(min = 2, max = 255, message = "Förnamn måste innehålla minst 2 bokstäver")
	private String firstName;
	@NotBlank
	@Size(min = 2, max = 255, message = "Efternamn måste innehålla minst 2 bokstäver")
	private String lastName;
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
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
	
	
	
}
