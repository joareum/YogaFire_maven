package com.yogafire.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yogafire.model.dto.FindIdForm;
import com.yogafire.model.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final UserService userService;

    @GetMapping("/find-id")
    public String findId(@ModelAttribute("form") FindIdForm form) {
        return "members/findId";
    }

    @PostMapping("/find-id")
    public String showId(@Valid @ModelAttribute("form") FindIdForm form, BindingResult bindingResult, Model model) {

        if(bindingResult.hasErrors()) {
            return "members/findId";
        }

        String encodedId = userService.findLoginIdByEmail(form.getEmail());
        model.addAttribute("encodedId", encodedId);
        return "members/showId";
    }
}
