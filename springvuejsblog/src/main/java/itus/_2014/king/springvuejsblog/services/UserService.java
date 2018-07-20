package itus._2014.king.springvuejsblog.services;

import itus._2014.king.springvuejsblog.entities.User;
import itus._2014.king.springvuejsblog.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    public void save(User user)
    {
        user.setPassword(passwordEncoder().encode(user.getPassword()));
        userRepository.save(user);
    }

    public User getUser(String username)
    {
        return userRepository.findByUsername(username);
    }

    public List<User> getAllUser()
    {
        return userRepository.findAll();
    }

}
