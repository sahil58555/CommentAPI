package com.assesment.CommentAPI.Repository;

import com.assesment.CommentAPI.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    User findByFromAndTo(String from, String to);

    List<User> findByTo(String to);
}
