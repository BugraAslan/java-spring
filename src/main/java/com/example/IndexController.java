package com.example;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class IndexController {

    @RequestMapping("/")
    public String index() {
        return "First response!";
    }

    @GetMapping("/name/{name}")
    public String welcome(@PathVariable String name){
        if (name.isEmpty() || name.isBlank()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "This value should not be blank or empty!"
            );
        }

        return "Welcome " + name;
    }
}