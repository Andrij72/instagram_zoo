package com.akul.instagramzoo.repository;

import com.akul.instagramzoo.entity.Comment;
import com.akul.instagramzoo.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByPost(Post post);

    CommentRepository findByIdAndUserId(Long commentId, Long userId);
}
