//package com.yogafire.model.service;
//
//import com.yogafire.model.Member;
//import com.yogafire.repository.MemberRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.UUID;
//
//@Service
//public class MemberService {
//
//    private final MemberRepository memberRepository;
//    private final Map<String, String> emailVerificationMap; // 이메일 인증을 위한 맵
//
//    @Autowired
//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//        this.emailVerificationMap = new HashMap<>();
//    }
//
//    public String initiateEmailVerification(String email) {
//        // 인증 코드 생성
//        String verificationCode = UUID.randomUUID().toString();
//        // 이메일과 인증 코드를 맵에 저장
//        emailVerificationMap.put(verificationCode, email);
//        // 이메일에 인증 링크를 포함하여 전송
//        sendVerificationEmail(email, verificationCode);
//        // 생성된 인증 코드를 반환
//        return verificationCode;
//    }
//
//    public String findLoginIdByEmail(String verificationCode) {
//        // 인증 코드를 사용하여 이메일 주소를 가져옴
//        String email = emailVerificationMap.get(verificationCode);
//        // 이메일 주소로 회원을 검색
//        Member member = memberRepository.findByEmail(email);
//        if (member == null) {
//            throw new RuntimeException("회원을 찾을 수 없습니다.");
//        }
//        return member.getLoginId();
//    }
//
//    private void sendVerificationEmail(String email, String verificationCode) {
//        // 여기에 이메일 전송 로직을 구현
//        // 예를 들어 JavaMailSender를 사용하여 이메일을 보낼 수 있습니다.
//        // 또는 메일 서비스의 API를 사용할 수도 있습니다.
//        // 실제 구현은 프로젝트에 맞게 선택하고 구현하시면 됩니다.
//        System.out.println("Verification email sent to: " + email);
//        System.out.println("Verification code: " + verificationCode);
//    }
//}