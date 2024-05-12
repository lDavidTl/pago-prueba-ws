package com.pago.pgo.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseFormat {
	public static Map<String, Object> success(Object data) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("data", data);
        return response;
    }

    public static Map<String, Object> error(String message, int statusCode) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "error");
        response.put("message", message);
        response.put("statusCode", statusCode); 
        return response;
    }
    public static ResponseEntity<Map<String, Object>> errorEntity(String message, HttpStatus statusCode) {
    	Map<String, Object> response = new HashMap<>();
    	response.put("status", "error");
    	response.put("message", message);
    	response.put("statusCode", statusCode.value()); 
    	return ResponseEntity.status(statusCode).body(response);
    }
    
    public static ResponseEntity<Map<String, Object>> successEntity(Object data) {
    	Map<String, Object> response = new HashMap<>();
    	response.put("status", "success");
        response.put("data", data);
    	response.put("statusCode", "200"); 
    	return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }
}
