package com.tunewalker.rest.repository;

import com.tunewalker.rest.model.Blogpost;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BlogpostRepository extends MongoRepository<Blogpost, String> {

    Blogpost findBlogpostById(String id);

//    List<Blogpost> findAllBlogposts();
}
