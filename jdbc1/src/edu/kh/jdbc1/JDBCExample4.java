package edu.kh.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.kh.jdbc1.model.vo.Employee;

public class JDBCExample4 {
	
	public static void main(String[] args) {
		
		//직급명, 급여를 입력받아
		//해당 직급에서 입력 받은 급여 보다 많이 받는 사원의
		//이름, 직급명, 급여 , 연봉을 조회하여 출력
		
		//단, 조회 결과가 없으면 "조회 결과 없음" 출력
		
		//조회 결과가 있으면 아래와 같이 출력
		//직급명 입력 : 부사장
		//급여 입력 : 5000000
		//송종기 / 부사장 / 6000000/ 7200000(연봉) 
		//노옹철 / 부사장 / 3700000/ 4400000
		//...
		
		//Employee (empName,jobName,salary,annualIncome)
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		Scanner sc = new Scanner(System.in);
		
		try {
			
			System.out.print("직급명 입력 : ");
			String input1 = sc.nextLine();
			
			System.out.print("급여 입력 : ");
			long input2 = sc.nextLong();
			
			 Class.forName("oracle.jdbc.driver.OracleDriver");
				
				String type = "jdbc:oracle:thin:@"; // jdbc 드라이버 종류
				String ip = "localhost"; //db 서버 컴퓨터 ip
				String port = ":1521"; //포트번호
				String sid = ":XE"; //db 이름
				String user = "kh_cdy"; //사용자 계정
				String pw = "kh_cdy"; //비밀번호
				
				conn = DriverManager.getConnection(type+ip+port+sid, user,pw );
			
				String sql =
						"SELECT EMP_NAME , JOB_NAME , SALARY , SALARY * 12 ANNUALINCOME" 
						+" FROM EMPLOYEE"
						+" JOIN JOB USING(JOB_CODE)" 
						+" WHERE JOB_NAME = '" + input1 + "'"
						+" AND SALARY >" + input2;
				
				 stmt = conn.createStatement();
					
				 rs = stmt.executeQuery(sql);
				 
				 List<Employee> list = new ArrayList<Employee>();
				 
				 while(rs.next()) {
					 
					 String empName = rs.getString("EMP_NAME");
					 String jobName = rs.getString("JOB_NAME");
					 int salary = rs.getInt("SALARY");
					 long annulIncome = rs.getLong("ANNUALINCOME");
					 
					 Employee employee = new Employee(empName ,jobName,salary,annulIncome);
					 
					 list.add(employee);
				 }
				 
				 if(list.isEmpty()) {
					 System.out.println("조회 결과 없음");
				 }else {
					 for(Employee employee : list) {
						 System.out.println(employee);
					 }
				 }
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(conn != null) conn.close();
				if(stmt != null) stmt.close();
				if(rs != null) rs.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
		
	}

}