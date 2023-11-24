package com.assesment.CommentAPI.controller;

import com.assesment.CommentAPI.model.Comment;
import com.assesment.CommentAPI.model.User;
import com.assesment.CommentAPI.service.CommentService;
import com.assesment.CommentAPI.service.UserService;
import com.assesment.CommentAPI.util.Message;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    @PostMapping("/add")
    public String addUserAndComment(@RequestBody @NotNull Message messageBody) {
        String result = "comment added successfully";
        String from = messageBody.getFrom();
        String to = messageBody.getTo();
        String message = messageBody.getMessage();

        if (!isValidUserName(from) || !isValidUserName(to)) {
            result = "Invalid Request";
        } else {
            User user = userService.getUserByFromAndTo(from, to);
            if (user == null) {
                user = userService.save(new User(from, to));
            }
            Comment comment = new Comment(message, user.getUserId());
            commentService.save(comment);
        }

        return result;
    }

    @GetMapping("/show")
    public List<String> getAllMessages(@RequestParam String userName) {
        Set<Long> userIds = new HashSet<>();
        userService.findByUserName(userName).forEach((user -> userIds.add(user.getUserId())));
        List<String> messages = new ArrayList<>();

        commentService.getAllComment().forEach((comment -> {
            if(userIds.contains(comment.getPostedBy())) {
                messages.add(comment.getMessage());
            }
        }));


        return messages;
    }

    private boolean isValidUserName(String name) {
        if(name == null) {
            return false;
        }

        name = name.trim();

        if(name.isEmpty()) {
            return false;
        }

        String regex = "^[a-zA-Z]+$";

        Pattern pattern = Pattern.compile(regex);

        return pattern.matcher(name).matches();
    }
}
