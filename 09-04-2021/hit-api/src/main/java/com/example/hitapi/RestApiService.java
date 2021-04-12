package com.example.hitapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class RestApiService {
    @Autowired
    private RestTemplate restTemplate;

    static String uri = "http://localhost:8080/";

    public ResponseEntity<Employee> getEmployees() {
        //TODO: Autowire the RestTemplate in all the examples
        RestTemplate restTemplate = new RestTemplate();

        //we can get the response as String, and use a custom parser or use a string replacement function to fix the response before handling it to the parser.

        //String result = restTemplate.getForObject(uri, String.class);
        //System.out.println(result);

        //EmployeeListVO result = restTemplate.getForObject(uri, EmployeeListVO.class);

        //get for entity
        ResponseEntity<Employee> response = restTemplate.getForEntity(uri, Employee.class);
        return response;
    }


    //Sending HTTP Headers using RestTemplate
    private static void getEmployeesWithHeader() {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set("X-COM-PERSIST", "NO");
        headers.set("X-COM-LOCATION", "USA");

        HttpEntity<String> entity = new HttpEntity<String>(headers);

        ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);

        //Use the response.getBody()
    }

    //Sending URL Parameters using RestTemplate
    private static void getEmployeeById() {
        RestTemplate restTemplate = new RestTemplate();

        Map<String, String> params = new HashMap<String, String>();
        params.put("id", "1");

        Employee result = restTemplate.getForObject(uri, Employee.class, params);

        //Use the result
    }

    //RestTemplate to access HTTP POST api requests.
    private static void createEmployee() {
        final String uri = "http://localhost:8080/springrestexample/employees";
        RestTemplate restTemplate = new RestTemplate();

        Employee newEmployee = new Employee(1, "Adam", "Gilly", "test@email.com");

        Employee result = restTemplate.postForObject(uri, newEmployee, Employee.class);

        System.out.println(result);
    }

    //RestTemplate example to consume PUT API
    private static void updateEmployee() {
        final String uri = "http://localhost:8080/springrestexample/employees/{id}";
        RestTemplate restTemplate = new RestTemplate();

        Map<String, String> params = new HashMap<String, String>();
        params.put("id", "2");

        Employee updatedEmployee = new Employee(2, "New Name", "Gilly", "test@email.com");

        restTemplate.put ( uri, updatedEmployee, params );
    }

    //RestTemplate example to consume DELETE API
    private static void deleteEmployee()
    {
        RestTemplate restTemplate = new RestTemplate();

        Map<String, String> params = new HashMap<String, String>();
        params.put("id", "2");

        restTemplate.delete ( uri,  params );
    }

}