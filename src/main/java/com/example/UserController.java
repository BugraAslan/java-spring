package com.example;

import com.example.Mysql.Entity.User;
import com.example.Mysql.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    public UserRepository userRepository;

    @GetMapping("/users")
    public Iterable<User> allUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/user")
    public void addUser(@RequestParam(value = "name") String name,
                        @RequestParam(value = "email") String email,
                        @RequestParam(value = "status") Integer status
    ){
        if (name.isEmpty() || email.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "name and email required");
        }

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setStatus(status);

        userRepository.save(user);
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable int id){
        boolean findUser = userRepository.existsById(id);
        if (findUser){
            userRepository.deleteById(id);
        }
    }

    @GetMapping("/user/{id}")
    public Optional<User> getUser(@PathVariable int id){
        return userRepository.findById(id);
    }

    @PatchMapping("/user/{id}")
    public ResponseEntity<?> updateUserName(@PathVariable("id") int id, @RequestBody User user){
        boolean existUser = userRepository.existsById(id);
        if (existUser){
            Optional<User> findUser = userRepository.findById(id);
            if (findUser.isPresent()) {
                findUser.get().setName(user.getName());
                findUser.get().setEmail(user.getEmail());
                findUser.get().setStatus(user.getStatus());
                userRepository.save(findUser.get());
                return new ResponseEntity<>(findUser.get(), HttpStatus.ACCEPTED);
            }
        }

        return new ResponseEntity<>("Not Found abi!", HttpStatus.NOT_FOUND);
    }
}
