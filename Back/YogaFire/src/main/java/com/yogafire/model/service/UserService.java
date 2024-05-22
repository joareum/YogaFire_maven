package com.yogafire.model.service;

import java.util.List;

import com.yogafire.model.dto.User;

public interface UserService {
	// 회원 정보 조회 ( 전체 회원 조회 )
	public List<User> getUserList();

	// 사용자 등록하기 ( 회원가입 ) - registUser
	public void signup(User user);

	// 로그인 하기 - signIn
	public User login(String id, String password);

	// 아이디 중복체크 - 아이디 찾기
	public int findId(String id);

	// 이메일 중복체크 - 이메일 찾기
	public int findEmail(String email);

	// 닉네임 중복체크 - 이메일 찾기
	public int findNickname(String nickname);

	// 비밀번호 찾기
	public String findPw(User user);

	// 비밀번호 재설정 -> id로 재설정
	public boolean updatePw(String id, String newPw);

	// 회원 정보 수정
	public void modifyUser(User user);

	// 회원 강제 탈퇴
	public void outUser(String userId);

	// 이메일을 통해서 회원가입 아이디 찾기
	public String findLoginIdByEmail(String email);

}
