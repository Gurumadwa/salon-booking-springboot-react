package com.koushik.controller;

import com.koushik.model.User;
import com.koushik.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @PostMapping("/api/users")
    public User createUser(@RequestBody @Valid User user){
        return userRepository.save(user);
    }

    @GetMapping("/api/users")
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/api/users/{id}")
    public User getUserById(@PathVariable Long id) throws Exception {
        Optional<User> opt = userRepository.findById(id);
        if (opt.isPresent()){
            return opt.get();
        }
        throw new Exception("User not present!");
    }

    @PutMapping("/api/users/{id}")
    public User updateUser(@RequestBody User user, @PathVariable Long id){
        Optional<User> opt = userRepository.findById(id);

        if(opt.isEmpty()){
            throw new RuntimeException("User not found with id "+id);
        }
        User existingUser = opt.get();

        existingUser.setFullName(user.getFullName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPhone(user.getPhone());
        existingUser.setRole(user.getRole());

        return userRepository.save(existingUser);
    }

    @DeleteMapping("/api/users/{id}")
    public String deleteUser(@PathVariable Long id){
        Optional<User> opt = userRepository.findById(id);
        if(opt.isEmpty())
            throw new RuntimeException("User Not found for id "+id);

        userRepository.deleteById(opt.get().getId());
        return "User deleted successfully!";
    }

}
