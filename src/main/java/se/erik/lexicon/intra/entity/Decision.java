package se.erik.lexicon.intra.entity;

import java.time.LocalDate;
import java.time.Period;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

import se.erik.lexicon.intra.enums.DecisionType;

@Entity
public class Decision {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String decisionId;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate decisionDate;
	private int durationInWeeks;
	private DecisionType decisionType;
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.LAZY)
	@JoinColumn(name = "STUDENT_ID")
	private Student student;
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.LAZY)
	@JoinColumn(name = "OFFICER_ID")
	private CaseOfficer caseOfficer;
	
	public Decision(LocalDate decisionDate, int durationInWeeks, DecisionType decisionType, Student student, CaseOfficer caseOfficer) {
		setDecisionDate(decisionDate);
		setDurationInWeeks(durationInWeeks);
		setDecisionType(decisionType);
		setStudent(student);
		setCaseOfficer(caseOfficer);
	}
	
	protected Decision() {}

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

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}	
	
	public CaseOfficer getCaseOfficer() {
		return caseOfficer;
	}

	public void setCaseOfficer(CaseOfficer caseOfficer) {
		this.caseOfficer = caseOfficer;
	}

	public LocalDate getEndDate() {
		return getDecisionDate().plusWeeks(durationInWeeks);
	}

	public String getDecisionId() {
		return decisionId;
	}
	
	public int daysLeft() {
		return daysLeft(LocalDate.now());		
	}
	
	/**
	 * Intended to be used directly only for testing
	 * @param LocalDate test LocalDate representing "today"
	 * @return int representing days until decision runs out
	 */
	public int daysLeft(LocalDate today) {
		LocalDate endDate = getEndDate();
		Period untilEnd = Period.between(today, endDate);
		if(untilEnd.isNegative()) {
			setDecisionType(DecisionType.NONE);			
		}
		return untilEnd.getDays();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((decisionDate == null) ? 0 : decisionDate.hashCode());
		result = prime * result + ((decisionId == null) ? 0 : decisionId.hashCode());
		result = prime * result + ((decisionType == null) ? 0 : decisionType.hashCode());
		result = prime * result + durationInWeeks;
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
		Decision other = (Decision) obj;
		if (decisionDate == null) {
			if (other.decisionDate != null)
				return false;
		} else if (!decisionDate.equals(other.decisionDate))
			return false;
		if (decisionId == null) {
			if (other.decisionId != null)
				return false;
		} else if (!decisionId.equals(other.decisionId))
			return false;
		if (decisionType != other.decisionType)
			return false;
		if (durationInWeeks != other.durationInWeeks)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Decision [decisionId=" + decisionId + ", decisionDate=" + decisionDate + ", durationInWeeks="
				+ durationInWeeks + ", decisionType=" + decisionType + "]";
	}	
	
}
