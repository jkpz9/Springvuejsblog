package itus._2014.king.springvuejsblog.services;

import itus._2014.king.springvuejsblog.entities.Comment;
import itus._2014.king.springvuejsblog.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> getComments(Long postId)
    {
        return this.commentRepository.findByPostId(postId);
    }

    public void comment(Comment c)
    {
        this.commentRepository.save(c);
    }

    public boolean deleteComment(Long id)
    {
        this.commentRepository.deleteById(id);
        return true;
    }

}
