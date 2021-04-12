package com.example.hitapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

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
    public ResponseEntity<String> addEmployee(@RequestBody Employee employee) {
        restApiService.createEmployee(employee);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(employee.getId())
                .toUri();
        return  ResponseEntity.created(location).build();
    }

    @PutMapping(value = "/employees")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
        restApiService.updateEmployee(employee);
        return new ResponseEntity<Employee>(employee, HttpStatus.OK);
    }
}
