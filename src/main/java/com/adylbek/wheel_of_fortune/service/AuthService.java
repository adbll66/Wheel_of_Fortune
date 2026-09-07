package com.adylbek.wheel_of_fortune.service;

import com.adylbek.wheel_of_fortune.dto.LoginRequest;
import com.adylbek.wheel_of_fortune.dto.RegisterRequest;
import com.adylbek.wheel_of_fortune.entity.User;
import com.adylbek.wheel_of_fortune.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    public User register(RegisterRequest request) {
        if (userRepository.findByLogin(request.getLogin()).isPresent()) {
            throw new RuntimeException("Пользователь с таким логином уже существует!");
        }

        User user = new User();
        user.setLogin(request.getLogin());

        user.setPassword(request.getPassword());
        user.setRole(request.getRole() != null ? request.getRole() : "USER");

        return userRepository.save(user);
    }

    public String login(LoginRequest request) {
        User user = userRepository.findByLogin(request.getLogin())
                .orElseThrow(() -> new RuntimeException("Неверный логин или пароль"));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Неверный логин или пароль");
        }


        return "Успешный вход для пользователя: " + user.getLogin();
    }
}