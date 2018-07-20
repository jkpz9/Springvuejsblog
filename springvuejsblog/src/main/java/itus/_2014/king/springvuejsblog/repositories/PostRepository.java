package itus._2014.king.springvuejsblog.repositories;

import itus._2014.king.springvuejsblog.entities.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Long> {
    List<Post> findByCreatorId(Long id);
}
