package edu.kh.emp.model.service;

import java.sql.Connection;
import java.util.List;

import static edu.kh.emp.common.JDBCTemplate. *;
import edu.kh.emp.model.dao.EmployeeDAO;
import edu.kh.emp.model.vo.Employee;

public class EmployeeService {
	
	private EmployeeDAO dao = new EmployeeDAO();

	/**전체 사원 정보 조회 서비스
	 * @return list
	 */
	public List<Employee> selectAll() throws Exception {
		
		//1.커넥션 생성
		Connection conn = getConnection();
		
		List<Employee> list = dao.selectAll(conn);
		
		close(conn);
	
		
		return list;
	}
	
	

	/**사원 정보 추가 서비스
	 * @param emp
	 * @return result (1/0)
	 */
	public int insertEmployee(Employee emp) throws Exception {
	
		Connection conn = getConnection();
		
		int result = dao.insertEmployee(conn,emp);
		
		if(result > 0) commit(conn);
		else          rollback(conn);
		
		close(conn);
		
		return result;
	}



	/**입력 받은 사원 정보 조회 서비스
	 * @param empId
	 * @return emp<Employee>
	 */
	public Employee selectEmpId(int empId) throws Exception {
		
		Connection conn = getConnection();
		
		Employee emp = dao.selectEmpId(conn,empId);
		
		close(conn);
		

		return emp;
	}



	/** 입력 받은 사원 정보 수정 서비스
	 * @param emp2
	 * @return
	 */
	public Employee updateEmployee(Employee emp2) throws Exception {
		
		Connection conn = getConnection();
		
		Employee emp = dao.updateEmployee(conn,emp2);
		
		close(conn);
		
		return emp;
	}
	
	
	
	
	

}
