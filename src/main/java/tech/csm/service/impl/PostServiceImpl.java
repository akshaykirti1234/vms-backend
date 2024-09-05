package tech.csm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.csm.entity.Post;
import tech.csm.repository.PostRepo;
import tech.csm.service.PostService;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;

    @Override
    public List<Post> getAllPosts() {
        return postRepo.getAllPosts();
    }

    @Override
    public Post savePost(Post post) {
        return postRepo.save(post);
    }

    @Override
    public Post getPostById(Integer postId) {
        Optional<Post> postOptional = postRepo.findById(postId);
        return postOptional.orElseThrow(() -> new RuntimeException("Post not found with id: " + postId));
    }

    @Override
    @Transactional
    public Integer deletePost(String postId) {
        return postRepo.deletePost(postId);
    }
}
