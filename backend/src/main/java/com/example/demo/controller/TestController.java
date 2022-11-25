package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ResponseDTO;
import com.example.demo.dto.TestRequestBodyDTO;

@RestController
@RequestMapping("test") // 리소스
public class TestController {

	@GetMapping
	public String testControllerString() {
		return "Hellow World";
	}

	@GetMapping("/testGetMapping")
	public String testControllerWithPath() {
		return "Hellow World testGetMapping";
	}

	@GetMapping("/{id}")
	public String testControllerWithPathValuable(@PathVariable(required = false) int id) {
		return "Hellow World! ID" + id;
	}

	@GetMapping("/testRequestParam")
	public String testControllerWithRequstParam(@RequestParam(required = false) int id) {
		return "Hellow World! ID: " + id;
	}
	
	@GetMapping("/testRequestBody")
	public String testControllerRequestBody(@RequestBody TestRequestBodyDTO testRequestBodyDTO) {
		return "Hellow World! ID: " + testRequestBodyDTO.getId() + " Message : " + testRequestBodyDTO.getMessage();
	}
	
	@GetMapping("/testResponseBody")
	public ResponseDTO<String> testControllerResponseBody(){
		List<String> list = new ArrayList<>();
		list.add("Hello World! I'm ResponseDTO");
		ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
//		ResponseEntity는 Body 정보 뿐만 아니라 헤더의 상태 등을 조작할 수 있다. 
//		ResponseEntity.ok().body(response);
//		ResponseEntity.badRequest().body(response);
		return response;
	}
}
