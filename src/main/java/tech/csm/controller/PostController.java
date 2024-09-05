package tech.csm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.csm.entity.Post;
import tech.csm.service.PostService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/getAllPosts")
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @PostMapping("/savePost")
    public ResponseEntity<?> savePost(@RequestBody Post post) {
        Post savedPost = postService.savePost(post);
        if (savedPost.getPostId() == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @PutMapping("/editPost/{postId}")
    public ResponseEntity<?> editPost(@PathVariable("postId") Integer postId) {
        if (postId != null) {
            Post post = postService.getPostById(postId);
            return new ResponseEntity<>(post, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deletePost/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable String postId) {
        try {
            if (postId != null) {
                Integer count = postService.deletePost(postId);
                if (count > 0)
                    return new ResponseEntity<>(HttpStatus.OK);
                else
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
