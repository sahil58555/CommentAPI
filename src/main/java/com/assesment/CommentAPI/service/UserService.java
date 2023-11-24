package com.assesment.CommentAPI.service;

import com.assesment.CommentAPI.model.User;
import com.assesment.CommentAPI.Repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private IUserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }

    public User getUserByFromAndTo(String from, String to) {
        return userRepository.findByFromAndTo(from, to);
    }

    public List<User> findByUserName(String to) {
        return userRepository.findByTo(to);
    }
}
