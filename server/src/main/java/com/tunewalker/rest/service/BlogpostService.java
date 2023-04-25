package com.tunewalker.rest.service;

import com.tunewalker.rest.dto.BlogpostDTO;
import com.tunewalker.rest.model.Blogpost;

import java.util.List;

public interface BlogpostService {

    List<Blogpost> findAll();
    Blogpost findByBlogpostId(String id);

    void save(Blogpost blogpost);

    void updateBlog(Blogpost blogpost, String id);

    void delete(String id);
}
