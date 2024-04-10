package com.project.Student.Management;

import java.util.List;

import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class JsonObject {
    public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, List<Student> students) {
        Map<String, Object> jsonResponse = new LinkedHashMap<>();
        jsonResponse.put("status", status.value());
        jsonResponse.put("message", message);
        jsonResponse.put("data", students);

        return new ResponseEntity<>(jsonResponse, status);
    }
}

