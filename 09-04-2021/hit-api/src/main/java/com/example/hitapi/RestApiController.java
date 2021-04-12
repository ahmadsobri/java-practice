package com.example.hitapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RestApiController {

    @Autowired
    RestApiService restApiService;

    @GetMapping(value = "/employees")
    public ResponseEntity<Employee> getEmployee(@PathVariable("id")Employee employee)
    {
        //TODO: Save get employee
        ResponseEntity<Employee> result = restApiService.getEmployees();
        return result;
    }

    @PostMapping(value = "/employees")
    public ResponseEntity<Employee> getEmployee(@RequestBody Employee employee) {
    }

    @GetMapping(value = "/employees")
    public ResponseEntity<Employee> getEmployee(@PathVariable("id") int id, Employee employee) {
    }
}
