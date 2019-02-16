package se.erik.lexicon.intra.forms_and_views;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonFormat;

import se.erik.lexicon.intra.enums.DecisionType;

public class DecisionForm {
	
	//Internal
	
	private String decisionId;
	@NotBlank
	@JsonFormat(pattern = "yyyy-MM-dd")
	@PastOrPresent(message = "Beslutsdatum får inte vara i framtiden")
	private LocalDate decisionDate;
	@Positive(message = "Beslutslängden måste anges med en positive siffra")
	private int durationInWeeks;
	@NotNull(message = "Beslutstypen måste anges")
	private DecisionType decisionType;
	
	//External	
	private LocalDate endDate;
	private int daysLeft;
	
	
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
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public int getDaysLeft() {
		return daysLeft;
	}
	public void setDaysLeft(int daysLeft) {
		this.daysLeft = daysLeft;
	}
	
	
	

}
