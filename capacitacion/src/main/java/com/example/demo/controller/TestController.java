package com.example.demo.controller;

import com.example.demo.dto.TestDTO;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/test")
@Slf4j
public class TestController {
	
	
	@GetMapping("/consultaGet")
	public ResponseEntity<?> consultarServiciosNovedades(@RequestParam(value="id", required = false) Long id) throws Exception {
		TestDTO tst= new TestDTO("get ok");
		return ResponseEntity.ok().body(tst);
	}
	
	@PostMapping("/consultaPost")
	public ResponseEntity<?> decryptToken(@RequestBody TestDTO request) throws Exception {
		TestDTO tst= new TestDTO("post ok");

		return ResponseEntity.ok().body(tst);
	}
}
