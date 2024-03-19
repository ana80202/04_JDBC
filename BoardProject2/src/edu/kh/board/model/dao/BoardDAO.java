package edu.kh.board.model.dao;

import java.io.FileInputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class BoardDAO {
	
	//JDBC 객체 참조 변수 
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	//XML에 작성된 SQL을 읽어와 저장할 객체를 참조하는 변수
	private Properties prop;
	
	public BoardDAO() {
		
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("board-sql.xml"));
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
