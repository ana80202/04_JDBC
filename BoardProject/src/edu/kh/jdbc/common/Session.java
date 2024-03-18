package edu.kh.jdbc.common;

import edu.kh.jdbc.member.model.dto.Member;

public class Session { //session : 클라이언트로부터 오는 일련의 요청을 하나의 상태로 보고 그 상태를 일정하게 유지하는 기술

	//로그인 : DB에 기록된 회원 정보를 가지고 오는 것이 로그인이다.
	//      -> 로그아웃을 할 때 까지 프로그램에서 회원 정보를 유지하고 있어야 한다.    
	
	public static Member loginMember = null;
	
	// loginMember == null -> 로그아웃 상태
	// loginMember != null -> 로그인 상태
	
	
	
	
}
