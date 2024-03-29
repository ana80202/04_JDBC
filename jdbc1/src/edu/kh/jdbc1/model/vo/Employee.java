package edu.kh.jdbc1.model.vo;

public class Employee {
	
	private String empName;
	private String jobName;
	private int salary;
	private long annulIncome;
	
	private String hireDate;
	private char gender;
	
	public Employee(){}

	public Employee(String empName, String jobName, int salary, long annulIncome) {
		super();
		this.empName = empName;
		this.jobName = jobName;
		this.salary = salary;
		this.annulIncome = annulIncome;
	}

	public String getEmpName() {
		return empName;
	}

	public String getHireDate() {
		return hireDate;
	}

	public void setHireDate(String hireDate) {
		this.hireDate = hireDate;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public long getAnnulIncome() {
		return annulIncome;
	}

	public void setAnnulIncome(long annulIncome) {
		this.annulIncome = annulIncome;
	}

	@Override
	public String toString() {
		return "이름 : " + empName + " / 직급명 : " + jobName + " / 급여 : " + salary + " / 연봉 : "
				+ annulIncome;
	}
	
	
	
	
	
	
	
}
