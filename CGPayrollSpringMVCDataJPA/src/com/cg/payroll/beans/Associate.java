package com.cg.payroll.beans;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotEmpty;
@Entity
public class Associate {
	@Embedded
	@Valid
	private Salary salary;
	@Embedded
	@Valid
	private BankDetails bankDetail;
	@Id
//	@NotEmpty
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int associateID;
	public Associate(Salary salary, BankDetails bankDetail, String password, float yearlyInvestmentUnder80C,
			String firstName, String lastName, String department, String designation, String pancard, String emailId) {
		super();
		this.salary = salary;
		this.bankDetail = bankDetail;
		this.password = password;
		this.yearlyInvestmentUnder80C = yearlyInvestmentUnder80C;
		this.firstName = firstName;
		this.lastName = lastName;
		this.department = department;
		this.designation = designation;
		this.pancard = pancard;
		this.emailId = emailId;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	@NotEmpty
	private String password;
	private float yearlyInvestmentUnder80C;
	@NotEmpty
	private String firstName;
	@NotEmpty
	private String lastName;
	@NotEmpty
	private String department;
	@NotEmpty
	private String designation;
	@NotEmpty
	private String pancard;
	@NotEmpty
	private String emailId;
	public Associate(){}

	public Associate(Salary salary, BankDetails bankDetail, float yearlyInvestmentUnder80C, String firstName,
			String lastName, String department, String designation, String pancard, String emailId) {
		super();
		this.salary = salary;
		this.bankDetail = bankDetail;
		this.yearlyInvestmentUnder80C = yearlyInvestmentUnder80C;
		this.firstName = firstName;
		this.lastName = lastName;
		this.department = department;
		this.designation = designation;
		this.pancard = pancard;
		this.emailId = emailId;
	}

	public Associate(Salary salary, BankDetails bankDetail, int associateID, float yearlyInvestmentUnder80C,
			String firstName, String lastName, String department, String designation, String pancard, String emailId) {
		super();
		this.salary = salary;
		this.bankDetail = bankDetail;
		this.associateID = associateID;
		this.yearlyInvestmentUnder80C = yearlyInvestmentUnder80C;
		this.firstName = firstName;
		this.lastName = lastName;
		this.department = department;
		this.designation = designation;
		this.pancard = pancard;
		this.emailId = emailId;
	}
	public Salary getSalary() {
		return salary;
	}
	public void setSalary(Salary salary) {
		this.salary = salary;
	}
	public BankDetails getBankDetail() {
		return bankDetail;
	}
	public void setBankDetail(BankDetails bankDetail) {
		this.bankDetail = bankDetail;
	}
	public int getAssociateID() {
		return associateID;
	}
	public void setAssociateID(int associateID) {
		this.associateID = associateID;
	}
	public float getYearlyInvestmentUnder80C() {
		return yearlyInvestmentUnder80C;
	}
	public void setYearlyInvestmentUnder80C(float yearlyInvestmentUnder80C) {
		this.yearlyInvestmentUnder80C = yearlyInvestmentUnder80C;
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
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getPancard() {
		return pancard;
	}
	public void setPancard(String pancard) {
		this.pancard = pancard;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + associateID;
		result = prime * result + ((bankDetail == null) ? 0 : bankDetail.hashCode());
		result = prime * result + ((department == null) ? 0 : department.hashCode());
		result = prime * result + ((designation == null) ? 0 : designation.hashCode());
		result = prime * result + ((emailId == null) ? 0 : emailId.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((pancard == null) ? 0 : pancard.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((salary == null) ? 0 : salary.hashCode());
		result = prime * result + Float.floatToIntBits(yearlyInvestmentUnder80C);
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
		Associate other = (Associate) obj;
		if (associateID != other.associateID)
			return false;
		if (bankDetail == null) {
			if (other.bankDetail != null)
				return false;
		} else if (!bankDetail.equals(other.bankDetail))
			return false;
		if (department == null) {
			if (other.department != null)
				return false;
		} else if (!department.equals(other.department))
			return false;
		if (designation == null) {
			if (other.designation != null)
				return false;
		} else if (!designation.equals(other.designation))
			return false;
		if (emailId == null) {
			if (other.emailId != null)
				return false;
		} else if (!emailId.equals(other.emailId))
			return false;
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
		if (pancard == null) {
			if (other.pancard != null)
				return false;
		} else if (!pancard.equals(other.pancard))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (salary == null) {
			if (other.salary != null)
				return false;
		} else if (!salary.equals(other.salary))
			return false;
		if (Float.floatToIntBits(yearlyInvestmentUnder80C) != Float.floatToIntBits(other.yearlyInvestmentUnder80C))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Associate [salary=" + salary + ", bankDetail=" + bankDetail + ", associateID=" + associateID
				+ ", yearlyInvestmentUnder80C=" + yearlyInvestmentUnder80C + ", firstName=" + firstName + ", lastName="
				+ lastName + ", department=" + department + ", designation=" + designation + ", pancard=" + pancard
				+ ", emailId=" + emailId + "]";
	}
}	