package tech.csm.service;

import tech.csm.entity.Post;

import java.util.List;


public interface PostService {
    List<Post> getAllPosts();

    Post savePost(Post post);

    Post getPostById(Integer postId);

    Integer deletePost(String postId);
}
