package com.cg.payroll.beans;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;
import javax.validation.Valid;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@SecondaryTables(
						value={
						@SecondaryTable(name="Salary",pkJoinColumns=@PrimaryKeyJoinColumn(name="associateId")),
						@SecondaryTable(name="BankDetails",pkJoinColumns=@PrimaryKeyJoinColumn(name="associateId"))
				})
public class Associate {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int associateId; 

	private int yearlyInvestmentUnder8oC;
	@NotEmpty	
	private String firstName;
	@NotEmpty
	private String lastName;
	@NotEmpty
	private String  department;
	@NotEmpty
	private String designation;
	@NotEmpty
	private String pancard;
	@NotEmpty
	@Email
	private String emailId; 

	@Valid
	@Embedded
	@AttributeOverrides(value={
				@AttributeOverride(name="basicSalary",column=@Column(name="basicSalary",table="Salary")),
				@AttributeOverride(name="hra",column=@Column(name="hra",table="Salary")),
				@AttributeOverride(name="conveyanceAllowance",column=@Column(name="conveyanceAllowance",table="Salary")),
				@AttributeOverride(name="otherAllowance",column=@Column(name="otherAllowance",table="Salary")),
				@AttributeOverride(name="personalAllowance",column=@Column(name="personalAllowance",table="Salary")),
				@AttributeOverride(name="monthlyTax",column=@Column(name="monthlyTax",table="Salary")),
				@AttributeOverride(name="epf",column=@Column(name="epf",table="Salary")),
				@AttributeOverride(name="companyPf",column=@Column(name="companyPf",table="Salary")),
				@AttributeOverride(name="gratuity",column=@Column(name="gratuity",table="Salary")),
				@AttributeOverride(name="grossSalary",column=@Column(name="grossSalary",table="Salary")),
				@AttributeOverride(name="netSalary",column=@Column(name="netSalary",table="Salary"))
	})
	private Salary salary;
	
	@Valid
	@Embedded
	@AttributeOverrides(value={
				@AttributeOverride(name="accountNo",column=@Column(name="accountNo",table="BankDetails")),
				@AttributeOverride(name="bankName",column=@Column(name="bankName",table="BankDetails")),
				@AttributeOverride(name="ifscCode",column=@Column(name="ifscCode",table="BankDetails")),
	})	
	private BankDetails bankDetails;
	public Associate() {
	}
	public Associate(int associateId, int yearlyInvestmentUnder8oC,
			String firstName, String lastName, String department,
			String designation, String pancard, String emailId, Salary salary,
			BankDetails bankDetails) {
		super();
		this.associateId = associateId;
		this.yearlyInvestmentUnder8oC = yearlyInvestmentUnder8oC;
		this.firstName = firstName;
		this.lastName = lastName;
		this.department = department;
		this.designation = designation;
		this.pancard = pancard;
		this.emailId = emailId;
		this.salary = salary;
		this.bankDetails = bankDetails;
	}
	
	public Associate(int yearlyInvestmentUnder8oC, String firstName,
			String lastName, String department, String designation,
			String pancard, String emailId, Salary salary,
			BankDetails bankDetails) {
		super();
		this.yearlyInvestmentUnder8oC = yearlyInvestmentUnder8oC;
		this.firstName = firstName;
		this.lastName = lastName;
		this.department = department;
		this.designation = designation;
		this.pancard = pancard;
		this.emailId = emailId;
		this.salary = salary;
		this.bankDetails = bankDetails;
	}
	public int getAssociateId() {
		return associateId;
	}
	public void setAssociateId(int associateId) {
		this.associateId = associateId;
	}
	public int getYearlyInvestmentUnder8oC() {
		return yearlyInvestmentUnder8oC;
	}
	public void setYearlyInvestmentUnder8oC(int yearlyInvestmentUnder8oC) {
		this.yearlyInvestmentUnder8oC = yearlyInvestmentUnder8oC;
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
	public Salary getSalary() {
		return salary;
	}
	public void setSalary(Salary salary) {
		this.salary = salary;
	}
	public BankDetails getBankDetails() {
		return bankDetails;
	}
	public void setBankDetails(BankDetails bankDetails) {
		this.bankDetails = bankDetails;
	}
	@Override
	public String toString() {
		return "Associate [associateId=" + associateId + ", yearlyInvestmentUnder8oC=" + yearlyInvestmentUnder8oC
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", department=" + department
				+ ", designation=" + designation + ", pancard=" + pancard + ", emailId=" + emailId + ", salary="
				+ salary + ", bankDetails=" + bankDetails + "]";
	}
}
