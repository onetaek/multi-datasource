package com.example.multidatasource.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    @PostMapping
    public void createMulti(@RequestBody MultiCreateRequest testCreateRequest) {
        testService.createMulti(testCreateRequest);
    }

    @GetMapping
    public Map<String, List<TableResponse>> findAllDbData() {
        return testService.findAllDbData();
    }

    @DeleteMapping
    public void deleteAll() {
        testService.deleteAll();
    }
}
