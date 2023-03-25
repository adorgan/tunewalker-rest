package com.tunewalker.rest.service;

import com.tunewalker.rest.model.Blogpost;

import java.util.List;

public interface BlogpostService {

    List<Blogpost> findAll();
//    List<Blogpost> findAllBlogposts();
    Blogpost findByBlogpostId(String id);
}
