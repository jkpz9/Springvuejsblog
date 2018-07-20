package itus._2014.king.springvuejsblog.services;

import itus._2014.king.springvuejsblog.entities.Post;
import itus._2014.king.springvuejsblog.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import itus._2014.king.springvuejsblog.entities.User;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;


    public List<Post> getAllPost() {
        return (List<Post>) this.postRepository.findAll();
    }

    public void insert(Post post) {
        if (post.getDateCreated() == null) post.setDateCreated(new Date());
        this.postRepository.save(post);
    }

    public List<Post> findByUser(User user)
    {
        return postRepository.findByCreatorId(user.getId());
    }

    public boolean deletePost(Long postId)
    {
        Post thePost = this.postRepository.findById(postId).orElse(null);
        if (thePost == null) return false;
        this.postRepository.deleteById(postId);
        return true;
    }

    public Post getPost(Long id)
    {
        return this.postRepository.findById(id).orElse(null);
    }


}
