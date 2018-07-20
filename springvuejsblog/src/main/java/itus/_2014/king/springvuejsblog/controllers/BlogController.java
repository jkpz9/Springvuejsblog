package itus._2014.king.springvuejsblog.controllers;

import itus._2014.king.springvuejsblog.configs.CustomerUserDetail;
import itus._2014.king.springvuejsblog.entities.Comment;
import itus._2014.king.springvuejsblog.entities.Post;
import itus._2014.king.springvuejsblog.entities.User;
import itus._2014.king.springvuejsblog.pojos.CommentPojo;
import itus._2014.king.springvuejsblog.services.CommentService;
import itus._2014.king.springvuejsblog.services.PostService;
import itus._2014.king.springvuejsblog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
public class BlogController {
    @Autowired
    private PostService postService;
    private UserService userService;
    private CommentService commentService;

    @GetMapping(value = "/posts")
    public List<Post> posts() {
        return this.postService.getAllPost();
    }

    @PostMapping("/post/{id}")
    public Post getPostById(@PathVariable Long id)
    {
        return this.postService.getPost(id);
    }


    @PostMapping(value="/posts")
    public String publishPost(@RequestBody Post post)
    {
        CustomerUserDetail customerUserDetail = (CustomerUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(post.getDateCreated() == null)
            post.setDateCreated(new Date());

        User user = this.userService.getUser(customerUserDetail.getUsername());

        post.setCreator(user);

        this.postService.insert(post);
        return "Post have been published";

    }


    @GetMapping("/posts/{username}")
    public List<Post> getPostByUser(@PathVariable String username)
    {
        return this.postService.findByUser(this.userService.getUser(username));
    }

    @DeleteMapping(value="/post/{id}")
    public boolean deletePost(@PathVariable Long id)
    {
        return this.postService.deletePost(id);
    }

    @DeleteMapping(value = "/comment/{id}")
    public boolean deleteComment(@PathVariable Long id){
        return commentService.deleteComment(id);
    }

    @GetMapping(value = "/comments/{postId}")
    public List<Comment> getComments(@PathVariable Long postId){
        return commentService.getComments(postId);
    }

    @PostMapping(value = "post/postComment")
    public boolean postComment(@RequestBody CommentPojo comment)
    {
        Post post = this.postService.getPost(comment.getPostId());

        CustomerUserDetail customerUserDetail = (CustomerUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User creator = this.userService.getUser(customerUserDetail.getUsername());

        if (post == null || creator == null)
            return false;

        this.commentService.comment(new Comment(comment.getText(), post, creator));
        return true;

    }

}
