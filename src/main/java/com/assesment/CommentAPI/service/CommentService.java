package com.assesment.CommentAPI.service;

import com.assesment.CommentAPI.model.Comment;
import com.assesment.CommentAPI.Repository.ICommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private ICommentRepository commentRepository;

    public List<Comment> getAllComment() {

        List<Comment> comments = new ArrayList<>();

        commentRepository.findAll().forEach((comment) -> comments.add(comment));

        return comments;
    }

    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }
}
