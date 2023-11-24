package com.assesment.CommentAPI.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "Comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long commentId;

    @Column
    private String message;

    @Column
    private LocalDateTime date;

    @Column
    private Long postedBy;

    public Comment(String message, Long postedBy) {
        this.message = message;
        this.postedBy = postedBy;
    }

    public Comment() {

    }

    public Long getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(Long postedBy) {
        this.postedBy = postedBy;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @PrePersist
    public void prePersist() {
        date = LocalDateTime.now();
    }

}
