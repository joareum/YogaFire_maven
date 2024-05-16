package com.yogafire.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yogafire.model.dto.EmailAuthResponse;
import com.yogafire.model.service.EmailService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/email")
public class EmailController {
	private final EmailService emailService;
	
	@GetMapping("/auth")
	public EmailAuthResponse sendAuthCode(@RequestParam String address) {
		return emailService.sendEmail(address);
	}
	
	@PostMapping("/auth")
    public EmailAuthResponse checkAuthCode(@RequestParam String address, @RequestParam String authCode) {
        return emailService.validateAuthCode(address, authCode);
    }
}