package edu.kh.emp.view;

import java.util.Scanner;

public class EmployeeView {
	
	Scanner sc = new Scanner(System.in);
	
	//기본생성자
	EmployeeView(){}
	
	public void startview() {
		 
		int input = 0;

		do {
			
			System.out.print("메뉴 선택");
			int inpu1= sc.nextInt();
			
			
			switch(input) {
			case 1 : EmployeeFullView(); break;
			case 2 : EmployeeDetailView(); break;
			case 3 : EmployeeAdd(); break; //추가
			case 4 : EmployeeUpdate(); break; //수정
			case 5 : EmployeeDelete(); break; //삭제
			
			}
			
			
			
			try {
				
				
			}catch(NumberFormatException e) {
				System.out.println("숫자만 입력해주세요");
				e.printStackTrace();
				input = -1;
			}
			

			
		}while(input != 0);
		
		
		
		
		
		
	}
	
	
	
	
	
	public void selectMenu() {}
	
	public void EmployeeFullView() {}
	
	public void EmployeeDetailView() {}
	
	public void  EmployeeAdd() {}
	
	public void  EmployeeUpdate() {}
	
	public void  EmployeeDelete() {}
	
	

}
