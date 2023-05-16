package com.example.hanghaetinder_bemain.domain.member.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hanghaetinder_bemain.domain.common.dto.DefaultDataRes;
import com.example.hanghaetinder_bemain.domain.common.dto.DefaultRes;
import com.example.hanghaetinder_bemain.domain.common.dto.ResponseMessage;
import com.example.hanghaetinder_bemain.domain.member.dto.request.LoginRequestDto;
import com.example.hanghaetinder_bemain.domain.member.dto.request.SignupRequestDto;
import com.example.hanghaetinder_bemain.domain.member.dto.resoponse.LoginResponseDto;
import com.example.hanghaetinder_bemain.domain.member.service.MemberService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {

	private final MemberService memberService;

	//회원가입
	@PostMapping(value = "user/signup" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity signup(@ModelAttribute final SignupRequestDto signUpRequestDto) {

		memberService.signup(signUpRequestDto);

		return ResponseEntity.ok(new DefaultRes(ResponseMessage.CREATED_USER));
	}

	@PostMapping("user/login")
	public ResponseEntity login(@RequestBody final LoginRequestDto loginRequestDto, final HttpServletResponse response) {
		final LoginResponseDto loginResponseDto = memberService.login(loginRequestDto, response);
		return ResponseEntity.ok(new DefaultDataRes<>(ResponseMessage.LOGIN_SUCCESS, loginResponseDto));
	}

}
