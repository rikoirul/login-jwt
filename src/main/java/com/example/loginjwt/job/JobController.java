package com.example.loginjwt.job;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jose4j.json.internal.json_simple.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/v1/")
public class JobController {
	private RestTemplate restTemplate;
	
	@GetMapping("getJobList")
	public ResponseEntity<List<Map<String, Object>>> getJobListAPI(){

		ObjectMapper mapper = new ObjectMapper();
		List<Map<String, Object>> mapResponse = new ArrayList<>();
		String getResponse;
		try {
			getResponse = getMethod("http://dev3.dansmultipro.co.id/api/recruitment/positions.json");
			mapResponse = mapper.readValue(getResponse, new TypeReference<List<Map<String, Object>>>(){});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(mapResponse);
	}
	
	@GetMapping("getJobDetail/{id}")
	public ResponseEntity<Map<String, Object>> getJobDetail(@PathVariable("id") String jobId){

		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> mapResponse = new HashMap<>();
		String getResponse;
		try {
			getResponse = getMethod("http://dev3.dansmultipro.co.id/api/recruitment/positions/" + jobId);
			mapResponse = mapper.readValue(getResponse, Map.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(mapResponse);
	}
	
	private String getMethod(String url) throws Exception {
		restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
		HttpStatus status = response.getStatusCode();
		String restCall = response.getBody();

		if (!status.toString().contains("200")) {
			throw new Exception("koneksi gagal");
		}
		return restCall;
	}
	

}
