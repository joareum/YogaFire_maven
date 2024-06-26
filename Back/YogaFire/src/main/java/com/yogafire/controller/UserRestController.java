package com.yogafire.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yogafire.model.dto.User;
import com.yogafire.model.service.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
@CrossOrigin(value = "*")
@Tag(name = "UserRestController", description = "유저 관련")
public class UserRestController {
	private UserService userService;

	@Autowired
	private UserRestController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/signup")
	public ResponseEntity<?> regist(@RequestBody User user) {
		System.out.println(user);
		userService.signup(user);

		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}

	@PostMapping("/signin")
	public ResponseEntity<User> login(@RequestBody User user, HttpServletRequest request) {
		User tmp = userService.login(user.getUserId(), user.getPassword());

		if (tmp != null) {
			HttpSession session = request.getSession(true);
			session.setAttribute("loggedInUser", tmp);
			System.out.println("Logged in user ID set in session: " + tmp.getUserId());
			return new ResponseEntity<User>(tmp, HttpStatus.OK);
		} else {
			// 아이디나 비밀번호를 입력하지 않았을 때 - 400 에러
			if (user.getPassword() == "" || user.getPassword().isEmpty()) {
				return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
			} else if (user.getUserId() == "" || user.getUserId().isEmpty()) {
				return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
			} else {
				// 아이디나 비밀번호가 잘못되었을 때 - 401 에러
				return new ResponseEntity<User>(HttpStatus.UNAUTHORIZED);
			}
		}
	}

	// 아이디 중복 체크용
	@GetMapping("/checkDuplicateUserId/{userId}")
	public ResponseEntity<?> returnId(@PathVariable String userId) {
		int userCount = userService.findId(userId);

		// 아이디가 중복되지 않을 때
		if (userCount == 0) {
			return new ResponseEntity<>(false, HttpStatus.OK);
			// 아이디가 중복될 때
		} else {
			return new ResponseEntity<>(true, HttpStatus.OK);
		}
	}

	// 이메일 중복 체크용
	@GetMapping("/checkDuplicateUserEmail/{email}")
	public ResponseEntity<?> returnEmail(@PathVariable String email) {
		int emailCount = userService.findEmail(email);

		// 아이디가 중복되지 않을 때
		if (emailCount == 0) {
			System.out.println(emailCount);
			return new ResponseEntity<>(false, HttpStatus.OK);
			// 아이디가 중복될 때
		} else {
			return new ResponseEntity<>(true, HttpStatus.OK);
		}
	}

	// 닉네임 중복 체크용
	@GetMapping("/checkDuplicateUserNickname/{nickname}")
	public ResponseEntity<?> returnNickname(@PathVariable String nickname) {
		int nicknameCount = userService.findNickname(nickname);

		// 아이디가 중복되지 않을 때
		if (nicknameCount == 0) {
			System.out.println(nicknameCount);
			return new ResponseEntity<>(false, HttpStatus.OK);
			// 아이디가 중복될 때
		} else {
			return new ResponseEntity<>(true, HttpStatus.OK);
		}
	}

	// 비밀번호 찾기
	@GetMapping("/find-password")
	public ResponseEntity<?> returnPw(@RequestBody User user) {
		String pw = userService.findPw(user);
		return new ResponseEntity<String>(pw, HttpStatus.OK);
	}

	// 비밀번호 재설정 - id 가져와서 설정하기
	@PutMapping("/find-password/{userId}/{newPw}")
	public ResponseEntity<Void> changePw(@PathVariable String userId, @PathVariable String newPw) {
		boolean resetSuccess = userService.updatePw(userId, newPw);

		if (resetSuccess) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}

	}

	// 로그아웃
	@GetMapping("/signout")
	public ResponseEntity<Void> logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}

		// 클라이언트에게 쿠키 삭제 요청
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				cookie.setMaxAge(0);
				cookie.setValue(null);
				cookie.setPath("/");
				response.addCookie(cookie);
			}
		}

		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	// 마이페이지 -> 뭐뭐 끌어올건지.(회원정보 - selectOne, 회원정보 수정, 찜 정보도..)

	// 정보 수정
	@PatchMapping("/myInfo/modify-profile/{user_id}")
	public ResponseEntity<Void> modifyProfile(@PathVariable("user_id") String id, @RequestBody User user) {
		user.setUserId(id);
		userService.modifyUser(user);

		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	// 작성한 게시글 내역 조회 -> 게시글 파트에서 불러와야 할 듯..

	// 회원탈퇴 -> id로 삭제하려고 해서 url에 id 받아오려고 url 수정했음
	// 그리고 도메인 설계 엑셀에 왜 put 빨간색? delete 아닌가?? -> yn만 바꾸기
	@PutMapping("/myInfo/withdraw/{user_id}")
	public ResponseEntity<Void> deleteUser(@PathVariable("user_id") String id) {
		userService.outUser(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
