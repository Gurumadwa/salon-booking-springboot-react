package com.koushik.service.impl;

import com.koushik.exception.UserException;
import com.koushik.model.User;
import com.koushik.repository.UserRepository;
import com.koushik.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> opt = userRepository.findById(id);
        if (opt.isPresent()){
            return opt.get();
        }
        throw new UserException("User not present!");
    }

    @Override
    public User updateUser(User user, Long id) {
        Optional<User> opt = userRepository.findById(id);

        if(opt.isEmpty()){
            throw new UserException("User not found with id "+id);
        }
        User existingUser = opt.get();

        existingUser.setFullName(user.getFullName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPhone(user.getPhone());
        existingUser.setRole(user.getRole());
        existingUser.setUsername(user.getUsername());

        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(Long id) {
        Optional<User> opt = userRepository.findById(id);
        if(opt.isEmpty())
            throw new UserException("User Not found for id "+id);

        userRepository.deleteById(opt.get().getId());
    }
}
