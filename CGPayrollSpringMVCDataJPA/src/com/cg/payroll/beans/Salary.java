package com.cg.payroll.beans;
import javax.persistence.Embeddable;

@Embeddable
public class Salary{
	private float basicSalary,hra,conveyenceAllowance; 
	private float otherAllowance,personalAllowance,monthlyTax,epf;
	private float companyPf,gratuity,grossSalary,netSalary;

	public Salary() {
		super();
	}
	public Salary(float basicSalary, float epf, float companyPf) {
		super();
		this.basicSalary = basicSalary;
		this.epf = epf;
		this.companyPf = companyPf;
	}
	public Salary(float basicSalary, float hra, float conveyenceAllowance, float otherAllowance,
			float personalAllowance, float monthlyTax, float epf, float companyPf, float gratuity, float grossSalary,
			float netSalary) {
		super();
		this.basicSalary = basicSalary;
		this.hra = hra;
		this.conveyenceAllowance = conveyenceAllowance;
		this.otherAllowance = otherAllowance;
		this.personalAllowance = personalAllowance;
		this.monthlyTax = monthlyTax;
		this.epf = epf;
		this.companyPf = companyPf;
		this.gratuity = gratuity;
		this.grossSalary = grossSalary;
		this.netSalary = netSalary;
	}
	public float getBasicSalary() {
		return basicSalary;
	}
	public void setBasicSalary(float basicSalary) {
		this.basicSalary = basicSalary;
	}
	public float getHra() {
		return hra;
	}
	public void setHra(float hra) {
		this.hra = hra;
	}
	public float getConveyenceAllowance() {
		return conveyenceAllowance;
	}
	public void setConveyenceAllowance(float conveyenceAllowance) {
		this.conveyenceAllowance = conveyenceAllowance;
	}
	public float getOtherAllowance() {
		return otherAllowance;
	}
	public void setOtherAllowance(float otherAllowance) {
		this.otherAllowance = otherAllowance;
	}
	public float getPersonalAllowance() {
		return personalAllowance;
	}
	public void setPersonalAllowance(float personalAllowance) {
		this.personalAllowance = personalAllowance;
	}
	public float getMonthlyTax() {
		return monthlyTax;
	}
	public void setMonthlyTax(float monthlyTax) {
		this.monthlyTax = monthlyTax;
	}
	public float getEpf() {
		return epf;
	}
	public void setEpf(float epf) {
		this.epf = epf;
	}
	public float getCompanyPf() {
		return companyPf;
	}
	public void setCompanyPf(float companyPf) {
		this.companyPf = companyPf;
	}
	public float getGratuity() {
		return gratuity;
	}
	public void setGratuity(float gratuity) {
		this.gratuity = gratuity;
	}
	public float getGrossSalary() {
		return grossSalary;
	}
	public void setGrossSalary(float grossSalary) {
		this.grossSalary = grossSalary;
	}
	public float getNetSalary() {
		return netSalary;
	}
	public void setNetSalary(float netSalary) {
		this.netSalary = netSalary;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(basicSalary);
		result = prime * result + Float.floatToIntBits(companyPf);
		result = prime * result + Float.floatToIntBits(conveyenceAllowance);
		result = prime * result + Float.floatToIntBits(epf);
		result = prime * result + Float.floatToIntBits(gratuity);
		result = prime * result + Float.floatToIntBits(grossSalary);
		result = prime * result + Float.floatToIntBits(hra);
		result = prime * result + Float.floatToIntBits(monthlyTax);
		result = prime * result + Float.floatToIntBits(netSalary);
		result = prime * result + Float.floatToIntBits(otherAllowance);
		result = prime * result + Float.floatToIntBits(personalAllowance);
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
		Salary other = (Salary) obj;
		if (Float.floatToIntBits(basicSalary) != Float.floatToIntBits(other.basicSalary))
			return false;
		if (Float.floatToIntBits(companyPf) != Float.floatToIntBits(other.companyPf))
			return false;
		if (Float.floatToIntBits(conveyenceAllowance) != Float.floatToIntBits(other.conveyenceAllowance))
			return false;
		if (Float.floatToIntBits(epf) != Float.floatToIntBits(other.epf))
			return false;
		if (Float.floatToIntBits(gratuity) != Float.floatToIntBits(other.gratuity))
			return false;
		if (Float.floatToIntBits(grossSalary) != Float.floatToIntBits(other.grossSalary))
			return false;
		if (Float.floatToIntBits(hra) != Float.floatToIntBits(other.hra))
			return false;
		if (Float.floatToIntBits(monthlyTax) != Float.floatToIntBits(other.monthlyTax))
			return false;
		if (Float.floatToIntBits(netSalary) != Float.floatToIntBits(other.netSalary))
			return false;
		if (Float.floatToIntBits(otherAllowance) != Float.floatToIntBits(other.otherAllowance))
			return false;
		if (Float.floatToIntBits(personalAllowance) != Float.floatToIntBits(other.personalAllowance))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Salary [basicSalary=" + basicSalary + ", hra=" + hra + ", conveyenceAllowance=" + conveyenceAllowance
				+ ", otherAllowance=" + otherAllowance + ", personalAllowance=" + personalAllowance + ", monthlyTax="
				+ monthlyTax + ", epf=" + epf + ", companyPf=" + companyPf + ", gratuity=" + gratuity + ", grossSalary="
				+ grossSalary + ", netSalary=" + netSalary + "]";
	}
}