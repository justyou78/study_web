package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ResponseDTO;
import com.example.demo.dto.TodoDTO;
import com.example.demo.model.TodoEntity;
import com.example.demo.service.TodoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("todo")
public class TodoController {

	@Autowired
	private TodoService service;

	@GetMapping("/test")
	public ResponseEntity<?> getTodo() {
//		ArrayList<TodoDTO> list = new ArrayList<>();
//		TodoDTO todoDTO = TodoDTO.builder().build();
//		todoDTO.setId("1");
//		todoDTO.setTitle("testTitle");
//		list.add(todoDTO);
//		ResponseDTO<TodoDTO> reponse = ResponseDTO.<TodoDTO>builder().data(list).build();

		String str = service.testService();
		List<String> list = new ArrayList<>();
		list.add(str);
		ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
		return ResponseEntity.ok().body(response);

	}

	@PostMapping
	public ResponseEntity<?> createTodo(@RequestBody TodoDTO dto) {
		try {
			String temporaryUserIdString = "temporary-user";

			TodoEntity entity = TodoDTO.toEntity(dto);

//			entity.setId(null);

			entity.setUserId(temporaryUserIdString);

//			서비스를 이용해 Todo 앤티티를 생성한다.
			List<TodoEntity> entities = service.create(entity);

//			자바 스트림을 이용해 리턴된 엔티티 리스트를 TodoDTO 리스트로 변환한다.
			List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());

			ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();

			return ResponseEntity.ok().body(response);

		} catch (Exception e) {
			String error = e.getMessage();
			ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().error(error).build();
			return ResponseEntity.badRequest().body(response);

		}
	}

	@GetMapping
	public ResponseEntity<?> retrieveTodoListEntity() {
		String temporaryUserIdString = "temporary-user";

		List<TodoEntity> entities = service.retrieve(temporaryUserIdString);

		List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());

		ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();

		return ResponseEntity.ok().body(response);
	}

	@PutMapping
	public ResponseEntity<?> updateTodo(@RequestBody TodoDTO dto) {
		String temporaryUserIdString = "temporary-user";

		TodoEntity entity = TodoDTO.toEntity(dto);

//		4장 인증과 인가에서 수정할 예정.
		entity.setUserId(temporaryUserIdString);

		List<TodoEntity> entities = service.update(entity);

		List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());

		ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();

		return ResponseEntity.ok().body(response);

	}

	@DeleteMapping
	public ResponseEntity<?> deleteDoto(@RequestBody TodoDTO dto) {
		try {
			String temporaryUserIdString = "temporary-user";

			TodoEntity entity = TodoDTO.toEntity(dto);

			entity.setUserId(temporaryUserIdString);

			List<TodoEntity> entities = service.delete(entity);

			List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());

			ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();

			return ResponseEntity.ok().body(response);
		} catch (Exception e) {
			String error = e.getMessage();
			ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().error(error).build();
			return ResponseEntity.badRequest().body(response);
		}
	}

}
