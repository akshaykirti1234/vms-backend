package tech.csm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import tech.csm.entity.Post;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Integer> {

    @Query("from Post where deletedFlag = false")
    List<Post> getAllPosts();

    @Modifying
    @Query("update Post set deletedFlag = true where postId = :postId")
    Integer deletePost(String postId);
}
