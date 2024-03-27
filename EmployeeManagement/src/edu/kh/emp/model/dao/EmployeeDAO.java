package edu.kh.emp.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static edu.kh.emp.common.JDBCTemplate.*;
import edu.kh.emp.model.vo.Employee;

public class EmployeeDAO {
	
	//JDBC 객체 참조변수 선언
	private Statement stmt; //db쪽으로 결과값을 보내는거
	private PreparedStatement pstmt; //placeholder에 사용
	private ResultSet rs;//
	
	private Properties prop;
	
	public EmployeeDAO(){
		
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("query.xml"));
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	/** 전체 사원 정보 조회 DAO
	 * @param conn
	 * @return list
	 */
	public List<Employee> selectAll(Connection conn) throws Exception {
		
		//결과 저장용 변수 선언
		List<Employee> list = new ArrayList<Employee>();
		
		try {
			
			String sql = prop.getProperty("selectAll");
			
			//Statement 객체 생성
			stmt = conn.createStatement();
			
			//SQL 을 수행 후 결과 (resultset) 반환 받음
			rs = stmt.executeQuery(sql);
			
			//조회 결과를 얻어와 한 행씩 접근하여
			//Employee 객체 생성 후 컬럼값 담기
			//-> List 담기
			while(rs.next()){//rs행을 진행하면서 다음행이 있으면 true / 없으면 false
				
				int empId = rs.getInt("EMP_ID");
				//EMP_ID 컬럼은 문자열 컬럼이지만
				//저장된 값들은 모두 숫자형태
				//-> DB에서 자동으로 형변환 진행해서 얻어옴
				
				String empName = rs.getNString("EMP_NAME");
				String empNo = rs.getNString("EMP_NO");
				String email = rs.getNString("EMAIL");
				String phone = rs.getNString("PHONE");
				String departmentTitle = rs.getNString("DEPT_TITLE");
				String jobName = rs.getNString("JOB_NAME");
				int salary = rs.getInt("SALARY");
				
				Employee emp = new Employee(empId,empName, empNo,email, phone,
						                    departmentTitle,jobName,salary );
				
				list.add(emp); //List에 담기
				
			} //while 문 종료
			
		}finally {
			
			//사용한 JDBC 객체 자원 반환
			close(rs);
			close(stmt);
			
		}	
		return list;
	}

	/** 사원 정보 추가 DAO
	 * @param conn
	 * @param emp
	 * @return result (1/0)
	 */
	public int insertEmployee(Connection conn, Employee emp) throws Exception {
		
		int result = 0;
		
		try {
			//SQL 작성
			String sql = prop.getProperty("insertEmployee");
			//INSERT INTO EMPLOYEE VLAUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? , SYSDATE, NULL, DEFAULT)
			
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			//? (위치홀더)에 알맞은 값 대입
			pstmt.setInt(1,emp.getEmpId());
			pstmt.setString(2,emp.getEmpName());
			pstmt.setString(3,emp.getEmpNo());
			pstmt.setString(4, emp.getEmail());
			pstmt.setString(5, emp.getPhone());
			pstmt.setString(6, emp.getDeptCode());
			pstmt.setString(7, emp.getJobCode());
			pstmt.setString(8, emp.getSalLevel());
			pstmt.setInt(9, emp.getSalary());
			pstmt.setDouble(10, emp.getBonus());
			pstmt.setInt(11, emp.getManagerId());
			
			result = pstmt.executeUpdate();
			
			
		}finally {
			close(pstmt);
		}
		return result;
	}

	/**사번이 일치하는 사원 정보 조회 DAO
	 * @param conn
	 * @param empId
	 * @return emp
	 * @throws Exception
	 */
	public Employee selectEmpId(Connection conn, int empId) throws Exception {
		
		Employee emp = null;
		
		try {
			String sql = prop.getProperty("selectEmpId");
			
			pstmt = conn.prepareStatement(sql); // ?(placeholder)있으니까 Statement 준비해 줘야함
			
			pstmt.setInt(1, empId); //값 세팅해주기
			
			//stmt - ? (위치홀더 없을 때)
			//pstmt - ? ( 위치홀더 있을 때)
			//executeQuery - select -> ResultSet
			//execteUpdate - DML ( update , delete , Insert) -> int (성공한 행의 갯수)
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				String empName = rs.getString("EMP_NAME");
				String empNo = rs.getString("EMP_NO");
				String email = rs.getString("EMAIL");
				String phone = rs.getString("PHONE");
				String departmentTitle = rs.getString("DEPT_TITLE");
				String jobName = rs.getString("JOB_NAME");
				int salary = rs.getInt("SALARY");
				
				emp = new Employee(empId, empName, empNo, email,phone,departmentTitle,jobName,salary);
				
			}
			/*
	          while(rs.next()){//rs행을 진행하면서 다음행이 있으면 true / 없으면 false
				
				int selectempId = rs.getInt("EMP_ID");
				//EMP_ID 컬럼은 문자열 컬럼이지만
				//저장된 값들은 모두 숫자형태
				//-> DB에서 자동으로 형변환 진행해서 얻어옴
				
				String empName = rs.getNString("EMP_NAME");
				String empNo = rs.getNString("EMP_NO");
				String email = rs.getNString("EMAIL");
				String phone = rs.getNString("PHONE");
				String departmentTitle = rs.getNString("DEPT_TITLE");
				String jobName = rs.getNString("JOB_NAME");
				int salary = rs.getInt("SALARY");
				
				emp = new Employee(selectempId,empName, empNo,email, phone,
						                    departmentTitle,jobName,salary );	
			}
			
			*/
			
		}finally {
		    close(rs);
			close(pstmt);
		}
		return emp;
	}

	/** 사번이 일치하는 사원 정보 수정 DAO
	 * @param conn
	 * @param emp
	 * @return result
	 */
	public Employee updateEmployee(Connection conn, Employee emp) throws Exception {
		
		int result = 0;
		
		try {
			
            String sql = prop.getProperty("updateEmployee");
            
			pstmt = conn.prepareStatement(sql); // ?(placeholder)있으니까 Statement 준비해 줘야함
			
			pstmt.setString(1, emp.getEmail());
			pstmt.setString(2, emp.getPhone());
			pstmt.setInt(3, emp.getSalary());
			pstmt.setInt(4, emp.getEmpId());
			
			result = pstmt.executeUpdate();
			
		}finally {
			 close(pstmt);
		}
		
		return result;
		
		
		
	}

	
	
	
	
	
	
	
	
	
	
	
}
