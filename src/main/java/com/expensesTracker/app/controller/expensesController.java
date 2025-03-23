package com.expensesTracker.app.controller;

import com.expensesTracker.app.DTO.expensesDTO;
import com.expensesTracker.app.service.expensesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tracker")
public class expensesController {
    @Autowired
    private expensesService service;

    @GetMapping("/expenses")
    public List<expensesDTO> getAll(){
        return service.getAllExpenses();
    }

    @GetMapping("/expenses/{id}")
    public Optional<expensesDTO> getById(@PathVariable int id){
        return service.getExpenseById(id);
    }

    @DeleteMapping("/expenses/{id}")
    public void deleteById(@PathVariable int id){
        service.deleteExpense(id);
    }

    @PostMapping("/expenses")
    public expensesDTO createExpense(@RequestBody expensesDTO expensesDTO){
        return service.saveExpense(expensesDTO);
    }

    @GetMapping("/expenses/{userId}/users")
    public ResponseEntity<Map<String, Double>> getExpensesByCategoryPerUser(@PathVariable int userId) {
        return ResponseEntity.ok(service.getExpensesByCategoryPerUser(userId));
    }

    @GetMapping("/expenses/users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, Double>> getExpensesByCategory() {
        return ResponseEntity.ok(service.getExpensesByCategory());
    }

    @GetMapping("/expenses/chart")
    public ResponseEntity<byte[]> getExpensesByCategoryChart() {

        RestTemplate restTemplate = new RestTemplate();
        Map<String, Double> chartData = service.getExpensesByCategory();

        String labelsJson = chartData.keySet().stream()
                .map(label -> "\"" + label + "\"")
                .collect(Collectors.joining(",", "[", "]"));

        String dataJson = chartData.values().stream()
                .map(String::valueOf)
                .collect(Collectors.joining(",", "[", "]"));

        String chartJson = "{"
                + "\"type\": \"doughnut\","
                + "\"data\": {"
                + "  \"labels\": " + labelsJson + ","
                + "  \"datasets\": [{"
                + "    \"label\": \"Expenses Categories\","
                + "    \"data\": " + dataJson
                + "  }]"
                + "}"
                + "}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String requestBody = "{"
                + "\"chart\": " + chartJson + ","
                + "\"backgroundColor\": \"white\","
                + "\"width\": 500,"
                + "\"height\": 300,"
                + "\"devicePixelRatio\": 1.0,"
                + "\"format\": \"png\","
                + "\"version\": \"2.9.3\""
                + "}";

        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        String url = "https://quickchart.io/chart";

        ResponseEntity<byte[]> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                byte[].class
        );

            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_PNG)
                    .body(response.getBody());
           }


}
