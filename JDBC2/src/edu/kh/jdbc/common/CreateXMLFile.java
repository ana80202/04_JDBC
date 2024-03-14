package edu.kh.jdbc.common;

import java.io.FileOutputStream;
import java.util.Properties;
import java.util.Scanner;

public class CreateXMLFile {
	
	public static void main(String[] args) {
		
		//XML(eXtensible Markup Language) : 단순화된 데이터 기술 형식 파일
		//                                  (텍스트 기반의 형식, 사람이 읽기 쉽게해주고 컴퓨터가 읽기 쉽게 해줌)
		
		//XML에 저장되는 데이터 형식 key : value 형식이다.
		//-> key,value 모두 string(문자열) 형식이다.
		
		//XML 파일을 읽고, 쓰기 위한 ID 관련 클래스 필요
		
		//*properties 컬렉션 객체*
		// -Map 후손 클래스
		//-Key, Value 모두 String(문자열)
		//-XML 파일을 읽고, 쓰는데 특화된 메서드 제공
		
		try {
			
			Scanner sc = new Scanner(System.in);
			
			//properties 객체 생성
			Properties prop = new Properties();  // Properties -> 줄여서 prop 이라고 부름
			
			System.out.print("생성할 파일 이름 : ");
			String fileName = sc.next();
			
			//FileOutputStram 생성
			FileOutputStream fos = new FileOutputStream(fileName + ".xml");
			
			//properties 객체를 이용해서 XML 파일생성
			prop.storeToXML(fos, fileName + ".xml file!!!");
			    //1. FileOutputStream 변수명, 2. 파일이름
			
			System.out.println(fileName + ".xml 파일 생성 완료");
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		                            
		
		
		
		
		
		
	}
}
